package com.app.financial.quotation.infrastructure.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;


@Component
@ConfigurationProperties(prefix = "api")
@Getter
@Setter
public class PropertiesConfiguration {
    private String b3;
    private String hgbrasil;
}
