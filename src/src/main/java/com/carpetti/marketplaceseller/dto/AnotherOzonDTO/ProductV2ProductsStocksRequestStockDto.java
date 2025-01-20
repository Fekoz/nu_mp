package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ProductV2ProductsStocksRequestStockDto {

    private String offerId; //Идентификатор товара в системе продавца — артикул.
    private long productId; //Идентификатор товара.
    private int stock; //Количество.
    private long warehouseId; //Идентификатор склада, полученный из метода /v1/warehouse/list.

}
