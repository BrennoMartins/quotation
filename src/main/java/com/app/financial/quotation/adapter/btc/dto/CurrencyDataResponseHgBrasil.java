package com.app.financial.quotation.adapter.btc.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class CurrencyDataResponseHgBrasil {
    private String name;
    private Double buy;
    private Double sell;
    private Double variation;
}
