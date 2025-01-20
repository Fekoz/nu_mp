package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.GetImportProductsInfoResponseResultItem;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResultProductImportInfoOzonDto {

    private List<GetImportProductsInfoResponseResultItem> items;  // Массив объектов (GetImportProductsInfoResponseResultItem). Информация о товарах.
    private int total;  // Идентификатор товара в системе продавца.
}
