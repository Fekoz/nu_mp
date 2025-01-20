package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class GetImportProductsInfoResponseResultItem {

    private String offerId;  // Идентификатор товара в системе продавца — артикул. Максимальная длина строки в значении поля — 50 символов.
    private long productId;  // Идентификатор товара.
    private String status;  // Статус создания товара. Возможные значения: pending, imported, failed.
    private List<ErrorItemOzonDTO> errors;  // Массив ошибок.

}