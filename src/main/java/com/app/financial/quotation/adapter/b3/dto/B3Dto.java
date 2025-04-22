package com.app.financial.quotation.adapter.b3.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class B3Dto {
    private String produto;
    private String instituicao;
    private String conta;
    private String codigoNegociacao;
    private String cnpjFundo;
    private String codigoIsinDistribuicao;
    private String tipo;
    private String administrador;
    private BigDecimal quantidade;
    private BigDecimal quantidadeDisponivel;
    private BigDecimal quantidadeIndisponivel;
    private String motivo;
    private BigDecimal precoFechamento;
    private BigDecimal valorAtualizado;
}
