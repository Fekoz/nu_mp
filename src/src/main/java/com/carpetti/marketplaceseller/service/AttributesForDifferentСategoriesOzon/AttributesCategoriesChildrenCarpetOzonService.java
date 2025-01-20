package com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon;

import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestAttributeDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestDictionaryValueDto;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Set;


@Service
public class AttributesCategoriesChildrenCarpetOzonService extends AttributeOzonService {

    private static final int MIN_AGE_CHILD_ID = 13214;
    private static final int MAX_AGE_CHILD_ID = 13215;
    private static final int CHILD_GENDER_ID = 13216;
    private static final int CATEGORY_ID = 22825384;
    private static final String NAME_CATEGORY = "Ковер детский";


    @Override
    protected Set<Productv2ImportProductsRequestAttributeDto> attributesCategorise(RequestRabbitMQDataDTO dto) {
        Set<Productv2ImportProductsRequestAttributeDto> standardAttributes = getStandardAttributes(dto);
        standardAttributes.add(getAttributeMinAgeChild());
        standardAttributes.add(getAttributeChildGender());
        standardAttributes.add(getAttributeMaxAgeChild());

        return standardAttributes;
    }

    @Override
    protected Productv2ImportProductsRequestAttributeDto getAttrTypeCarpet(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(97179, "Коврик для детской"));

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

    private Productv2ImportProductsRequestAttributeDto getAttributeMinAgeChild() {

        String min_age = "0 месяцев";
        int dictionaryValueId = 971005990;

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(dictionaryValueId, min_age));

        return new Productv2ImportProductsRequestAttributeDto(0, MIN_AGE_CHILD_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttributeMaxAgeChild() {

        String max_age = "До 18 лет";
        int dictionaryValueId = 971005969;

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(dictionaryValueId, max_age));

        return new Productv2ImportProductsRequestAttributeDto(0, MAX_AGE_CHILD_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    //"Укажите пол ребенка, для которого рекомендован товар. Если нет явной гендерной принадлежности - выберите значение \"Унисекс\"",
    private Productv2ImportProductsRequestAttributeDto getAttributeChildGender() {

        int dictionaryValueId = 971006037;
        String genderAll = "Унисекс";
        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(dictionaryValueId, genderAll)
        );

        return new Productv2ImportProductsRequestAttributeDto(0, CHILD_GENDER_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


}
