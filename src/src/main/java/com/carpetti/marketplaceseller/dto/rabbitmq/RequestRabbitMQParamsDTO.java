package com.carpetti.marketplaceseller.dto.rabbitmq;


import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQParamsDTO {

    /**
     * Ключ характеристики товара
     */
    private String key;

    /**
     * Название характеристики товара
     */
    private String name;

    /**
     * Значение характеристики товара
     */
    private String value;
}
