package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

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
public class ResponseProductsDeleteOzonDTO {
    private List<Status> status;


    @Getter
    @NoArgsConstructor
    @ToString
    @JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
    @AllArgsConstructor
    public static class Status {
        private String offerId;
        private boolean isDeleted;
        private String error;
    }
}
