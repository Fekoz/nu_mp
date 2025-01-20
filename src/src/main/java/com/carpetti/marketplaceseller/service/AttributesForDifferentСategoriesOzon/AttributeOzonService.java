package com.carpetti.marketplaceseller.service.AttributesForDifferentСategoriesOzon;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestAttributeDto;
import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.Productv2ImportProductsRequestDictionaryValueDto;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQParamsDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RequiredArgsConstructor
@Service
public abstract class AttributeOzonService {

    private static final String WARRANTY_PERIOD = "7 дней";
    private static final int BRAND_ATTRIBUTE_ID = 85;
    private static final int NAME_MODEL_ID = 9048;
    private static final int PACKAGE_SIZE_ID = 4082;
    private static final int NAME_CARPET_ID = 4180;
    private static final int DESCRIPTION_CARPET_ID = 4191;
    private static final int WARRANTY_PERIOD_ID = 4385;
    private static final int PRODUCER_COUNTRY_ID = 4389;
    private static final int FORM_CARPET_ID = 6465;
    private static final int WEIGHT_IN_THE_PACKAGE_ID = 4497;
    private static final int ARTICLE_ID = 9024;
    private static final int WIDTH_CARPET_ID = 6467;
    private static final int LENGTH_CARPET_ID = 6468;
    private static final int PILE_HEIGHT_ID = 6469;
    private static final int COLOR_CARPET_ID = 10096;
    private static final int NAME_COLOR_CARPET_ID = 10097;
    private static final int NUMBER_OF_PACKAGES_ID = 8962;
    private static final int CARPET_MATERIAL_ID = 6383;
    protected static final int TYPE_ID = 8229;


    public Set<Productv2ImportProductsRequestAttributeDto> getListAttributes(RequestRabbitMQDataDTO dto) {
        Set<Productv2ImportProductsRequestAttributeDto> resultAttributes = attributesCategorise(dto);

        resultAttributes.removeIf(Objects::isNull);

        return resultAttributes;
    }

    protected abstract Set<Productv2ImportProductsRequestAttributeDto> attributesCategorise(RequestRabbitMQDataDTO dto);

    protected Set<Productv2ImportProductsRequestAttributeDto> getStandardAttributes(RequestRabbitMQDataDTO dto) {
        Set<Productv2ImportProductsRequestAttributeDto> resultAttributes = new HashSet<>();

        resultAttributes.add(getAttrArticle(dto));
        resultAttributes.add(getAttrNameModel(dto));
        resultAttributes.add(getAttrFormCarpet(dto));
        resultAttributes.add(getAttrNameCarpet(dto));
        resultAttributes.add(getAttrColorCarpet(dto));
        resultAttributes.add(getAttrBrandCarpet(dto));
        resultAttributes.add(getAttrWidthCarpet(dto));
        resultAttributes.add(getAttrLengthCarpet(dto));
        resultAttributes.add(getAttrCarpetMaterial(dto));
        resultAttributes.add(getAttrWarrantyPeriod());
        resultAttributes.add(getAttrNameColorCarpet(dto));
        resultAttributes.add(getAttrProducerCountry(dto));
        resultAttributes.add(getAttrPileHeightCarpet(dto));
        resultAttributes.add(getAttrNumberInPackages(dto));
        resultAttributes.add(getAttrDescriptionCarpet(dto));
        resultAttributes.add(getAttrPackageCarpetSize(dto));
        resultAttributes.add(getAttrWeightInThePackage(dto));
        resultAttributes.add(getAttrTypeCarpet(dto));

        return resultAttributes;
    }

    private Productv2ImportProductsRequestAttributeDto getAttrBrandCarpet(RequestRabbitMQDataDTO dto) {

        int brandId = dto.getCharacter().getFabric().getId();
        String brandValue = dto.getCharacter().getFabric().getValue();

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(brandId, brandValue));

        return new Productv2ImportProductsRequestAttributeDto(0, BRAND_ATTRIBUTE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    private Productv2ImportProductsRequestAttributeDto getAttrNameModel(RequestRabbitMQDataDTO dto) {

        String nameModelValue = null;

        for (RequestRabbitMQParamsDTO productParam : dto.getProductParams()) {
            if (productParam.getName().equals("collection")) {
                nameModelValue = productParam.getValue();
            }
        }

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, nameModelValue));

        return new Productv2ImportProductsRequestAttributeDto(0, NAME_MODEL_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    private Productv2ImportProductsRequestAttributeDto getAttrPackageCarpetSize(RequestRabbitMQDataDTO dto) {
        StringBuilder builderValue = new StringBuilder();
        String lengthPackage = String.valueOf(dto.getStatDimension().getLength());
        String widthPackage = String.valueOf(dto.getStatDimension().getWidth());
        String heightPackage = String.valueOf(dto.getStatDimension().getHeight());

        builderValue.append(lengthPackage);
        builderValue.append("x");
        builderValue.append(widthPackage);
        builderValue.append("x");
        builderValue.append(heightPackage);

        String strLengthWidthHeightPackage = builderValue.toString();


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, strLengthWidthHeightPackage));

        return new Productv2ImportProductsRequestAttributeDto(0, PACKAGE_SIZE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    /**
     * //Название пишется по принципу:
     * //Тип + Бренд + Модель (серия + пояснение) + Артикул производителя + , (запятая) + Атрибут
     * //Название не пишется большими буквами (не используем caps lock).
     * //Перед атрибутом ставится запятая. Если атрибутов несколько, они так же разделяются запятыми.
     * //Если какой-то составной части названия нет - пропускаем её.
     * //Атрибутом может быть: цвет, вес, объём, количество штук в упаковке и т.д.
     * //Цвет пишется с маленькой буквы, в мужском роде, единственном числе.
     * //Слово цвет в названии не пишем.
     * //Точка в конце не ставится.
     * //Никаких знаков препинания, кроме запятой, не используем.
     * //Кавычки используем только для названий на русском языке.
     * //Примеры корректных названий:
     * //Смартфон Apple iPhone XS MT572RU
     */
    private Productv2ImportProductsRequestAttributeDto getAttrNameCarpet(RequestRabbitMQDataDTO dto) {
        StringBuilder builderValue = new StringBuilder();

        builderValue.append(getNameCategory());
        builderValue.append(" + ");
        builderValue.append(dto.getCharacter().getFabric().getValue());
        builderValue.append(" + ");
        builderValue.append(" + ");
        builderValue.append(" + ");
        builderValue.append(",");
        builderValue.append(" + ");

        String strNameCarpet = builderValue.toString();

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, strNameCarpet));

        return new Productv2ImportProductsRequestAttributeDto(0, NAME_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    private Productv2ImportProductsRequestAttributeDto getAttrDescriptionCarpet(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, dto.getDescription()));

        return new Productv2ImportProductsRequestAttributeDto(0, DESCRIPTION_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrWarrantyPeriod() {


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, WARRANTY_PERIOD));

        return new Productv2ImportProductsRequestAttributeDto(0, WARRANTY_PERIOD_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    private Productv2ImportProductsRequestAttributeDto getAttrProducerCountry(RequestRabbitMQDataDTO dto) {


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto
                (
                        dto.getCharacter().getCountry().getId(),
                        dto.getCharacter().getCountry().getValue()
                )
        );


        return new Productv2ImportProductsRequestAttributeDto(0, PRODUCER_COUNTRY_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrFormCarpet(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto
                (
                        dto.getCharacter().getForm().getId(),
                        dto.getCharacter().getForm().getValue()
                )
        );

        return new Productv2ImportProductsRequestAttributeDto(0, FORM_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrWeightInThePackage(RequestRabbitMQDataDTO dto) {
        int oneKgInG = 1000;
        int resultForAttr = (int) Math.ceil(dto.getWeight() * oneKgInG);
        String strResultForAttr = String.valueOf(resultForAttr);

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, strResultForAttr)
        );

        return new Productv2ImportProductsRequestAttributeDto(0, WEIGHT_IN_THE_PACKAGE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrArticle(RequestRabbitMQDataDTO dto) {


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, dto.getId())
        );

        return new Productv2ImportProductsRequestAttributeDto(0, ARTICLE_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }

    /**
     * Укажите ширину в метрах.Только цифры!
     *
     * @param dto
     * @return
     * @throws IOException
     */
    private Productv2ImportProductsRequestAttributeDto getAttrWidthCarpet(RequestRabbitMQDataDTO dto) {


        Double widthInMeter = dto.getStatCarpet().getWidth() / 100.0;

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, widthInMeter.toString())
        );

        return new Productv2ImportProductsRequestAttributeDto(0, WIDTH_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrLengthCarpet(RequestRabbitMQDataDTO dto) {


        Double heightInMeter = dto.getStatCarpet().getHeight() / 100.0;

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, heightInMeter.toString())
        );

        return new Productv2ImportProductsRequestAttributeDto(0, LENGTH_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrPileHeightCarpet(RequestRabbitMQDataDTO dto) {

        String typePile = null;

        for (RequestRabbitMQParamsDTO productParam : dto.getProductParams()) {
            if (productParam.getKey().equals("Высота ворса")) {
                typePile = productParam.getValue();
            }
        }

        if(typePile == null){
            return null;
        }

        Integer lengthInMeter =  extractDigits(typePile);

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, lengthInMeter.toString())
        );

        return new Productv2ImportProductsRequestAttributeDto(0, PILE_HEIGHT_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrColorCarpet(RequestRabbitMQDataDTO dto) {


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(
                dto.getCharacter().getColor().getId(),
                dto.getCharacter().getColor().getValue())
        );

        return new Productv2ImportProductsRequestAttributeDto(0, COLOR_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrNameColorCarpet(RequestRabbitMQDataDTO dto) {

        String colour = null;
        for (RequestRabbitMQParamsDTO productParam : dto.getProductParams()) {
            if(productParam.getKey().equals("Код цвета")){
                colour = productParam.getValue();
            }
        }

        if(colour == null){
            return null;
        }


        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, colour));

        return new Productv2ImportProductsRequestAttributeDto(0, NAME_COLOR_CARPET_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrNumberInPackages(RequestRabbitMQDataDTO dto) {
        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(0, String.valueOf(dto.getQuantity())));

        return new Productv2ImportProductsRequestAttributeDto(0, NUMBER_OF_PACKAGES_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    private Productv2ImportProductsRequestAttributeDto getAttrCarpetMaterial(RequestRabbitMQDataDTO dto) {

        List<Productv2ImportProductsRequestDictionaryValueDto> listProductv2ImportProductsRequestDictionaryValueDto
                = Arrays.asList(new Productv2ImportProductsRequestDictionaryValueDto(
                        dto.getCharacter().getTypeBase().getId(), dto.getCharacter().getTypeBase().getValue()));

        return new Productv2ImportProductsRequestAttributeDto(0, CARPET_MATERIAL_ID, listProductv2ImportProductsRequestDictionaryValueDto);
    }


    protected abstract Productv2ImportProductsRequestAttributeDto getAttrTypeCarpet(RequestRabbitMQDataDTO dto);

    protected int extractDigits(String typePile) {

        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher = pattern.matcher(typePile);

        StringBuilder digits = new StringBuilder();

        while (matcher.find()) {
            digits.append(matcher.group());
        }

        return Integer.parseInt(digits.toString());
    }

    public abstract int getCategoryId();
    public abstract String getNameCategory();
}
