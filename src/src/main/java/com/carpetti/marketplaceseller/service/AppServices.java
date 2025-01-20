package com.carpetti.marketplaceseller.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;
import java.util.stream.Stream;


@Service
public class AppServices {

    private static final Logger logger
            = LoggerFactory.getLogger(AppServices.class);

    @Value("${path.folder.logs:logs}")
    private String pathFolderLogs;

    //Раз в сутки удаляет старые логи
    @Scheduled(fixedRate = 1,timeUnit = TimeUnit.DAYS)
    private void deleteOutdatedLogs() {
        try (Stream<Path> paths = Files.walk(Paths.get(pathFolderLogs))) {
            paths.forEach(path -> {
                boolean isOldLog = path.getFileName().toString().endsWith(".gz");
                if (isOldLog) {
                    try {
                        Files.deleteIfExists(path);
                    } catch (IOException e) {
                        logger.error("Проблемы с удалением старых логов из папки ".concat(pathFolderLogs), e);
                    }
                }
            });
        } catch (IOException e) {
            logger.error("Проблемы с удалением старых логов из папки ".concat(pathFolderLogs), e);
        }
    }
}
