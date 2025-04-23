package com.app.financial.quotation.adapter.b3;

import com.app.financial.quotation.adapter.b3.dto.B3Dto;
import com.app.financial.quotation.infrastructure.config.PropertiesConfiguration;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class B3Gateway {

    private final PropertiesConfiguration propertiesConfiguration;
    private RestTemplate restTemplate = new RestTemplate();

    public B3Gateway(PropertiesConfiguration propertiesConfiguration){
        this.propertiesConfiguration=propertiesConfiguration;
    }


    public B3Dto[] getAllQuotationB3(){
        URI uri = UriComponentsBuilder
                .fromHttpUrl(propertiesConfiguration.getB3())
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, B3Dto[].class);
    }
}
