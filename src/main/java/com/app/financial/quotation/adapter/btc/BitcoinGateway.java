package com.app.financial.quotation.adapter.btc;

import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class BitcoinGateway {

    @Value("${hgbrasil.api")
    private String apiUrl= "http://localhost:8085/hgbrasil/finance";

    private RestTemplate restTemplate = new RestTemplate();


    public QuoteResponseHgBrasil getQuotationBitcoin(){
        URI uri = UriComponentsBuilder
                .fromHttpUrl(apiUrl)
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, QuoteResponseHgBrasil.class);
    }

}
