package br.com.livraria.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Carrinho;
import br.com.livraria.loja.models.CarrinhoItem;
import br.com.livraria.loja.models.Livro;

@Model
public class CarrinhoComprasBean 
{
	@Inject
	private LivroDao livroDao;
	
	@Inject
	private Carrinho carrinho;
	
	public String add(Integer id)
	{
		Livro livro = livroDao.buscarPorId(id);
		CarrinhoItem item = new CarrinhoItem(livro);
		carrinho.add(item);
		return "carrinho?faces-redirect=true";
	}
	
	public List<CarrinhoItem> getItens()
	{
		return carrinho.getItens();
	}
	
	public void remover(CarrinhoItem item)
	{
		carrinho.remover(item);
	}
}
