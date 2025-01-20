package com.carpetti.marketplaceseller.dto.RequestOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestItemDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@EqualsAndHashCode
public class RequestProductImportOzonDto {

    private List<Productv2ImportProductsRequestItemDto> items;  // Массив объектов "items"
}
