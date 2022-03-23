package br.com.casadocodigo.loja.models;

import lombok.Data;

import java.util.Calendar;
import java.util.List;

@Data
public class RelatorioProduto {

    private Calendar dataGeracao;
    private Integer quantidade;
    private List<Produto> produtos;
}
