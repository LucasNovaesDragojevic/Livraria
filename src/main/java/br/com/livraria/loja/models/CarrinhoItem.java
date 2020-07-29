package br.com.livraria.loja.models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class CarrinhoItem
{
	@EqualsAndHashCode.Include
	private Livro livro;
	private Integer quantidade;
	
	public CarrinhoItem(Livro livro)
	{
		this.livro = livro;
		this.quantidade = 1;
	}
}
