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
public class ErrorItemOzonDTO {

    private String code;  // Код ошибки.
    private String message;  // Техническое описание ошибки.
    private String state;  // Состояние товара, в котором произошла ошибка.
    private String level;  // Уровень ошибки.
    private String description;  // Описание ошибки.
    private String field;  // Поле, в котором произошла ошибка.
    private long attributeId;  // Атрибут, в котором произошла ошибка.
    private String attributeName;  // Название атрибута, в котором произошла ошибка.
    private AdditionalPropertyForFieldsErrorOzonDto additionalProperty;  // Дополнительные поля для описания ошибки.

}
