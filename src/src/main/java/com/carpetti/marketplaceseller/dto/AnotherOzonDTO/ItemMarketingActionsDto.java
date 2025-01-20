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
public class ItemMarketingActionsDto {

    // Маркетинговые акции продавца
    private MarketingActionsMarketingActionDto[] actions;
    // Дата и время начала текущего периода по всем действующим акциям
    private String current_period_from;
    // Дата и время окончания текущего периода по всем действующим акциям
    private String current_period_to;
    // Если к товару может быть применена акция за счёт Ozon
    private boolean ozon_actions_exist;
}
