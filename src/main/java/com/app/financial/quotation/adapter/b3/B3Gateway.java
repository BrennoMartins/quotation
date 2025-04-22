package com.app.financial.quotation.adapter.b3;

import com.app.financial.quotation.adapter.b3.dto.B3Dto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class B3Gateway {

    //TODO Corrigir o problema de nao pegar o value
    @Value("${b3.api")
    private String apiUrl = "http://localhost:8086/app/b3/assets";

    private RestTemplate restTemplate = new RestTemplate();


    public B3Dto[] getAllQuotationB3(){
        URI uri = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, B3Dto[].class);
    }
}
