package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ImportProductsResponseResultDTO;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseProductImportOzonDto {

    private ImportProductsResponseResultDTO result; // Объект "result" содержит результаты запроса
}
