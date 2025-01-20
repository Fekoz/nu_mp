package com.carpetti.marketplaceseller.controller;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.*;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductImportOzonDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductsInfoAttributesOzonDTO;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductInfoOzonDTO;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductsInfoAttributesOzonDTO;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import com.carpetti.marketplaceseller.service.ManagerOzonService;
import com.carpetti.marketplaceseller.service.OzonApiService;
import com.carpetti.marketplaceseller.service.OzonUtilsService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Component
@Profile("unrabbitmq")
public class UnRabbitStarter {

    @Autowired
    private OzonApiService ozonApiService;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private OzonUtilsService ozonUtilsService;

    @Autowired
    private ManagerOzonService managerOzonService;

    private ArrayList<String> queue = new ArrayList<>();

    private static final Logger logger
            = LoggerFactory.getLogger(UnRabbitStarter.class);

    @PostConstruct
    void init() throws JsonProcessingException {


        queue.add("{\"character\":{\"other\":{\"id\":970991393,\"value\":\"Джут\"},\"styles\":{\"id\":970789952,\"value\":\"Современный\"},\"country\":{\"id\":90295,\"value\":\"Россия\"},\"typeCarpet\":{\"id\":93655,\"value\":\"Ковер\"},\"typeBase\":{\"id\":61818,\"value\":\"Джут\"},\"fabric\":{\"id\":971981412,\"value\":\"CARPETTI\"},\"form\":{\"id\":43084,\"value\":\"Прямоугольник\"},\"color\":{\"id\":258411648,\"value\":\"кремовый\"}},\"linkedName\":\"\",\"statCarpet\":{\"length\":1,\"width\":200,\"height\":300},\"categoriesName\":[\"Россия\",\"Прямоугольник\",\"Современный\",\"B9AFC\"],\"linkedItemIds\":[],\"priceToMarket\":{\"ozon\":{\"old\":6884,\"current\":6884},\"yandex\":{\"old\":6884,\"current\":6884},\"wildberries\":{\"old\":6884,\"current\":6884},\"original\":{\"old\":4884,\"current\":4884},\"reformat\":{\"old\":6884,\"current\":6884}},\"number\":2754,\"linkedItemNumbers\":[],\"liveDate\":{\"at\":\"2024-01-05 15:06:34\",\"to\":\"2024-01-05 21:06:34\"},\"productId\":658,\"productArticle\":\"2D013E-CREAM-B9AFC\",\"productUuid\":\"b1c1c47e8f46ddac3eb11833cd5a89e4\",\"priceUuid\":\"38571ca22729d4c51d224ddf6d847f90\",\"priceMid\":\"974d435e2b4a33d09cb674c5f34bf310\",\"circle\":false,\"priceCount\":3,\"productParams\":[{\"key\":\"Коллекция на вендоре\",\"name\":\"vendor_collection\",\"value\":\"VALENCIA DELUXE\"},{\"key\":\"Качество\",\"name\":\"quality\",\"value\":\"HEATSET\"},{\"key\":\"Состав\",\"name\":\"compound\",\"value\":\"PP\"},{\"key\":\"Фактура\",\"name\":\"facture\",\"value\":\"Гладкая\"},{\"key\":\"Плотность\",\"name\":\"density\",\"value\":\"304000 точек\\/м2\"},{\"key\":\"Высота ворса\",\"name\":\"pile\",\"value\":\"10 мм\"},{\"key\":\"Вес\",\"name\":\"weight\",\"value\":\"1805 гр.\\/м2\"},{\"key\":\"Тип основы\",\"name\":\"warp\",\"value\":\"Джут\"},{\"key\":\"Вендор\",\"name\":\"vendor_name\",\"value\":\"MERINOS\"},{\"key\":\"Страна\",\"name\":\"country\",\"value\":\"Россия\"},{\"key\":\"Способ изготовления\",\"name\":\"preparation\",\"value\":\"тканый машин.\"},{\"key\":\"Стиль\",\"name\":\"style\",\"value\":\"Современный\"},{\"key\":\"Форма\",\"name\":\"form\",\"value\":\"Прямоугольник\"},{\"key\":\"Код цвета\",\"name\":\"color\",\"value\":\"CREAM\"},{\"key\":\"Код дизайна\",\"name\":\"design\",\"value\":\"SV05\"},{\"key\":\"Цвет\",\"name\":\"colour\",\"value\":\"Кремовый\"},{\"key\":\"Тип ворса ковра\",\"name\":\"pile_type\",\"value\":\"С ворсом\"},{\"key\":\"Тип ковра\",\"name\":\"carpet_type\",\"value\":\"Ковер\"},{\"key\":\"Тип изготавления\",\"name\":\"prepare_type\",\"value\":\"Машинный\"},{\"key\":\"Фабрика\",\"name\":\"fabric\",\"value\":\"CARPETTI\"},{\"key\":\"Коллекция\",\"name\":\"collection\",\"value\":\"B9AFC\"}],\"productEan\":\"2000000027548\",\"statDimension\":{\"length\":201,\"width\":16,\"height\":16},\"quantity\":1,\"name\":\"Ковер B9AFC - Прямоугольник Кремовый, Современный, Ковер на пол, в гостиную, спальню, в ассортименте, Турция, Бельгия, Россия (200 см. на 300 см.)\",\"vendor\":\"B9AFC\",\"vendorCode\":\"2D013E-CREAM-B9AFC\",\"id\":\"carpetti.TST.id.974d435e2b4a33d09cb674c5f34bf310\",\"available\":true,\"url\":\"https:\\/\\/carpetti.vip\\/carpet?article=2D013E-CREAM-B9AFC\",\"currencyId\":\"RUR\",\"categoriesId\":[15,18,122,54],\"deliveryOptions\":[],\"description\":\"Коллекция B9AFC предлагает ковер Прямоугольник формы. Этот ковер изготовлен в Россия с качеством HEATSET и имеет Кремовый цвет с Современный дизайн. Это отличная находка для дома и дачи, которая обеспечит тепло и комфорт. Такой интерьерный аксессуар разных цветов очень приятен для ног, ходить по нему одно удовольствие. Интерьерное украшение очень легко чистить, предназначено как для влажной, так и для сухой уборки, для робота пылесоса. Товар полностью безопасен для здоровья и имеет необходимые сертификаты соответствия качества.\",\"countryOfOrigin\":\"Россия\",\"weight\":1.08,\"params\":[],\"pictures\":[\"https:\\/\\/vnstatic.net\\/venera\\/big\\/valencia-deluxe-sv05-cream-stan.jpg\"],\"customElements\":[]}\n");
        queue.add("{\"character\":{\"other\":{\"id\":970991393,\"value\":\"Джут\"},\"styles\":{\"id\":970789952,\"value\":\"Современный\"},\"country\":{\"id\":90295,\"value\":\"Россия\"},\"typeCarpet\":{\"id\":93655,\"value\":\"Ковер\"},\"typeBase\":{\"id\":61818,\"value\":\"Джут\"},\"fabric\":{\"id\":971981412,\"value\":\"CARPETTI\"},\"form\":{\"id\":43084,\"value\":\"Прямоугольник\"},\"color\":{\"id\":258411648,\"value\":\"кремовый\"}},\"linkedName\":\"\",\"statCarpet\":{\"length\":1,\"width\":80,\"height\":150},\"categoriesName\":[\"Россия\",\"Прямоугольник\",\"Современный\",\"B9AFC\"],\"linkedItemIds\":[],\"priceToMarket\":{\"ozon\":{\"old\":2976,\"current\":2976},\"yandex\":{\"old\":2976,\"current\":2976},\"wildberries\":{\"old\":2976,\"current\":2976},\"original\":{\"old\":976,\"current\":976},\"reformat\":{\"old\":2976,\"current\":2976}},\"number\":2755,\"linkedItemNumbers\":[],\"liveDate\":{\"at\":\"2024-01-05 15:06:34\",\"to\":\"2024-01-05 21:06:34\"},\"productId\":658,\"productArticle\":\"2D013E-CREAM-B9AFC\",\"productUuid\":\"b1c1c47e8f46ddac3eb11833cd5a89e4\",\"priceUuid\":\"a9150260fd6870f05425b460fd6e947d\",\"priceMid\":\"3f80319adc49fb139fa2fb4732189b63\",\"circle\":false,\"priceCount\":28,\"productParams\":[{\"key\":\"Коллекция на вендоре\",\"name\":\"vendor_collection\",\"value\":\"VALENCIA DELUXE\"},{\"key\":\"Качество\",\"name\":\"quality\",\"value\":\"HEATSET\"},{\"key\":\"Состав\",\"name\":\"compound\",\"value\":\"PP\"},{\"key\":\"Фактура\",\"name\":\"facture\",\"value\":\"Гладкая\"},{\"key\":\"Плотность\",\"name\":\"density\",\"value\":\"304000 точек\\/м2\"},{\"key\":\"Высота ворса\",\"name\":\"pile\",\"value\":\"10 мм\"},{\"key\":\"Вес\",\"name\":\"weight\",\"value\":\"1805 гр.\\/м2\"},{\"key\":\"Тип основы\",\"name\":\"warp\",\"value\":\"Джут\"},{\"key\":\"Вендор\",\"name\":\"vendor_name\",\"value\":\"MERINOS\"},{\"key\":\"Страна\",\"name\":\"country\",\"value\":\"Россия\"},{\"key\":\"Способ изготовления\",\"name\":\"preparation\",\"value\":\"тканый машин.\"},{\"key\":\"Стиль\",\"name\":\"style\",\"value\":\"Современный\"},{\"key\":\"Форма\",\"name\":\"form\",\"value\":\"Прямоугольник\"},{\"key\":\"Код цвета\",\"name\":\"color\",\"value\":\"CREAM\"},{\"key\":\"Код дизайна\",\"name\":\"design\",\"value\":\"SV05\"},{\"key\":\"Цвет\",\"name\":\"colour\",\"value\":\"Кремовый\"},{\"key\":\"Тип ворса ковра\",\"name\":\"pile_type\",\"value\":\"С ворсом\"},{\"key\":\"Тип ковра\",\"name\":\"carpet_type\",\"value\":\"Ковер\"},{\"key\":\"Тип изготавления\",\"name\":\"prepare_type\",\"value\":\"Машинный\"},{\"key\":\"Фабрика\",\"name\":\"fabric\",\"value\":\"CARPETTI\"},{\"key\":\"Коллекция\",\"name\":\"collection\",\"value\":\"B9AFC\"}],\"productEan\":\"2000000027555\",\"statDimension\":{\"length\":81,\"width\":12,\"height\":12},\"quantity\":1,\"name\":\"Ковер B9AFC - Прямоугольник Кремовый, Современный, Ковер на пол, в гостиную, спальню, в ассортименте, Турция, Бельгия, Россия (80 см. на 150 см.)\",\"vendor\":\"B9AFC\",\"vendorCode\":\"2D013E-CREAM-B9AFC\",\"id\":\"carpetti.TST.id.3f80319adc49fb139fa2fb4732189b63\",\"available\":true,\"url\":\"https:\\/\\/carpetti.vip\\/carpet?article=2D013E-CREAM-B9AFC\",\"currencyId\":\"RUR\",\"categoriesId\":[15,18,122,54],\"deliveryOptions\":[],\"description\":\"Коллекция B9AFC предлагает ковер Прямоугольник формы. Этот ковер изготовлен в Россия с качеством HEATSET и имеет Кремовый цвет с Современный дизайн. Это отличная находка для дома и дачи, которая обеспечит тепло и комфорт. Такой интерьерный аксессуар разных цветов очень приятен для ног, ходить по нему одно удовольствие. Интерьерное украшение очень легко чистить, предназначено как для влажной, так и для сухой уборки, для робота пылесоса. Товар полностью безопасен для здоровья и имеет необходимые сертификаты соответствия качества.\",\"countryOfOrigin\":\"Россия\",\"weight\":0.22,\"params\":[],\"pictures\":[\"https:\\/\\/vnstatic.net\\/venera\\/big\\/valencia-deluxe-sv05-cream-stan.jpg\"],\"customElements\":[]}\n");
        queue.add("{\"character\":{\"other\":{\"id\":970991393,\"value\":\"Джут\"},\"styles\":{\"id\":970789952,\"value\":\"Современный\"},\"country\":{\"id\":90295,\"value\":\"Россия\"},\"typeCarpet\":{\"id\":93655,\"value\":\"Ковер\"},\"typeBase\":{\"id\":61818,\"value\":\"Джут\"},\"fabric\":{\"id\":971981412,\"value\":\"CARPETTI\"},\"form\":{\"id\":43084,\"value\":\"Прямоугольник\"},\"color\":{\"id\":258411648,\"value\":\"кремовый\"}},\"linkedName\":\"\",\"statCarpet\":{\"length\":1,\"width\":0,\"height\":0},\"categoriesName\":[\"Россия\",\"Прямоугольник\",\"Современный\",\"B9AFC\"],\"linkedItemIds\":[],\"priceToMarket\":{\"ozon\":{\"old\":2976,\"current\":2976},\"yandex\":{\"old\":2976,\"current\":2976},\"wildberries\":{\"old\":2976,\"current\":2976},\"original\":{\"old\":976,\"current\":976},\"reformat\":{\"old\":2976,\"current\":2976}},\"number\":2756,\"linkedItemNumbers\":[],\"liveDate\":{\"at\":\"2024-01-05 15:06:34\",\"to\":\"2024-01-05 21:06:34\"},\"productId\":658,\"productArticle\":\"2D013E-CREAM-B9AFC\",\"productUuid\":\"b1c1c47e8f46ddac3eb11833cd5a89e4\",\"priceUuid\":\"618fa439f19a20c17a2527d9abcb3fa7\",\"priceMid\":\"1be36378d99fdade9efa8cf084de1052\",\"circle\":false,\"priceCount\":0,\"productParams\":[{\"key\":\"Коллекция на вендоре\",\"name\":\"vendor_collection\",\"value\":\"VALENCIA DELUXE\"},{\"key\":\"Качество\",\"name\":\"quality\",\"value\":\"HEATSET\"},{\"key\":\"Состав\",\"name\":\"compound\",\"value\":\"PP\"},{\"key\":\"Фактура\",\"name\":\"facture\",\"value\":\"Гладкая\"},{\"key\":\"Плотность\",\"name\":\"density\",\"value\":\"304000 точек\\/м2\"},{\"key\":\"Высота ворса\",\"name\":\"pile\",\"value\":\"10 мм\"},{\"key\":\"Вес\",\"name\":\"weight\",\"value\":\"1805 гр.\\/м2\"},{\"key\":\"Тип основы\",\"name\":\"warp\",\"value\":\"Джут\"},{\"key\":\"Вендор\",\"name\":\"vendor_name\",\"value\":\"MERINOS\"},{\"key\":\"Страна\",\"name\":\"country\",\"value\":\"Россия\"},{\"key\":\"Способ изготовления\",\"name\":\"preparation\",\"value\":\"тканый машин.\"},{\"key\":\"Стиль\",\"name\":\"style\",\"value\":\"Современный\"},{\"key\":\"Форма\",\"name\":\"form\",\"value\":\"Прямоугольник\"},{\"key\":\"Код цвета\",\"name\":\"color\",\"value\":\"CREAM\"},{\"key\":\"Код дизайна\",\"name\":\"design\",\"value\":\"SV05\"},{\"key\":\"Цвет\",\"name\":\"colour\",\"value\":\"Кремовый\"},{\"key\":\"Тип ворса ковра\",\"name\":\"pile_type\",\"value\":\"С ворсом\"},{\"key\":\"Тип ковра\",\"name\":\"carpet_type\",\"value\":\"Ковер\"},{\"key\":\"Тип изготавления\",\"name\":\"prepare_type\",\"value\":\"Машинный\"},{\"key\":\"Фабрика\",\"name\":\"fabric\",\"value\":\"CARPETTI\"},{\"key\":\"Коллекция\",\"name\":\"collection\",\"value\":\"B9AFC\"}],\"productEan\":\"2000000027562\",\"statDimension\":{\"length\":1,\"width\":1,\"height\":1},\"quantity\":1,\"name\":\"Ковер B9AFC - Прямоугольник Кремовый, Современный, Ковер на пол, в гостиную, спальню, в ассортименте, Турция, Бельгия, Россия (0 см. на 0 см.)\",\"vendor\":\"B9AFC\",\"vendorCode\":\"2D013E-CREAM-B9AFC\",\"id\":\"carpetti.TST.id.1be36378d99fdade9efa8cf084de1052\",\"available\":false,\"url\":\"https:\\/\\/carpetti.vip\\/carpet?article=2D013E-CREAM-B9AFC\",\"currencyId\":\"RUR\",\"categoriesId\":[15,18,122,54],\"deliveryOptions\":[],\"description\":\"Коллекция B9AFC предлагает ковер Прямоугольник формы. Этот ковер изготовлен в Россия с качеством HEATSET и имеет Кремовый цвет с Современный дизайн. Это отличная находка для дома и дачи, которая обеспечит тепло и комфорт. Такой интерьерный аксессуар разных цветов очень приятен для ног, ходить по нему одно удовольствие. Интерьерное украшение очень легко чистить, предназначено как для влажной, так и для сухой уборки, для робота пылесоса. Товар полностью безопасен для здоровья и имеет необходимые сертификаты соответствия качества.\",\"countryOfOrigin\":\"Россия\",\"weight\":0,\"params\":[],\"pictures\":[\"https:\\/\\/vnstatic.net\\/venera\\/big\\/valencia-deluxe-sv05-cream-stan.jpg\"],\"customElements\":[]}\n");


//        queue.forEach(this::processMyQueue);
        for (String q : queue) {
            RequestRabbitMQDataDTO requestRabbitMQDataDTO = objectMapper.readValue(q, RequestRabbitMQDataDTO.class);
            managerOzonService.sendObjToOzon(requestRabbitMQDataDTO);
        }

//        for (String carpet : queue) {
//            RequestRabbitMQDataDTO requestRabbitMQDataDTO = objectMapper.readValue(carpet, RequestRabbitMQDataDTO.class);
//            RequestProductImportOzonDto requestProductImportOzonDto = ozonUtilsService.createRequestProductImportOzonDto(requestRabbitMQDataDTO);
//            Productv2ImportProductsRequestItemDto productv2ImportProductsRequestItemDto = requestProductImportOzonDto.getItems().get(0);
//            Productv2ImportProductsRequestItemDto productImportFromOzonDto = getProductv2ImportProductsRequestItemDtoFromOzon(requestRabbitMQDataDTO.getId());
//            deleteAttributesIgnore(productImportFromOzonDto, productv2ImportProductsRequestItemDto);
//            boolean equals = productv2ImportProductsRequestItemDto.equals(productImportFromOzonDto);
//
//
//            System.out.println(equals);
//        }

    }

    private Productv2ImportProductsRequestItemDto getProductv2ImportProductsRequestItemDtoFromOzon(String article) {
        ResponseProductInfoOzonDTO block = ozonApiService.getInfoItemFromOzon(article).block();
        return getProductv2ImportProductsRequestItemDtoFromResponseOzon(block);
    }

    private void deleteAttributesIgnore(Productv2ImportProductsRequestItemDto... productsDto) {
        for (Productv2ImportProductsRequestItemDto productDto : productsDto) {

            Set<Productv2ImportProductsRequestAttributeDto> attributes = productDto.getAttributes();
            List<Integer> ignoreIdList = Arrays.asList(20900, 4180, 4497);
            Set<Productv2ImportProductsRequestAttributeDto> editAttributes = attributes
                    .stream()
                    .filter(dto -> !ignoreIdList.contains(dto.getId()))
                    .collect(Collectors.toSet());
            productDto.setAttributes(editAttributes);
        }
    }


    private Productv2ImportProductsRequestItemDto getProductv2ImportProductsRequestItemDtoFromResponseOzon(ResponseProductInfoOzonDTO block) {
        ResultProductInfoOzonDTO result = block.getResult();
        Productv2ImportProductsRequestItemDto productv2ImportProductsRequestItemDto = new Productv2ImportProductsRequestItemDto();
        productv2ImportProductsRequestItemDto.setBarcode(result.getBarcode());
        productv2ImportProductsRequestItemDto.setOldPrice(result.getOldPrice().split("\\.")[0]);
        productv2ImportProductsRequestItemDto.setPrice(result.getPrice().split("\\.")[0]);
        productv2ImportProductsRequestItemDto.setOfferId(result.getOfferId());
        productv2ImportProductsRequestItemDto.setName(result.getName());

        List<String> productIdList = Arrays.asList(String.valueOf(result.getId()));

        FilterProductsInfoAttributesOzonDTO filterProductsInfoAttributesOzonDTO = new FilterProductsInfoAttributesOzonDTO(productIdList, "ALL");
        RequestProductsInfoAttributesOzonDTO requestProductsInfoAttributesOzonDTO =
                new RequestProductsInfoAttributesOzonDTO(filterProductsInfoAttributesOzonDTO, 100, Strings.EMPTY, Strings.EMPTY);

        ResponseProductsInfoAttributesOzonDTO responseProductsInfoAttributesOzonDTO = ozonApiService.getInfoAttributes(requestProductsInfoAttributesOzonDTO).block();
        ProductsInfoAttributesOzonDTO infoAttributesOzonDTO = responseProductsInfoAttributesOzonDTO.getResult().get(0);
        productv2ImportProductsRequestItemDto.setAttributes(infoAttributesOzonDTO.getAttributes());

        productv2ImportProductsRequestItemDto.setHeight(infoAttributesOzonDTO.getHeight() / 10);
        productv2ImportProductsRequestItemDto.setDepth(infoAttributesOzonDTO.getDepth() / 10);
        productv2ImportProductsRequestItemDto.setWidth(infoAttributesOzonDTO.getWidth() / 10);

        productv2ImportProductsRequestItemDto.setWeight(infoAttributesOzonDTO.getWeight() / 1000);


        return productv2ImportProductsRequestItemDto;
    }


    public void processMyQueue(String message) {
        logger.info("Достал объект из ArrayList \n {}", message);
        RequestRabbitMQDataDTO requestRabbitMQDataDTO = new RequestRabbitMQDataDTO();
        try {
            Thread.sleep(10000);
            requestRabbitMQDataDTO = objectMapper.readValue(message, RequestRabbitMQDataDTO.class);
            RequestProductImportOzonDto requestProductImportOzonDto = ozonUtilsService.createRequestProductImportOzonDto(requestRabbitMQDataDTO);

        } catch (JsonProcessingException e) {
            logger.error("Десерелизация из ArrayList не прошла успешно", e);
        } catch (InterruptedException e) {
            logger.error("Ошибка потока", e);
        }
        managerOzonService.sendObjToOzon(requestRabbitMQDataDTO);

    }

//    public void processDelete() {
//        RequestProductListOzonDTO.Filter filter = new RequestProductListOzonDTO.Filter("BANNED");
//        RequestProductListOzonDTO requestProductListOzonDTO = new RequestProductListOzonDTO(filter,"",100);
//
//        logger.info("Request список на на товары в архиве :\n {} ",appUtilsService.tryBlockAndParsToJsonString(requestProductListOzonDTO));
//        ResponseProductListOzonDTO block = ozonApiService.getListCarpetFromOzon(requestProductListOzonDTO).block();
//        logger.info("Response список на на товары в архиве :\n {} ",appUtilsService.tryBlockAndParsToJsonString(block));
//        List<ResponseProductListOzonDTO.Item> items = block.getResult().getItems();
//        List<RequestProductsDeleteOzonDTO.ProductsDeleteOzonDTO> list = new ArrayList<>();
//        for (ResponseProductListOzonDTO.Item item : items) {
//            RequestProductsDeleteOzonDTO.ProductsDeleteOzonDTO productsDeleteOzonDTO = new RequestProductsDeleteOzonDTO.ProductsDeleteOzonDTO(item.getOfferId());
//            list.add(productsDeleteOzonDTO);
//        }
//        logger.info("Request список на удаление :\n {} ",appUtilsService.tryBlockAndParsToJsonString(list));
//        ResponseProductsDeleteOzonDTO responseProductsDeleteOzonDTO = ozonApiService.deleteCarpetFromArchive(list).block();
//        logger.info("Response Удаление :\n {} ",appUtilsService.tryBlockAndParsToJsonString(responseProductsDeleteOzonDTO));
//    }


}
