package br.com.casadocodigo.loja.models;

import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@Data
public class Pedido {

    private Long id;

    private BigDecimal valor;

    @DateTimeFormat
    private Calendar data;

    private List<Produto> produtos = new ArrayList<>();
}
