package com.carpetti.marketplaceseller.dto.RequestOzonDTO;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductV4FilterDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class RequestProductInfoPricesDto {




    // Фильтр по товарам.
    private ProductV4FilterDto filter;
    // Идентификатор последнего значения на странице.
    // Оставьте это поле пустым при выполнении первого запроса.
    // Чтобы получить следующие значения, укажите last_id из ответа предыдущего запроса.
    private String lastId;
    // Количество значений на странице. Минимум — 1, максимум — 1000.
    private int limit;

}
