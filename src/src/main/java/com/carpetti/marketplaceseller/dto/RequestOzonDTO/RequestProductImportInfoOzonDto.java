package com.carpetti.marketplaceseller.dto.RequestOzonDTO;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestProductImportInfoOzonDto {


    @JsonProperty("task_id")
    private long taskId; //Код задачи на импорт товаров.
}
