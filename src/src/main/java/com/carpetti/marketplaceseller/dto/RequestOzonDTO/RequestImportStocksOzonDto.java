package com.carpetti.marketplaceseller.dto.RequestOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductImportProductsStocksRequestStockDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class RequestImportStocksOzonDto {

    private List<ProductImportProductsStocksRequestStockDto> stocks;
}
