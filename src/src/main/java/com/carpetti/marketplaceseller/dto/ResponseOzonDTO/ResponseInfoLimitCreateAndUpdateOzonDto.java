package com.carpetti.marketplaceseller.dto.ResponseOzonDTO;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.GetUploadQuotaDailyCreateDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.GetUploadQuotaDailyUpdateDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.GetUploadQuotaTotalDto;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;


@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
public class ResponseInfoLimitCreateAndUpdateOzonDto {


    private GetUploadQuotaDailyCreateDto dailyCreate; //Суточный лимит на создание товаров.
    private GetUploadQuotaDailyUpdateDto dailyUpdate; //Суточный лимит на обновление товаров.
    private GetUploadQuotaTotalDto total; //Лимит на ассортимент.


}
