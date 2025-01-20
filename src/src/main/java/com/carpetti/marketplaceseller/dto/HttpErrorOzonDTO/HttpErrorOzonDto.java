package com.carpetti.marketplaceseller.dto.HttpErrorOzonDTO;

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
public class HttpErrorOzonDto extends Throwable{

    private int code; //Код ошибки.
    private List<HttpErrorDetailOzonDto> details; //Дополнительная информация об ошибке..
    private String message; //Описание ошибки.

}
