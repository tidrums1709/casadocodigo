package br.com.casadocodigo.loja.controllers;

import br.com.casadocodigo.loja.dao.ProdutoDAO;
import br.com.casadocodigo.loja.models.Produto;
import br.com.casadocodigo.loja.models.RelatorioProduto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.*;
import java.util.regex.Pattern;

@Controller
public class RelatorioProdutosController {

	private static Pattern DATE_PATTERN = Pattern.compile("^\\d{4}-\\d{2}-\\d{2}$");

    @Autowired
    private ProdutoDAO produtoDao;

	@RequestMapping({"/relatorio-produtos", "/relatorio-produtos/{data}"})
	@ResponseBody
	public RelatorioProduto detalheJson(@RequestParam(required = false) String data){
		if(Objects.nonNull(data)){
			Calendar dataValida = this.converteData(data);
			return this.converteRelatorio(produtoDao.listaByData(dataValida));

		}
	    return this.converteRelatorio(produtoDao.listar());
	}

	/**
	 * Metodo que cria a o relatório com base na lista de produtos.
	 * PARAM: List<Produtos>
	 * RETURN: RelatorioProduto
	 */
	private RelatorioProduto converteRelatorio(List<Produto> listaByData) {
		RelatorioProduto relatorioProduto = new RelatorioProduto();
		relatorioProduto.setDataGeracao(Calendar.getInstance());
		relatorioProduto.setQuantidade(listaByData.size());
		relatorioProduto.setProdutos(listaByData);
		return relatorioProduto;
	}

	private Calendar converteData(String data) {
		if(!DATE_PATTERN.matcher(data).matches()){
			throw new RuntimeException("Formato de data invalido");
		}
		String[] dateParts = data.split("-");
		if(dateParts[1].startsWith("0")){
			dateParts[1] = dateParts[1].substring(1);
		}
		return new GregorianCalendar(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]) -1, Integer.parseInt(dateParts[2]));
	}


}
