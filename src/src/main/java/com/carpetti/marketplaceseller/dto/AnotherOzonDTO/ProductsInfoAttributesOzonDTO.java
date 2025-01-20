package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ProductsInfoAttributesOzonDTO {

    private Integer id;
    private String barcode;
    @Deprecated
    private Long categoryId;
    private String name;
    private String offerId;
    private Integer height;
    private Integer depth;
    private Integer width;
    private String dimensionUnit;
    private Integer weight;
    private String weightUnit;
    private List<ImageOzonDto> images;
    private String imageGroupId;
    private Set<Productv2ImportProductsRequestAttributeDto> attributes;
    private List<Object> complexAttributes; // Тип зависит от содержимого
    private String colorImage;
    private String lastId;
}



