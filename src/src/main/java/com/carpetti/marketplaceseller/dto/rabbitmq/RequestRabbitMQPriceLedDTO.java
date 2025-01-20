package com.carpetti.marketplaceseller.dto.rabbitmq;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQPriceLedDTO {

    /**
     * Цена до скидки
     */
    double old;

    /**
     * Цена после скидки
     */
    double current;
}
