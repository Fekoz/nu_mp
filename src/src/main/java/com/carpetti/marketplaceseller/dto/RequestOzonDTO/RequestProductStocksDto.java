package com.carpetti.marketplaceseller.dto.RequestOzonDTO;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductV2ProductsStocksRequestStockDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class RequestProductStocksDto {

    private List<ProductV2ProductsStocksRequestStockDto> stocks;
}
