package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.domain.repository.QuotationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class QuotationEntityRepository implements QuotationRepository {

    @Autowired
    private SpringDataQuotationRepository jpaRepository;


    @Override
    public void save(Quotation quotation) {
        jpaRepository.save(new QuotationMapper().toQuotationEntity(quotation));
    }

    @Override
    public Optional<Quotation> findById(Long id) {
        return jpaRepository.findById(id).map(quotationEntity -> new QuotationMapper().toQuotation(quotationEntity));
    }

    @Override
    public Optional<Quotation> findByName(String name) {
        return jpaRepository.findByName(name).map(quotationEntity -> new QuotationMapper().toQuotation(quotationEntity));
    }

    @Override
    public List<Quotation>  findByAutomaticUpdateValue(Boolean automaticUpdateValue) {
        return jpaRepository.findByAutomaticUpdateValue(automaticUpdateValue).stream().map(quotationEntity -> new QuotationMapper().toQuotation(quotationEntity)).toList();
    }

    @Override
    public List<Quotation> findAll() {
        return jpaRepository.findAll().stream().map(quotationEntity -> new QuotationMapper().toQuotation(quotationEntity)).toList();
    }

    @Override
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void update(Quotation quotation) {
        jpaRepository.save(new QuotationMapper().toQuotationEntity(quotation));
    }
}
