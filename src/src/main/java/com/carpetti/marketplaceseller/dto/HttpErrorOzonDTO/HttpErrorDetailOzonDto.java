package com.carpetti.marketplaceseller.dto.HttpErrorOzonDTO;


import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class HttpErrorDetailOzonDto {

    private String typeUrl; //Тип протокола передачи данных.
    private String value; //Значение ошибки.
}
