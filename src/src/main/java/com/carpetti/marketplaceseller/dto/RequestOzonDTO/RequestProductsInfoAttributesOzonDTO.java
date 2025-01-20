package com.carpetti.marketplaceseller.dto.RequestOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.FilterProductsInfoAttributesOzonDTO;
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
public class RequestProductsInfoAttributesOzonDTO {

    private FilterProductsInfoAttributesOzonDTO filter;
    private int limit;
    private String lastId;
    private String sortDir;

}
