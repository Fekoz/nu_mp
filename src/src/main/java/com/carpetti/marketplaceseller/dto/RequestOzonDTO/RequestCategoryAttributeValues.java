package com.carpetti.marketplaceseller.dto.RequestOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class RequestCategoryAttributeValues {

    // Идентификатор характеристики
    private long attributeId;

    // Идентификатор категории
    private long categoryId;

    // Язык в ответе: английский (EN), русский (RU)
    // По умолчанию используется русский язык ("DEFAULT")
    private String language;

    // Идентификатор справочника, с которого нужно начать ответ.
    // Если last_value_id — 10, то в ответе будут справочники, начиная с одиннадцатого.
    private long lastValueId;

    // Количество значений в ответе:
    // Максимум — 5000,
    // Минимум — 1.
    private long limit;
}
