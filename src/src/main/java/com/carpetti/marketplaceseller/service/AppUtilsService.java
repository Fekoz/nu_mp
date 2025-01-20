package com.carpetti.marketplaceseller.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;


@Service
public class AppUtilsService {

    private static final Logger logger
            = LoggerFactory.getLogger(AppUtilsService.class);

    private ObjectWriter objectWriter;

    public AppUtilsService(ObjectWriter objectWriter) {
        this.objectWriter = objectWriter;
    }

    public String tryBlockAndParsToJsonString(Object obj) {
        try {
            return objectWriter.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            logger.error("tryBlockAndParsToString Method", e);
            return "";
        }
    }
}
