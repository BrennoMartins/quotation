package com.app.financial.quotation.adapter.b3;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroExcel {
    private String produto;
    private String instituicao;
    private String conta;
    private String codigoNegociacao;
    private String cnpjFundo;
    private String codigoIsinDistribuicao;
    private String tipo;
    private String administrador;
    private String quantidade;
    private String quantidadeDisponivel;
    private String quantidadeIndisponivel;
    private String motivo;
    private String precoFechamento;
    private String valorAtualizado;


    //TODO Criar uma aplicação para devolver o Json da B3
    //TODO Montar Integração com a B3 para buscar a cotação
    //TODO Montar Integração quotation e b3 no investiment asset







}
