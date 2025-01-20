package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ProductsInfoAttributesOzonDTO;
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
public class ResponseProductsInfoAttributesOzonDTO {

    List<ProductsInfoAttributesOzonDTO> result;
}
