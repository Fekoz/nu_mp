package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Categoryv2DictionaryValueBatchResponseDictionaryValueOzonDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseCategoryAttributeValues {

    // Массив объектов "ResultItem", представляющих элементы
    private List<Categoryv2DictionaryValueBatchResponseDictionaryValueOzonDto> result;

    // Флаг указывающий, есть ли следующая порция данныхdd
    private boolean hasNext;
}
