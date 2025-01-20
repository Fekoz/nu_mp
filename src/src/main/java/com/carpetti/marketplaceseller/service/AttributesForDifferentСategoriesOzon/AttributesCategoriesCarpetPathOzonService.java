package com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestAttributeDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestDictionaryValueDto;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import org.springframework.stereotype.Service;


import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Service
public class AttributesCategoriesCarpetPathOzonService extends AttributeOzonService{

    private  static final int STYLE_ID = 9546;
    private static final int CATEGORY_ID = 22825378;
    private static final String NAME_CATEGORY = "Ковровая дорожка";

    @Override
    protected Set<Productv2ImportProductsRequestAttributeDto> attributesCategorise(RequestRabbitMQDataDTO dto) {
        Set<Productv2ImportProductsRequestAttributeDto> standardAttributes = getStandardAttributes(dto);
        standardAttributes.add(getAttrStyle(dto));

        return standardAttributes;
    }

    @Override
    protected Productv2ImportProductsRequestAttributeDto getAttrTypeCarpet(RequestRabbitMQDataDTO dto) {
        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(93662, NAME_CATEGORY));

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

    protected Productv2ImportProductsRequestAttributeDto getAttrStyle(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(dto.getCharacter().getStyles().getId(),
                                                                                    dto.getCharacter().getStyles().getValue()));

        return new Productv2ImportProductsRequestAttributeDto(0, STYLE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

}
