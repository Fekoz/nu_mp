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
public class MarketingActionsMarketingActionDto {

    // Дата начала акции
    private String date_from;
    // Дата окончания акции
    private String date_to;
    // Значение скидки
    private String discount_value;
    // Название акции
    private String title;
}
