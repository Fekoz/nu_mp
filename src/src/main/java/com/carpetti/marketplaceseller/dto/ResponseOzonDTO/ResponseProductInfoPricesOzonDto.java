package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductGetProductInfoPricesV4ResponseResultDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseProductInfoPricesOzonDto {

    // Результаты запроса
    private ProductGetProductInfoPricesV4ResponseResultDto result;
}
