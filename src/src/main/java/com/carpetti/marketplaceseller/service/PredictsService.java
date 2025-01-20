package com.carpetti.marketplaceseller.service;


import com.carpetti.marketplaceseller.dto.AnotherOzonDTO.*;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductImportOzonDto;
import com.carpetti.marketplaceseller.dto.RequestOzonDTO.RequestProductsInfoAttributesOzonDTO;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductImportInfoOzonDto;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductInfoOzonDTO;
import com.carpetti.marketplaceseller.dto.ResponseOzonDTO.ResponseProductsInfoAttributesOzonDTO;
import com.carpetti.marketplaceseller.dto.rabbitmq.RequestRabbitMQDataDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
public class PredictsService {



    @Autowired
    OzonApiService ozonApiService;

    @Autowired
    OzonUtilsService ozonUtilsService;

    public boolean getIsDeadData(Date dateDeadData) {
        return new Date().after(dateDeadData);
    }

    public boolean isNonCreated(ResponseProductInfoOzonDTO respInfoCarpetFromOzon) {
        boolean isCreated = respInfoCarpetFromOzon.getResult().getStatus().isCreated();

        if (isCreated) {
            return false;
        }
        return true;
    }

    public boolean isKgt(ResponseProductInfoOzonDTO respInfoCarpetFromOzon) {
        return respInfoCarpetFromOzon.getResult().isKgt();
    }

    public boolean IsNotCreatedProductId(ResponseProductImportInfoOzonDto responseProductImportInfoOzonDto) {
        long productIdOnOzon = responseProductImportInfoOzonDto
                .getResult()
                .getItems()
                .get(0)
                .getProductId();

        if (productIdOnOzon == 0) {
            return true;
        }

        return false;
    }


    public boolean equalsProductv2ImportProductsRequestItemDtoPrepareSend(RequestRabbitMQDataDTO dtoFromQueue,
                                                                          RequestProductImportOzonDto requestProductImportOzonDto,
                                                                          ResponseProductInfoOzonDTO responseProductInfoOzonDTO  ){
        if(Objects.isNull(dtoFromQueue)){
            return false;
        }

        Productv2ImportProductsRequestItemDto productv2ImportProductsRequestItemDto = requestProductImportOzonDto.getItems().get(0);
        Productv2ImportProductsRequestItemDto productImportFromOzonDto = getProductv2ImportProductsRequestItemDtoFromResponseOzon(responseProductInfoOzonDTO);
        deleteAttributesIgnore(productImportFromOzonDto, productv2ImportProductsRequestItemDto);
        boolean result = productv2ImportProductsRequestItemDto.equals(productImportFromOzonDto);
        log.info("Обьекты сравнивались и результат такой: {}", result);
        return result;
    }


    private Productv2ImportProductsRequestItemDto getProductv2ImportProductsRequestItemDtoFromResponseOzon(ResponseProductInfoOzonDTO block) {
        ResultProductInfoOzonDTO result = block.getResult();
        Productv2ImportProductsRequestItemDto productv2ImportProductsRequestItemDto = new Productv2ImportProductsRequestItemDto();
        productv2ImportProductsRequestItemDto.setBarcode(result.getBarcode());
        productv2ImportProductsRequestItemDto.setOldPrice(result.getOldPrice().split("\\.")[0]);
        productv2ImportProductsRequestItemDto.setPrice(result.getPrice().split("\\.")[0]);
        productv2ImportProductsRequestItemDto.setOfferId(result.getOfferId());
        productv2ImportProductsRequestItemDto.setName(result.getName());

        List<String> productIdList = Arrays.asList(String.valueOf(result.getId()));

        FilterProductsInfoAttributesOzonDTO filterProductsInfoAttributesOzonDTO = new FilterProductsInfoAttributesOzonDTO(productIdList, "ALL");
        RequestProductsInfoAttributesOzonDTO requestProductsInfoAttributesOzonDTO =
                new RequestProductsInfoAttributesOzonDTO(filterProductsInfoAttributesOzonDTO, 100, Strings.EMPTY, Strings.EMPTY);

        ResponseProductsInfoAttributesOzonDTO responseProductsInfoAttributesOzonDTO = ozonApiService.getInfoAttributes(requestProductsInfoAttributesOzonDTO).block();
        ProductsInfoAttributesOzonDTO infoAttributesOzonDTO = responseProductsInfoAttributesOzonDTO.getResult().get(0);
        Objects.requireNonNull(infoAttributesOzonDTO);
        productv2ImportProductsRequestItemDto.setAttributes(infoAttributesOzonDTO.getAttributes());

        productv2ImportProductsRequestItemDto.setHeight(infoAttributesOzonDTO.getHeight() / 10);
        productv2ImportProductsRequestItemDto.setDepth(infoAttributesOzonDTO.getDepth() / 10);
        productv2ImportProductsRequestItemDto.setWidth(infoAttributesOzonDTO.getWidth() / 10);

        productv2ImportProductsRequestItemDto.setWeight(infoAttributesOzonDTO.getWeight() / 1000);


        return productv2ImportProductsRequestItemDto;
    }

    private void deleteAttributesIgnore(Productv2ImportProductsRequestItemDto... productsDto) {
        for (Productv2ImportProductsRequestItemDto productDto : productsDto) {

            Set<Productv2ImportProductsRequestAttributeDto> attributes = productDto.getAttributes();
            List<Integer> ignoreIdList = Arrays.asList(20900, 4180, 4497);
            Set<Productv2ImportProductsRequestAttributeDto> editAttributes = attributes
                    .stream()
                    .filter(dto -> !ignoreIdList.contains(dto.getId()))
                    .collect(Collectors.toSet());
            productDto.setAttributes(editAttributes);
        }
    }
}
