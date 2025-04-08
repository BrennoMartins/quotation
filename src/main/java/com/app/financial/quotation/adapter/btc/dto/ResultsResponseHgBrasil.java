package com.app.financial.quotation.adapter.btc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class ResultsResponseHgBrasil {
    private CurrencyResponseHgBrasil currencies;
    private StocksResponseHgBrasil stocks;
    private List<String> available_sources;
    private List<Object> taxes; // ou crie uma classe se souber a estrutura
}
