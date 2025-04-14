package com.app.financial.quotation.controller;

import com.app.financial.quotation.usecase.IntegrationQuotationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("app/quotation/integrations")
public class IntegrationQuotationController {

    @Autowired
    private IntegrationQuotationUseCase integrationQuotationUseCase;

    @PostMapping
    public ResponseEntity<String> updateQuotation() {
        integrationQuotationUseCase.updateAutomaticQuotation();
        return ResponseEntity.created(null).body("Executado com sucesso");
    }
}
