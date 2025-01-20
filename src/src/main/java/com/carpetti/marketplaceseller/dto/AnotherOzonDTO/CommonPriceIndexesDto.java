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
public class CommonPriceIndexesDto {

    //Цена товара у конкурентов на других площадках.
    private PriceIndexesIndexDataExternalDto externalIndexData;
    //Цена товара у конкурентов на Ozon.
    private PriceIndexesIndexDataOzonDto ozonIndexData;
    // Ценовой индекс
    private String priceIndex;
    //Цена вашего товара на других площадках.
    private PriceIndexesIndexDataSelfDto selfMarketplacesIndexData;
}
