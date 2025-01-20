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
public class PriceIndexesIndexDataSelfDto {

    // Минимальная цена вашего товара на других площадках
    private String minimalPrice;
    // Валюта цены
    private String minimalPriceCurrency;
    // Значение индекса цены
    private int priceIndexValue;
}
