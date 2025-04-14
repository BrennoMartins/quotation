package com.app.financial.quotation.adapter.btc.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class CurrencyResponseHgBrasil {
    private String source;
    private CurrencyDataResponseHgBrasil USD;
    private CurrencyDataResponseHgBrasil EUR;
    private CurrencyDataResponseHgBrasil GBP;
    private CurrencyDataResponseHgBrasil ARS;
    private CurrencyDataResponseHgBrasil CAD;
    private CurrencyDataResponseHgBrasil AUD;
    private CurrencyDataResponseHgBrasil JPY;
    private CurrencyDataResponseHgBrasil CNY;
    private CurrencyDataResponseHgBrasil BTC;
}
