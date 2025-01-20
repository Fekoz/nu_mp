package com.carpetti.marketplaceseller.dto.RequestOzonDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("unrabbitmq")
@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class RequestProductsDeleteOzonDTO {


    private List<ProductsDeleteOzonDTO> products;

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @AllArgsConstructor
    public static class ProductsDeleteOzonDTO {
        private String offerId;
    }
}
