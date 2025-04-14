package com.app.financial.quotation.usecase;

import com.app.financial.quotation.adapter.btc.BitcoinGateway;
import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import com.app.financial.quotation.domain.model.Quotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class IntegrationQuotationUseCase {

    @Autowired
    private QuotationUseCase quotationUseCase;

    public void updateAutomaticQuotation() {
        List<Quotation> quotationsAutomatic = quotationUseCase.getAllQuotation().stream().filter(Quotation::isAutomaticUpdateValue).toList();
        quotationsAutomatic.forEach(this::handlerUpdateQuotation);
    }

    private void handlerUpdateQuotation(Quotation quotation){
        switch (quotation.getName()) {
            case "BTC" -> updateQuotationBitcoin(quotation);
            default -> defaultQuotation(quotation);
        }
    }

    private void updateQuotationBitcoin(Quotation bitcoinQuotation) {
        QuoteResponseHgBrasil quoteResponseHgBrasil = new BitcoinGateway().getQuotationBitcoin();
        bitcoinQuotation.setValue(BigDecimal.valueOf(quoteResponseHgBrasil.getResults().getCurrencies().getBTC().getSell()));
        bitcoinQuotation.setAutomaticUpdateValue(true);
        quotationUseCase.updateQuotation(bitcoinQuotation);
    }

    private void defaultQuotation(Quotation defaultQuotation) {
        defaultQuotation.setValue(BigDecimal.ZERO);
        defaultQuotation.setAutomaticUpdateValue(false);
        quotationUseCase.updateQuotation(defaultQuotation);
    }
}
