package com.app.financial.quotation.adapter.btc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class QuoteResponseHgBrasil {
    private String by;
    private boolean valid_key;
    private ResultsResponseHgBrasil results;
    private double execution_time;
    private boolean from_cache;
}
