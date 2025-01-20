package com.carpetti.marketplaceseller.service;


import com.carpetti.marketplaceseller.dto.HttpErrorOzonDTO.HttpErrorOzonDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.*;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.*;
import io.netty.handler.ssl.SslContextBuilder;
import io.netty.handler.ssl.util.InsecureTrustManagerFactory;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.client.reactive.ReactorClientHttpConnector;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.netty.http.client.HttpClient;

import java.util.List;


/**
 * The type Ozon api service.
 */
@Service
public class OzonApiService {

    @Value("${ozon.client.id}")
    private String clientId;

    @Value("${ozon.token}")
    private String token;

    private WebClient webClient;
    private static final Logger logger
            = LoggerFactory.getLogger(OzonApiService.class);

    /**
     * Init.
     */
    @PostConstruct
    public void init() {
        // Создаем SSL контекст, который принимает все сертификаты (не рекомендуется использовать в продакшене)
        HttpClient httpClient = HttpClient.create()
                .secure(sslContextSpec -> sslContextSpec.sslContext(
                        SslContextBuilder.forClient().trustManager(InsecureTrustManagerFactory.INSTANCE)
                ));

        // Используем созданный HttpClient для создания WebClient
        webClient = WebClient.builder()
                .clientConnector(new ReactorClientHttpConnector(httpClient))
                .baseUrl("https://api-seller.ozon.ru")
                .filters(exchangeFilterFunctions -> {
                    exchangeFilterFunctions.add(logRequest());
                    exchangeFilterFunctions.add(logResponse());
                })
                .defaultHeaders(httpHeaders -> {
                    httpHeaders.add("Client-Id", clientId);
                    httpHeaders.add("Api-Key", token);
                })
                .build();
    }

    /**
     * Лимиты на ассортимент, создание и обновление товаров
     * Метод для получения информации о лимитах:
     * <p>
     * На ассортимент — сколько всего товаров можно создать в вашем личном кабинете.
     * На создание товаров — сколько товаров можно создать в сутки.
     * На обновление товаров — сколько товаров можно отредактировать в сутки.
     * Если у вас есть лимит на ассортимент и вы израсходуете его, вы не сможете создавать новые товары.
     *
     * @return the limit create offers
     */
    public Mono<ResponseInfoLimitCreateAndUpdateOzonDto> getLimitCreateOffers() {
        return webClient
                .post()
                .uri("/v4/product/info/limit")
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseInfoLimitCreateAndUpdateOzonDto.class);
    }


    public Mono<ResponseProductsDeleteOzonDTO> deleteCarpetFromArchive(List<RequestProductsDeleteOzonDTO.ProductsDeleteOzonDTO> offerIds) {
        return webClient
                .post()
                .uri("/v2/products/delete")
                .bodyValue(new RequestProductsDeleteOzonDTO(offerIds))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductsDeleteOzonDTO.class);
    }

    public Mono<ResponseProductListOzonDTO> getListCarpetFromOzon(RequestProductListOzonDTO dto) {
        return webClient
                .post()
                .uri("/v2/product/list")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductListOzonDTO.class);
    }

    /**
     * Gets info offer.
     *
     * @param taskId the task id
     * @return the info offer
     */
    public Mono<ResponseProductImportInfoOzonDto> getInfoOffer(long taskId) {
        return webClient
                .post()
                .uri("/v1/product/import/info")
                .bodyValue(new RequestProductImportInfoOzonDto(taskId))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductImportInfoOzonDto.class);
    }

    public Mono<ResponseProductsStocksOzonDTO> updateQuantityInWarehouses(RequestProductStocksDto dto) {
        return webClient
                .post()
                .uri("/v2/products/stocks")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductsStocksOzonDTO.class);
    }


    public Mono<ResponseProductImportOzonDto> createOrUpdateCarpet(RequestProductImportOzonDto requestDto) {
        // Логируем тело запроса
        return webClient
                .post()
                .uri("/v2/product/import")
                .bodyValue(requestDto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductImportOzonDto.class);
    }

    public Mono<ResponseProductsInfoAttributesOzonDTO> getInfoAttributes(RequestProductsInfoAttributesOzonDTO dto) {
        return webClient
                .post()
                .uri("/v3/products/info/attributes")
                .bodyValue(dto)
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductsInfoAttributesOzonDTO.class);
    }


    public Mono<ResponseProductInfoOzonDTO> getInfoItemFromOzon(String article) {
        // Логируем тело запроса
        return webClient
                .post()
                .uri("/v2/product/info")
                .bodyValue(new RequestProductInfoOzonDTO(article))
                .retrieve()
                .onStatus(HttpStatusCode::is4xxClientError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse -> clientResponse.bodyToMono(HttpErrorOzonDto.class))
                .bodyToMono(ResponseProductInfoOzonDTO.class);
    }


    ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {

            StringBuilder sb = new StringBuilder("Request: \n");
            //append clientRequest method and url

            sb.append(clientRequest.method()).append(" ").append(clientRequest.url());
            logger.info(sb.toString());
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            StringBuilder sb = new StringBuilder();
            //append clientRequest method and url
            sb.append("Status Code Response: ").append(clientResponse.statusCode());
            logger.info(sb.toString());
            return Mono.just(clientResponse);
        });
    }

}