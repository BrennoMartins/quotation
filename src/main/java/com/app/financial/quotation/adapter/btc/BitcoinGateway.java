package com.app.financial.quotation.adapter.btc;

import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import com.app.financial.quotation.infrastructure.config.PropertiesConfiguration;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;

@Service
public class BitcoinGateway {

    private final PropertiesConfiguration propertiesConfiguration;
    private RestTemplate restTemplate = new RestTemplate();

    public BitcoinGateway(PropertiesConfiguration propertiesConfiguration){
        this.propertiesConfiguration=propertiesConfiguration;
    }

    public QuoteResponseHgBrasil getQuotationBitcoin(){
        URI uri = UriComponentsBuilder
                .fromHttpUrl(propertiesConfiguration.getHgbrasil())
                .encode()
                .build()
                .toUri();

        return restTemplate.getForObject(uri, QuoteResponseHgBrasil.class);
    }

}
