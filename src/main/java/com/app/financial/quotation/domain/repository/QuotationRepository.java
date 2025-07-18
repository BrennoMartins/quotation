package com.app.financial.quotation.domain.repository;

import com.app.financial.quotation.domain.model.Quotation;

import java.util.List;
import java.util.Optional;

public interface QuotationRepository {
    void save(Quotation quotation);
    Optional<Quotation> findById(Long id);
    Optional<Quotation> findByName(String name);
    List findByAutomaticUpdateValue(Boolean automaticUpdateValue);
    List findAll();
    void deleteById(Long id);
    void update(Quotation quotation);
}
