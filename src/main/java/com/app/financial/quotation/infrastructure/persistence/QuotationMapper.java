package com.app.financial.quotation.infrastructure.persistence;

import com.app.financial.quotation.domain.model.Quotation;
import com.app.financial.quotation.infrastructure.persistence.entities.QuotationEntity;

public class QuotationMapper {

    public QuotationEntity toQuotationEntity(Quotation quotation){
        return new QuotationEntity(quotation.getId(), quotation.getName(), quotation.getValue(),quotation.getDateLastUpdate(),quotation.isAutomaticUpdateValue());
    }

    public Quotation toQuotation(QuotationEntity quotationEntity){
        return new Quotation(quotationEntity.getId(),
                quotationEntity.getName(),
                quotationEntity.getValue(),
                quotationEntity.getDateLastUpdate(),
                quotationEntity.isAutomaticUpdateValue());
    }

}
