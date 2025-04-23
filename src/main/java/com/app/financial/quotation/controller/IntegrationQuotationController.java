package com.app.financial.quotation.controller;

import com.app.financial.quotation.adapter.b3.B3Gateway;
import com.app.financial.quotation.adapter.b3.dto.B3Dto;
import com.app.financial.quotation.usecase.IntegrationQuotationUseCase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("app/quotation/integrations")
public class IntegrationQuotationController {

    @Autowired
    private IntegrationQuotationUseCase integrationQuotationUseCase;
    @Autowired
    private B3Gateway b3Gateway;

    @PostMapping
    public ResponseEntity<String> updateQuotation() {
        integrationQuotationUseCase.updateAutomaticQuotation();
        return ResponseEntity.created(null).body("Executado com sucesso");
    }

    @GetMapping
    public ResponseEntity<List<B3Dto>> getAllB3() {
        return ResponseEntity.ok(Arrays.stream(b3Gateway.getAllQuotationB3()).toList());
    }


}
