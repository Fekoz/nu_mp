package com.carpetti.marketplaceseller.service;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductV2ProductsStocksRequestStockAndDateDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductV2ProductsStocksRequestStockDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductImportOzonDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductStocksDto;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductInfoOzonDTO;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;


@Service
public class ManagerOzonService {


    public ManagerOzonService(OzonUtilsService ozonUtilsService,
                              OzonApiService ozonApiService,
                              AppUtilsService appUtilsService,
                              PredictsService predictsService
    ) {
        this.ozonUtilsService = ozonUtilsService;
        this.ozonApiService = ozonApiService;
        this.predictsService = predictsService;
        this.appUtilsService = appUtilsService;
    }

    private static final Logger logger
            = LoggerFactory.getLogger(ManagerOzonService.class);

    private OzonUtilsService ozonUtilsService;
    private OzonApiService ozonApiService;
    private PredictsService predictsService;
    private AppUtilsService appUtilsService;


    private final BlockingQueue<ProductV2ProductsStocksRequestStockAndDateDto> queueStocksMGT = new LinkedBlockingQueue<>();
    private final BlockingQueue<ProductV2ProductsStocksRequestStockAndDateDto> queueStocksKGT = new LinkedBlockingQueue<>();
    private final ScheduledExecutorService executorScheduledTaskId = Executors.newSingleThreadScheduledExecutor();

    @Value("${ozon.warehouse.kgt.id:0}")
    private long idWarehouseKGT;

    @Value("${ozon.warehouse.mgt.id:0}")
    private long idWarehouseMGT;

    private AtomicInteger counterWorker = new AtomicInteger(0);
    private AtomicInteger counterScheduled = new AtomicInteger(0);
    private AtomicInteger counterQuery = new AtomicInteger(0);

    //Каждые 2 минуты и 5 секунд отправлять Stocks
    @Scheduled(fixedRate = 125L, timeUnit = TimeUnit.SECONDS)
    private void updateWarehouseQuantities() {
        sendStocksToWarehouse(queueStocksMGT);
        logger.info("в коллекции queueStocksMGT осталось: {}", queueStocksMGT.size());
        sendStocksToWarehouse(queueStocksKGT);
        logger.info("в коллекции queueStocksKGT осталось: {}", queueStocksKGT.size());
    }

    public void sendObjToOzon(RequestRabbitMQDataDTO dto) {
        Date dateDeadData = ozonUtilsService.parseDate(dto.getLiveDate().getTo());
        ResponseProductInfoOzonDTO responseProductInfoOzonDTO;
        boolean isDeadData = predictsService.getIsDeadData(dateDeadData);

        if (isDeadData) {
            logger.info("ДАнные мертвы {}", dto.getLiveDate().getTo());
            return;
        }

        RequestProductImportOzonDto requestProductImportOzonDto = ozonUtilsService.createRequestProductImportOzonDto(dto);

        try {
            responseProductInfoOzonDTO = ozonApiService.getInfoItemFromOzon(dto.getId()).block();
            Objects.requireNonNull(responseProductInfoOzonDTO);
        }catch (Exception e){
            ozonApiService.createOrUpdateCarpet(requestProductImportOzonDto).block();
            return;
        }

        if (!predictsService.equalsProductv2ImportProductsRequestItemDtoPrepareSend(dto, requestProductImportOzonDto, responseProductInfoOzonDTO)) {
            ozonApiService.createOrUpdateCarpet(requestProductImportOzonDto).block();
            return;
        }

        int counter = counterQuery.incrementAndGet();
        logger.info("Коунтер в ОЗОН: {}", counter);


        //Получить результат одного товар. Потому что мы по штучно отправляем в ozon. У нас  getItems всегда имеет только один результат.
        //Получить продуктID
        long productIdOnOzon = responseProductInfoOzonDTO
                .getResult()
                .getId();

        //Если не Создана карточка товара то дальше ничего не делать
        if (responseProductInfoOzonDTO.getResult().getStatus().isFailed()) {
            logger.info("Создан ли товар = {}", responseProductInfoOzonDTO.getResult().getStatus().isCreated());
            logger.info("Если ошибки у товара = {}", responseProductInfoOzonDTO.getResult().getStatus().isFailed());
            return;
        }

        int countCarpetInKafkaDto = dto.isAvailable() ? dto.getPriceCount() : 0;
        int countCarpetInOzon = responseProductInfoOzonDTO.getResult().getStocks().getPresent();

        //Если кол-во товаров одинакого то не изменять их на складах
        if (countCarpetInOzon == countCarpetInKafkaDto) {
            logger.info("Товар не будет добавлин на склад так как значение уже такое на складе");
            return;
        }

        addQueueStockCarpet(dto, productIdOnOzon, countCarpetInKafkaDto, predictsService.isKgt(responseProductInfoOzonDTO));
    }

    private void addQueueStockCarpet(RequestRabbitMQDataDTO dto, long productIdOnOzon, int stock, boolean isKGT) {

        long idWarehouse = isKGT ? idWarehouseKGT : idWarehouseMGT;

        //Если id Склада не указан то он равен 0 и не будет дальше ничего делать
        if (idWarehouse == 0) {
            logger.info("Склад не указан");
            return;
        }

        ProductV2ProductsStocksRequestStockDto productV2ProductsStocksRequestStockDto = ozonUtilsService.createObjProductV2ProductsStocksRequestStockDto(dto.getId(), productIdOnOzon, stock, idWarehouse);
        ProductV2ProductsStocksRequestStockAndDateDto stockDto = new ProductV2ProductsStocksRequestStockAndDateDto(productV2ProductsStocksRequestStockDto, dto.getLiveDate().getTo());

        try {
            if (isKGT) {
                int counter = counterScheduled.incrementAndGet();
                logger.info("Коунтер в counterScheduled -BIG: {}", counter);
                queueStocksKGT.put(stockDto);
                logger.info("Положил в свою очередь Крупногабарит: \n {}", appUtilsService.tryBlockAndParsToJsonString(stockDto));
            } else {
                int counter = counterScheduled.incrementAndGet();
                logger.info("Коунтер в counterScheduled -small: {}", counter);
                queueStocksMGT.put(stockDto);
                logger.info("Положил в свою очередь МалоГабарит: \n {}", appUtilsService.tryBlockAndParsToJsonString(stockDto));
            }
            logger.info("Положил в очереь stocks");
        } catch (InterruptedException e) {
            logger.error(e.getMessage(), e);
        }
    }

    void sendStocksToWarehouse(BlockingQueue<ProductV2ProductsStocksRequestStockAndDateDto> queue) {
        final int MAXIMUM_STOCK_OFFERS_PER_WAREHOUSE = 80;

        if (queue.isEmpty()) {
            logger.warn("Одна или обе коллекции 'очереди стоки' пустые: В ozon");
            return;
        }

        List<ProductV2ProductsStocksRequestStockDto> listStocks = new ArrayList<>();

        for (int i = 0; i < MAXIMUM_STOCK_OFFERS_PER_WAREHOUSE; i++) {
            ProductV2ProductsStocksRequestStockAndDateDto productStockAndDate = queue.poll();
            if (productStockAndDate == null) {
                continue;
            }

            Date dateDeadData = ozonUtilsService.parseDate(productStockAndDate.getLiveDate());
            boolean isDeadData = predictsService.getIsDeadData(dateDeadData);
            if (isDeadData) {
                logger.info("Дата сметри данных в очереди : {}", productStockAndDate.getLiveDate());
                i--;
                continue;
            }


            logger.info("Добавил в 'очередь стоки': В ozon");
            listStocks.add(productStockAndDate.getProductV2ProductsStocksRequestStockDto());

        }

        RequestProductStocksDto reqStocksDto = new RequestProductStocksDto(listStocks);
        logger.info("Request /v2/products/stocks: {}", appUtilsService.tryBlockAndParsToJsonString(reqStocksDto));

        ozonApiService.updateQuantityInWarehouses(reqStocksDto)
                .subscribe(
                        resp -> logger.info("Response /v2/products/stocks: {}", appUtilsService.tryBlockAndParsToJsonString(resp)),
                        throwable -> logger.error("Response /v2/products/stocks ERROR:  {}", throwable)
                );
        int counter = counterWorker.incrementAndGet();
        logger.info("Коунтер в Воркере: {}", counter);
    }


    @PreDestroy
    private void destroyService() {
        executorScheduledTaskId.shutdown();
        logger.info("ScheduledTaskId shutdown ozon");
    }


}
