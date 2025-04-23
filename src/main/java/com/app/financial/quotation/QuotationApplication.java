package com.app.financial.quotation;

import com.app.financial.quotation.infrastructure.config.PropertiesConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(PropertiesConfiguration.class)
public class QuotationApplication {

	public static void main(String[] args) {
		SpringApplication.run(QuotationApplication.class, args);
	}

}
