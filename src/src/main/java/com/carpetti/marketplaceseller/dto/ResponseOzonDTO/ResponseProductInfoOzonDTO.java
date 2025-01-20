package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.ResultProductInfoOzonDTO;
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
public class ResponseProductInfoOzonDTO {

    private ResultProductInfoOzonDTO result;
}
