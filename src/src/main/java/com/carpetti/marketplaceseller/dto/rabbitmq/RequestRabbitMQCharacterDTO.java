package com.carpetti.marketplaceseller.dto.rabbitmq;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;


@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQCharacterDTO {
    private RequestRabbitMQTypeCarpetDTO typeCarpet;
    private RequestRabbitMQTypeBaseDTO typeBase;
    private RequestRabbitMQOtherDTO other;
    private RequestRabbitMQStylesDTO styles;
    private RequestRabbitMQFabricDTO fabric;
    private RequestRabbitMQFormDTO form;
    /**
     * Страна
     */
    private RequestRabbitMQCountryDTO country;

    /**
     * Код цвета
     */
    private RequestRabbitMQColorDTO color;

}
