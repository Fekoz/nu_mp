package com.carpetti.marketplaceseller.dto.rabbitmq;

import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQStatisticDTO {

    /**
     * 	Глубина [z]
     */
    private int length;

    /**
     * Ширина [x]
     */
    private int width;

    /**
     * 	Высота [y]
     */
    private int height;
}
