package com.app.financial.quotation.usecase;

import com.app.financial.quotation.adapter.b3.B3Gateway;
import com.app.financial.quotation.adapter.b3.dto.B3Dto;
import com.app.financial.quotation.adapter.btc.BitcoinGateway;
import com.app.financial.quotation.adapter.btc.dto.QuoteResponseHgBrasil;
import com.app.financial.quotation.domain.model.Quotation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Service
public class IntegrationQuotationUseCase {

    @Autowired
    private QuotationUseCase quotationUseCase;
    private List<B3Dto> listB3Asset;

    public void updateAutomaticQuotation() {
        listB3Asset = Arrays.stream(new B3Gateway().getAllQuotationB3()).toList();
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

        listB3Asset.forEach(b3Asset -> {
            if(b3Asset.getCodigoNegociacao().equals(defaultQuotation.getName())){
                defaultQuotation.setValue(b3Asset.getPrecoFechamento());
                defaultQuotation.setAutomaticUpdateValue(true);
            }
        });
        quotationUseCase.updateQuotation(defaultQuotation);


    }
}
