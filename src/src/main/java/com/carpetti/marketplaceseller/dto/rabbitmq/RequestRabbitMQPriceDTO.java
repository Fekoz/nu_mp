package com.carpetti.marketplaceseller.dto.rabbitmq;


import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
public class RequestRabbitMQPriceDTO {

    /**
     * Финальная цена товара для ОЗ
     */
    private RequestRabbitMQPriceLedDTO ozon ;

    /**
     * Финальная цена товара для ЯМ
     */
    private RequestRabbitMQPriceLedDTO yandex ;

    /**
     * Финальная цена товара для ВБ
     */
    private RequestRabbitMQPriceLedDTO wildberries ;

    /**
     * Цена на магазине
     */
    private RequestRabbitMQPriceLedDTO original ;

    /**
     * Цена после формулы
     */
    private RequestRabbitMQPriceLedDTO reformat ;
}
