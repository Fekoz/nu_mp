package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductV4FilterDto {


    // Фильтр по параметру offer_id. Можно передавать список значений.
    private List<String> offerId;
    // Фильтр по параметру product_id. Можно передавать до 1000 значений.
    private List<Long> productId;
    // Фильтр по видимости товара (см. комментарий ниже)
    private String visibility;

}
