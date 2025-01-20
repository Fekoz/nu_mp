package com.carpetti.marketplaceseller.dto.rabbitmq;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.DataLifetimeDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Getter
@NoArgsConstructor
@ToString
@AllArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestRabbitMQDataDTO {


    /**
     * Общие характеристики товара
     */
    private RequestRabbitMQCharacterDTO character;

    /**
     * Общее название карточек для связанных товаров
     */
    private String linkedName;

    /**
     * 	Фактические размеры ковра
     */
    private RequestRabbitMQStatisticDTO statCarpet;

    /**
     * Массив названий категорий
     */
    private List<String> categoriesName;

    /**
     * Массив ID товаров, связанных с этим товаром
     */
    private List<String> linkedItemIds;

    /**
     * Объект цен для всех площадок
     */
    private RequestRabbitMQPriceDTO priceToMarket;

    /**
     * ID Товара в нашем магазине
     */
    private int productId;

    /**
     * Артикул товара
     */
    private String productArticle;

    /**
     * UUID товара в магазине (product.uuid)
     */
    private String productUuid;

    /**
     * UUID цены товара в магазине (price.uuid) [Информационное поле]
     */
    private String priceUuid;

    /**
     * MID цены товара в магазине (price.mid) [Чистый ID Для маркетплейсов]
     */
    private String priceMid;

    /**
     * Тип ковра (true - круглый; false - нет;)
     */
    private boolean circle;

    /**
     * Количество товара на складе (общее)
     */
    private int priceCount;

    /**
     * Параметры товара
     */
    private List<RequestRabbitMQParamsDTO> productParams;

    /**
     * Баркод
     */
    private String productEan;

    /**
     * Размеры ковра в упаковке
     */
    private RequestRabbitMQStatisticDTO statDimension;

    /**
     * Количество товара в упакевке при продаже (шаг продажи)
     */
    private int quantity;

    /**
     * Название ковра
     */
    private String name;

    /**
     * Бренд товара
     */
    private String vendor;

    /**
     * Артикул бренда (Равен артикулу товара)
     */
    private String vendorCode;

    /**
     * id Товара
     */
    private String id;

    /**
     * Фактическое отображения на маркетплейсах (true - круглый; false - нет;)
     */
    private boolean available;

    /**
     * Ссылка на товар в магазине
     */
    private String url;


    /**
     * Валюта [Информационное поле]
     */
    private String currencyId;

    /**
     * Массив id категорий
     */
    private List<Integer> categoriesId;

    /**
     * Условия доставки [Информационное поле]
     */
    private List<String> deliveryOptions;

    /**
     * Описание товара
     */
    private String description;

    /**
     * Страна производитель
     */
    private String countryOfOrigin;

    /**
     * Вес ковра
     */
    private double weight;

    /**
     * Дополнительные параметры [Информационное поле]
     */
    private List<String> params;

    /**
     * Массив фотографий товаров. Основное фото - 1-ое.
     */
    private List<String> pictures;

    /**
     * Дополнительные параметры [Информационное поле]
     */
    private List<String> customElements;

    private long number;
    private long price;
    private long oldPrice;
    private long[] linkedItemNumbers;
    private DataLifetimeDTO liveDate;

}
