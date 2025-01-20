package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductGetProductInfoPricesV4ResponseItemDto {


    // Идентификатор товара
    private long productId;
    // Идентификатор товара в системе продавца
    private String offerId;
    // Цена товара
    private ItemPriceDto price;
    // Ценовые индексы товара
    private CommonPriceIndexesDto priceIndexes;
    // Информация о комиссиях
    private ItemCommissionsDto commissions;
    // Информация о маркетинговых акциях
    private ItemMarketingActionsDto marketingActions;
    // Объёмный вес товара
    private double volumeWeight;
}
