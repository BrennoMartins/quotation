package com.app.financial.quotation.controller;

import com.app.financial.quotation.adapter.btc.BitcoinGateway;
import com.app.financial.quotation.adapter.btc.dto.CurrencyDataResponseHgBrasil;
import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/quotation/integrations")
public class IntegrationQuotationController {


    //TODO Eu vou criar a camada Adpter para bater q pegar a cotação do Bitcoin
    //Esse controller vai chamar o useCase que vai ter o update nas cotações
    //Aqui vamos receber uma lista de cotações
    @PostMapping
    public CurrencyDataResponseHgBrasil updateQuotation(){
        QuoteResponseHgBrasil quoteResponseHgBrasil = new BitcoinGateway().getQuotationBitcoin();
        return quoteResponseHgBrasil.getResults().getCurrencies().getBTC();
    }
}
