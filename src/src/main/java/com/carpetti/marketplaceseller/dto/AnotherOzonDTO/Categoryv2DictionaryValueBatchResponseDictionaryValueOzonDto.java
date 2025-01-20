package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class Categoryv2DictionaryValueBatchResponseDictionaryValueOzonDto {


    // Идентификатор элемента
    private long id;

    // Значение характеристики товара
    private String value;

    // Дополнительная информация
    private String info;

    // Путь к картинке (изображению)
    private String picture;
}
