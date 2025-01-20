package com.carpetti.marketplaceseller.dto.RequestOzonDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
/**
 * Одно из полей должно быть , а так все поля не обязательные
 */
public class RequestProductInfoOzonDTO {

    //Идентификатор товара в системе продавца — артикул.
    private String offerId;

//    //Идентификатор товара.
//    private long productId;

//    //Идентификатор товара в системе Ozon — SKU.
//    private int sku;
}
