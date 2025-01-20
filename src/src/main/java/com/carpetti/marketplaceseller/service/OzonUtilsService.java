package com.carpetti.marketplaceseller.service;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductV2ProductsStocksRequestStockDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestAttributeDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestItemDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductImportOzonDto;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon.AttributeOzonService;
import com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon.AttributesCategoriesCarpetOzonService;
import com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon.AttributesCategoriesCarpetPathOzonService;
import com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon.AttributesCategoriesChildrenCarpetOzonService;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class OzonUtilsService {
    private static final Logger logger
            = LoggerFactory.getLogger(OzonUtilsService.class);

    public OzonUtilsService(AttributesCategoriesCarpetOzonService attributesCategoriesCarpetOzonService, AttributesCategoriesCarpetPathOzonService attributesCategoriesCarpetPathOzonService, AttributesCategoriesChildrenCarpetOzonService attributesCategoriesChildrenCarpetOzonService) {
        this.attributesCategoriesCarpetOzonService = attributesCategoriesCarpetOzonService;
        this.attributesCategoriesCarpetPathOzonService = attributesCategoriesCarpetPathOzonService;
        this.attributesCategoriesChildrenCarpetOzonService = attributesCategoriesChildrenCarpetOzonService;
    }

    private final AttributesCategoriesCarpetOzonService attributesCategoriesCarpetOzonService;
    private final AttributesCategoriesCarpetPathOzonService attributesCategoriesCarpetPathOzonService;
    private final AttributesCategoriesChildrenCarpetOzonService attributesCategoriesChildrenCarpetOzonService;

    private static final String COLOR_IMAGE = "";
    private static final List<Object> COMPLEX_ATTRIBUTES = Collections.emptyList();
    private static final List<Object> PDF_LIST = Collections.emptyList();
    private static final List<String> IMAGE_360 = Collections.emptyList();
    private static final String CURRENCY_CODE = "RUB";
    private static final String DIMENSION_UNIT = "cm";
    private static final String VAT = "0";
    private static final String WEIGHT_UNIT = "kg";


    public RequestProductImportOzonDto createRequestProductImportOzonDto(RequestRabbitMQDataDTO dto) {
        if (dto.getCharacter().getStyles().getValue().equals("Детский")) {
            return new RequestProductImportOzonDto(createListProductv2ImportProductsRequestItemDto(dto, attributesCategoriesChildrenCarpetOzonService));
        }

        if (dto.getCharacter().getStyles().getValue().equals("Ковровая дорожка")) {
            return new RequestProductImportOzonDto(createListProductv2ImportProductsRequestItemDto(dto, attributesCategoriesCarpetPathOzonService));
        }

        return new RequestProductImportOzonDto(createListProductv2ImportProductsRequestItemDto(dto, attributesCategoriesCarpetOzonService));
    }
//todo реализовать
//    public RequestProductImportOzonDto createRequestProductImportOzonDto(ResponseProductInfoOzonDTO dto,) {
//
//        return new RequestProductImportOzonDto(createListProductv2ImportProductsRequestItemDto(dto, attributesCategoriesCarpetOzonService));
//    }

    private List<Productv2ImportProductsRequestItemDto> createListProductv2ImportProductsRequestItemDto(RequestRabbitMQDataDTO dto, AttributeOzonService categorise) {
        final String BARCODE = dto.getProductEan();
        final String PRICE = String.valueOf((int) Math.ceil(dto.getPriceToMarket().getOzon().getCurrent()));
        final String OLD_PRICE = String.valueOf((int) Math.ceil(dto.getPriceToMarket().getOzon().getOld()));
        final String PREMIUM_PRICE = PRICE;
        final int WEIGHT = (int) Math.ceil(dto.getWeight());
        String primaryImage = Strings.EMPTY;
        if(dto.getPictures().size()>0){
           primaryImage = dto.getPictures().get(0);
            dto.getPictures().remove(0);
        }

        final List<String> images = dto.getPictures();
        final Set<Productv2ImportProductsRequestAttributeDto> attributes = categorise.getListAttributes(dto);


        Productv2ImportProductsRequestItemDto productv2ImportProductsRequestItemDto
                = new Productv2ImportProductsRequestItemDto(
                attributes,
                BARCODE,
                categorise.getCategoryId(),
                COLOR_IMAGE,
                COMPLEX_ATTRIBUTES,
                CURRENCY_CODE,
                dto.getStatDimension().getLength(),
                DIMENSION_UNIT,
                dto.getStatDimension().getHeight(),
                images,
                IMAGE_360,
                dto.getName(),
                dto.getId(),
                OLD_PRICE,
                PDF_LIST,
                PREMIUM_PRICE,
                PRICE,
                primaryImage,
                VAT,
                WEIGHT,
                WEIGHT_UNIT,
                dto.getStatDimension().getWidth()
        );


        return Arrays.asList(productv2ImportProductsRequestItemDto);
    }

    /**
     *
     * @param offerId
     * @param productId
     * @param stock
     * @return ProductV2ProductsStocksRequestStockDto
     */
    public ProductV2ProductsStocksRequestStockDto createObjProductV2ProductsStocksRequestStockDto(String offerId, long productId, int stock, long wareHouseId){
        return new ProductV2ProductsStocksRequestStockDto(
                offerId,
                productId,
                stock,
                wareHouseId);
    }

    public Date parseDate(String dateString) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(dateString);
        } catch (ParseException e) {
            logger.error("Ошибка парсинга даты",e);
            return new Date(1L);
        }
    }
}
