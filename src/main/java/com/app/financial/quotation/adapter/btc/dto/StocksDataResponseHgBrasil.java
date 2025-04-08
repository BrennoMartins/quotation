package com.app.financial.quotation.adapter.btc.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@AllArgsConstructor
@Getter
@Setter
public class StocksDataResponseHgBrasil {
    private String name;
    private String location;
    private Double points;
    private Double variation;
}
