package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class StatusOzonDTO {
    private String state; // Состояние товара.
    private String stateFailed; // Состояние товара, на переходе в которое произошла ошибка.
    private String moderateStatus; // Статус модерации.
    private List<String> declineReasons; // Причины отклонения товара.
    private String validationState; // Статус валидации.
    private String stateName; // Название состояния товара.
    private String stateDescription; // Описание состояния товара.
    private boolean isFailed; // Признак, что при создании товара возникли ошибки.
    private boolean isCreated; // Признак, что товар создан.
    private String stateTooltip; // Подсказки для текущего состояния товара.
    private List<ItemErrorOzonDTO> itemErrors; // Ошибки при загрузке товаров.
    private String stateUpdatedAt; // Время последнего изменения состояния товара.
}
