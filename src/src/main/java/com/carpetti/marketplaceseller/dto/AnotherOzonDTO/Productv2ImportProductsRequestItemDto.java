package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.*;

import java.util.List;
import java.util.Objects;
import java.util.Set;

@Getter
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
@AllArgsConstructor
@Setter
@ToString(exclude = {"colorImage", "categoryId", "images360", "pdfList", "primaryImage", "images", "vat", "weightUnit", "dimensionUnit"})
public class Productv2ImportProductsRequestItemDto {
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productv2ImportProductsRequestItemDto dto = (Productv2ImportProductsRequestItemDto) o;
        return depth == dto.depth &&
                height == dto.height &&
                weight == dto.weight &&
                width == dto.width &&
                attributes.equals(dto.attributes) &&
                Objects.equals(barcode, dto.barcode) &&
                name.equals(dto.name) &&
                offerId.equals(dto.offerId) &&
                Objects.equals(oldPrice, dto.oldPrice) &&
                Objects.equals(price, dto.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(attributes, barcode, depth, height, name, offerId, oldPrice, premiumPrice, price, weight, width);
    }

    private Set<Productv2ImportProductsRequestAttributeDto> attributes; // Массив с характеристиками товара
    private String barcode; // Штрихкод товара
    private int categoryId; // Идентификатор категории
    private String colorImage; // Маркетинговый цвет
    private List<Object> complexAttributes; // Массив характеристик с вложенными атрибутами
    private String currencyCode; // Валюта цен
    private int depth; // Глубина упаковки
    private String dimensionUnit; // Единица измерения габаритов
    private int height; // Высота упаковки
    private List<String> images; // Массив изображений
    private List<String> images360; // Массив изображений 360
    private String name; // Название товара
    private String offerId; // Идентификатор товара в системе продавца
    private String oldPrice; // Цена до скидок
    private List<Object> pdfList; // Список PDF-файлов
    private String premiumPrice; // Цена для клиентов с подпиской Ozon Premium
    private String price; // Цена товара
    private String primaryImage; // Основная фотография товара
    private String vat; // Ставка НДС для товара
    private int weight; // Вес товара в упаковке
    private String weightUnit; // Единица измерения веса
    private int width; // Ширина упаковки

}
