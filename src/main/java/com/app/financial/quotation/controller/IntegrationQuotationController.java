package com.app.financial.quotation.controller;

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
    public void updateQuotation(){
        String valor = "Cansei hj";
    }
}
