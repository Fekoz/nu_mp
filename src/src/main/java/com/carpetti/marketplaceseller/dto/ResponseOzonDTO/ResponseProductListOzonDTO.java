package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.context.annotation.Profile;

import java.util.List;

@Profile("unrabbitmq")
@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseProductListOzonDTO {
    private Result result;

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @AllArgsConstructor
    public static class Result {
        private List<Item> items;
        private int total;
        private String lastId;
    }

    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @AllArgsConstructor
    public static class Item {
        private long productId;
        private String offerId;
        private boolean isFboVisible;
        private boolean isFbsVisible;
        private boolean archived;
        private boolean isDiscounted;
    }
}