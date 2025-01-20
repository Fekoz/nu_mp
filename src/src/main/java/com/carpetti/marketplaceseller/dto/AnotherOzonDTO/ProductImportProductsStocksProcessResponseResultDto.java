package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ErrorSimpleDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ProductImportProductsStocksProcessResponseResultDto {

    private String offerId;
    private int productId;
    private boolean updated;
    private List<ErrorSimpleDto> errors;
}
