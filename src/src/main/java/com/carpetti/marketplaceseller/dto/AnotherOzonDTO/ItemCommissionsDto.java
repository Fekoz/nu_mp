package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemCommissionsDto {

    // Процент комиссии за продажу (FBO и FBS).
    private int salesPercent;
    // Комиссия за возврат и отмену (FBO).
    private double fboFulfillmentAmount;
    // Магистраль до (FBO)
    private double fboDirectFlowTransMaxAmount;
    // Магистраль от (FBO)
    private double fboDirectFlowTransMinAmount;
    // Последняя миля (FBO)
    private double fboDelivToCustomerAmount;
    // Комиссия за возврат и отмену (FBO).
    private double fboReturnFlowAmount;
    // Комиссия за обратную логистику от (FBO)
    private double fboReturnFlowTransMinAmount;
    // Комиссия за обратную логистику до (FBO)
    private double fboReturnFlowTransMaxAmount;
    // Последняя миля (FBS)
    private double fbsDelivToCustomerAmount;
    // Магистраль до (FBS)
    private double fbsDirectFlowTransMaxAmount;
    // Магистраль от (FBS)
    private double fbsDirectFlowTransMinAmount;
    // Комиссия за обработку отправления от (FBS)
    private double fbsFirstMileMinAmount;
    // Комиссия за обработку отправления до (FBS).
    private double fbsFirstMileMaxAmount;
    // Комиссия за возврат и отмену, обработка отправления (FBS).
    private double fbsReturnFlowAmount;
    // Комиссия за возврат и отмену, магистраль до (FBS).
    private double fbsReturnFlowTransMaxAmount;
    // Комиссия за возврат и отмену, магистраль от (FBS).
    private double fbsReturnFlowTransMinAmount;
}
