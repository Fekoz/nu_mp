package com.carpetti.marketplaceseller.dto.AnotherOzonDTO;


import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
import java.util.Objects;

@Getter
@NoArgsConstructor
@ToString
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Productv2ImportProductsRequestAttributeDto {

    private int complexId; // Идентификатор характеристики, поддерживающей вложенные свойства
    @JsonAlias({"id", "attribute_id"})
    private int id; // Идентификатор характеристики
    private List<Productv2ImportProductsRequestDictionaryValueDto> values; // Массив объектов "values"

    public Productv2ImportProductsRequestAttributeDto(int complexId, int id, List<Productv2ImportProductsRequestDictionaryValueDto> values) {
        this.complexId = complexId;
        this.id = id;
        this.values = values;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Productv2ImportProductsRequestAttributeDto that = (Productv2ImportProductsRequestAttributeDto) o;
        return id == that.id && values.equals(that.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, values);
    }
}
