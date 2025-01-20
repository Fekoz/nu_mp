package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ItemErrorOzonDTO {
    private String code; // Код ошибки.
    private String state; // Состояние товара, в котором произошла ошибка.
    private String level; // Уровень ошибки.
    private String description; // Описание ошибки.
    private String field; // Поле, в котором произошла ошибка.
    private int attributeId; // Атрибут, в котором произошла ошибка.
    private String attributeName; // Название атрибута, в котором произошла ошибка.
    private Object optionalDescriptionElements; // Дополнительные поля для описания ошибки.
}