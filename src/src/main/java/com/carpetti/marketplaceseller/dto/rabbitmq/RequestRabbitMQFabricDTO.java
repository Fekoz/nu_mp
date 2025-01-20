package com.carpetti.marketplaceseller.dto.rabbitmq;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQFabricDTO {
    private int id;
    private String value;
}
