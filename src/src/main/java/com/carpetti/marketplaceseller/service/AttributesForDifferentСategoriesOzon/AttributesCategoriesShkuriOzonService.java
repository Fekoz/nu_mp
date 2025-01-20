package com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestAttributeDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestDictionaryValueDto;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class AttributesCategoriesShkuriOzonService extends AttributeOzonService {

    private static final int CATEGORY_ID = 22825259;
    private static final String NAME_CATEGORY = "Натуральная шкура";

    @Override
    protected Set<Productv2ImportProductsRequestAttributeDto> attributesCategorise(RequestRabbitMQDataDTO dto) {
        return getStandardAttributes(dto);
    }

    @Override
    protected Productv2ImportProductsRequestAttributeDto getAttrTypeCarpet(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(93666, "Шкура"));

        return new Productv2ImportProductsRequestAttributeDto(0, TYPE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    @Override
    public int getCategoryId() {
        return CATEGORY_ID;
    }

    @Override
    public String getNameCategory() {
        return NAME_CATEGORY;
    }

}
