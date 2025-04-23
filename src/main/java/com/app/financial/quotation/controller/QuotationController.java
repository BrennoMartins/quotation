package com.app.financial.quotation.controller;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.usecase.QuotationUseCase;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("app/")
public class QuotationController {

    @Autowired
    private QuotationUseCase quotationUseCase;

    @GetMapping("/quotation")
    public ResponseEntity<List<Quotation>> getAllQuatotation(){
        if(quotationUseCase.getAllQuotation().isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(quotationUseCase.getAllQuotation());
        }
    }

    @PostMapping("/quotation")
    public ResponseEntity<Quotation> saveQuotation(@RequestBody Quotation quotation){
        quotationUseCase.saveQuotation(quotation);
        return ResponseEntity.created(null).build();
    }

    @PutMapping("/quotation/{id}")
    public ResponseEntity<Quotation> updateQuotation(@PathVariable Long id, @RequestBody Quotation quotation){
        quotation.setId(id);
        quotationUseCase.saveQuotation(quotation);
        return ResponseEntity.created(null).build();
    }

    @GetMapping("/quotation/{id}")
    public ResponseEntity<Quotation> getByQuatotation(@PathVariable Long id){
        return quotationUseCase.getByIdQuotation(id).map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
    @GetMapping("/quotation/filter")
    public ResponseEntity<List<Quotation>> getByAutomatic(@RequestParam("automaticQuotation") Boolean automaticQuotation){
        if(quotationUseCase.getByAutomatic(automaticQuotation).isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            return ResponseEntity.ok(quotationUseCase.getByAutomatic(automaticQuotation));
        }
    }

    @DeleteMapping("/quotation/{id}")
    public ResponseEntity<Quotation>  deleteQuatotation(@PathVariable Long id){
        if(quotationUseCase.getByIdQuotation(id).isEmpty()){
            return ResponseEntity.noContent().build();
        }else{
            quotationUseCase.deleteQuotation(id);
            return ResponseEntity.ok().build();
        }
    }


}
