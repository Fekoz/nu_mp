package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseProductArchiveOzonDto {
    //Результат обработки запроса. true, если запрос выполнен без ошибок.
    private boolean result;
}
