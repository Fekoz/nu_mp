package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


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
public class ResultProductStocksDTO {

    /**
     * Идентификатор склада.
     */
    private long warehouseId;

    /**
     * Идентификатор товара.
     */
    private int productId;

    /**
     * Идентификатор товара в системе продавца — артикул.
     */
    private String offerId;

    /**
     * Если запрос выполнен успешно и остатки обновлены — true.
     */
    private boolean updated;

    /**
     * Массив ошибок, которые возникли при обработке запроса.
     */
    private List<ErrorProductStocksOzonDTO> errors;
}
