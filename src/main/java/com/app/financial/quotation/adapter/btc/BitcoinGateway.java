package com.app.financial.quotation.adapter.btc;

import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import com.app.financial.quotation.infrastructure.config.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

public class BitcoinGateway {

    private RestTemplate restTemplate = new RestTemplate();


    public QuoteResponseHgBrasil getQuotationBitcoin(){
        URI uri = UriComponentsBuilder
                .fromHttpUrl("http://localhost:8085/hgbrasil/finance")
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, QuoteResponseHgBrasil.class);
    }

}
