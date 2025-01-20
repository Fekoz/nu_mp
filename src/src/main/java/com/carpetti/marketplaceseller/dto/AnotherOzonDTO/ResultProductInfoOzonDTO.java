package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResultProductInfoOzonDTO {

    // Номер задания на формирование документов
    private long id;

    // Название
    private String name;

    // Идентификатор товара в системе продавца — артикул
    private String offerId;

    // Штрихкод
    private String barcode;

    // Все штрихкоды товара
    private String[] barcodes;

    // Цена главного предложения на Ozon (Устаревший параметр)
    private String buyboxPrice;

    // Идентификатор категории (Устаревший параметр)
    private long categoryId;

    // Дата и время создания товара
    private String createdAt;

    // Массив ссылок на изображения
    private String[] images;

    // Признак, что у товара есть уценённые аналоги на складе Ozon
    private boolean hasDiscountedItem;

    // Признак, является ли товар уценённым
    private boolean isDiscounted;

    // Остатки уценённого товара на складе Ozon
    private DiscountedStocksOzonDto discountedStocks;

    // Валюта ваших цен
    private String currencyCode;

    // Минимальная цена товара после применения акций
    private String minPrice;

    // Устаревший параметр (Минимальная цена на аналогичный товар на Ozon)
    private String minOzonPrice;

    // Устаревший параметр (Минимальная комиссия)
    private double minValue;

    // Маркетинговый цвет
    private String colorImage;

    // Информация о комиссиях
    private CommissionOzonDto[] commissions;

    // Схема продажи
    private String saleSchema;

    // Обязательная предоплата для товара
    private boolean isPrepayment;

    // Если возможна предоплата
    private boolean isPrepaymentAllowed;

    // Массив изображений 360
    private String[] images360;

    // Признак крупногабаритного товара
    private boolean isKgt;

    // Главное изображение товара
    private String primaryImage;

    // Описание состояния товара
    private StatusOzonDTO status;

    private StocksOzonDTO stocks;

    private String oldPrice;
    private String premiumPrice;
    private String price;

}
