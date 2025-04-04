package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SpringDataQuotationRepository extends JpaRepository<QuotationEntity, Long> {
}
