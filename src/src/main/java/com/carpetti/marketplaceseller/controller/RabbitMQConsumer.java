package com.carpetti.marketplaceseller.controller;

import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import com.carpetti.marketplaceseller.service.ManagerOzonService;
import com.carpetti.marketplaceseller.service.OzonApiService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!unrabbitmq")
@EnableRabbit
public class RabbitMQConsumer {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private ManagerOzonService managerOzonService;


    private static final Logger logger
            = LoggerFactory.getLogger(RabbitMQConsumer.class);

    @RabbitListener(queues = "run.send.mp")
    public void processMyQueue(String message) {

        if(message.isBlank() || message.isEmpty()){
            return;
        }

        logger.info("Пришол объект из RabbitMQ");
        RequestRabbitMQDataDTO requestRabbitMQDataDTO;
        try {
            requestRabbitMQDataDTO = objectMapper.readValue(message, RequestRabbitMQDataDTO.class);
        } catch (JsonProcessingException e) {
            logger.error("Кол-во символов в очереди :{}",message.length());
            logger.error("Объект из очереди {}", message);
            logger.error("Десерелизация из очереди провалилась", e);
            return;
        }
        managerOzonService.sendObjToOzon(requestRabbitMQDataDTO);

    }
}