package com.app.financial.quotation.adapter.btc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class StocksResponseHgBrasil {
    private StocksDataResponseHgBrasil IBOVESPA;
    private StocksDataResponseHgBrasil IFIX;
    private StocksDataResponseHgBrasil NASDAQ;
    private StocksDataResponseHgBrasil DOWJONES;
    private StocksDataResponseHgBrasil CAC;
    private StocksDataResponseHgBrasil NIKKEI;
}
