package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GetUploadQuotaDailyCreateDto {

    private int limit; //Сколько всего товаров можно создать в сутки.
    private String resetAt; //Время в формате UTC, когда сбросится значение счётчика за текущие сутки.
    private int usage; //Сколько товаров создано за текущие сутки.

}
