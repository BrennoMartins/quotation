package com.app.financial.quotation.usecase;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.infrastructure.persistence.QuotationEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class QuotationUseCase {

    @Autowired
    private QuotationEntityRepository quotationEntityRepository;

    public QuotationUseCase(QuotationEntityRepository quotationEntityRepository) {
        this.quotationEntityRepository = quotationEntityRepository;
    }

    public void saveQuotation(Quotation quotation){
        quotationEntityRepository.save(quotation);
    }

    public List<Quotation> getAllQuotation(){
        return quotationEntityRepository.findAll();
    }

    public List<Quotation> getByAutomatic(Boolean automaticUpdateValue){
        return quotationEntityRepository.findByAutomaticUpdateValue(automaticUpdateValue);
    }

    public Optional<Quotation> getByIdQuotation(Long id){
        return quotationEntityRepository.findById(id);
    }

    public void deleteQuotation(Long id){
        quotationEntityRepository.deleteById(id);
    }

    public void updateQuotation(Quotation quotation){
        quotationEntityRepository.save(quotation);
    }
}
