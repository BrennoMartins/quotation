package com.app.financial.quotation.controller;

import com.app.financial.quotation.adapter.btc.BitcoinGateway;
import com.app.financial.quotation.adapter.btc.dto.CurrencyDataResponseHgBrasil;
import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import com.app.financial.quotation.adapter.btc.exception.HandlerExceptionBTC;
import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.usecase.QuotationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("app/quotation/integrations")
public class IntegrationQuotationController {

    @Autowired
    private QuotationUseCase quotationUseCase;

    //TODO Buscar os dados do BTC no Banco de dados e só alterar o valor
    //TODO Buscar apenas os que sao automaticos
    //TODO Fazer a validação se tem automatico buscar caso nao setar zerado

    @PostMapping
    public ResponseEntity<String> updateQuotation() {
        QuoteResponseHgBrasil quoteResponseHgBrasil = new BitcoinGateway().getQuotationBitcoin();

        Quotation quotation = new Quotation();
        quotation.setId(9L);
        quotation.setName("BTC");
        quotation.setValue(BigDecimal.valueOf(quoteResponseHgBrasil.getResults().getCurrencies().getBTC().getSell()));
        quotation.setAutomaticUpdateValue(true);

        quotationUseCase.updateQuotation(quotation);
        return ResponseEntity.created(null).body("Executado com sucesso");

    }
}
