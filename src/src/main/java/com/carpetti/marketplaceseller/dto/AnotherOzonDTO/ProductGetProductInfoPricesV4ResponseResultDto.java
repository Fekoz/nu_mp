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
public class ProductGetProductInfoPricesV4ResponseResultDto {

    // Список товаров
    private ProductGetProductInfoPricesV4ResponseItemDto[] items;
    // Количество товаров в списке
    private int total;
    // Идентификатор последнего значения на странице
    private String lastId;
}
