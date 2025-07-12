package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SpringDataQuotationRepository extends JpaRepository<QuotationEntity, Long> {

    List<QuotationEntity> findByAutomaticUpdateValue(Boolean automaticUpdateValue);

    Optional<QuotationEntity> findByName(String name);
}
