package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ItemPriceDto {


    // Валюта ваших цен
    private String currencyCode;
    // Цена товара с учётом скидок
    private String price;
    // Цена до учёта скидок
    private String oldPrice;
    // Цена для клиентов с подпиской Ozon Premium
    private String premiumPrice;
    // Цена на товар, рекомендованная системой
    private String recommendedPrice;
    // Цена поставщика
    private String retailPrice;
    // Ставка НДС для товара
    private String vat;
    // Минимальная цена на аналогичный товар на Ozon
    private String minOzonPrice;
    // Цена на товар с учетом всех акций
    private String marketingPrice;
    // Цена на товар с учетом акций продавца
    private String marketingSellerPrice;
    // Если автоприменение акций у товара включено — true
    private boolean autoActionEnabled;
}
