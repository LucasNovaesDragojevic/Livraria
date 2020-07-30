package br.com.livraria.loja.models;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.json.Json;
import javax.json.JsonArrayBuilder;

import br.com.livraria.loja.daos.CompraDao;

@Named
@SessionScoped
public class Carrinho implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	private Set<CarrinhoItem> itens = new HashSet<>();
	
	@Inject
	private CompraDao compraDao;
	
	public void add(CarrinhoItem item)
	{
		itens.add(item);
	}
	
	public List<CarrinhoItem> getItens()
	{
		return new ArrayList<CarrinhoItem>(itens);
	}
	
	public BigDecimal getTotal()
	{
		BigDecimal total = BigDecimal.ZERO;
		for (CarrinhoItem carrinhoItem : itens)
		{
			total = total.add(carrinhoItem.getLivro().getPreco().multiply(new BigDecimal(carrinhoItem.getQuantidade())));
		}
		return total;
	}
	
	public BigDecimal getTotal(CarrinhoItem item)
	{
		return item.getLivro().getPreco().multiply(new BigDecimal(item.getQuantidade()));
	}
	
	public void remover(CarrinhoItem item)
	{
		itens.remove(item);
	}
	
	public Integer getQuantidadeTotal()
	{
		return itens.stream().mapToInt(item -> item.getQuantidade()).sum();
	}
	
	public void finalizar(Usuario usuario)
	{
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		compra.setItens(toJson());
		compraDao.salvar(compra);
	}

	private String toJson() 
	{
		JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
		for (CarrinhoItem carrinhoItem : itens) 
		{
			jsonArrayBuilder.add
			(
				Json.createObjectBuilder()
					.add("titulo", carrinhoItem.getLivro().getTitulo())
					.add("preco", carrinhoItem.getLivro().getPreco())
					.add("quantidade", carrinhoItem.getQuantidade())
					.add("total", getTotal(carrinhoItem))
			);
		}
		return jsonArrayBuilder.build().toString();
	}
}
