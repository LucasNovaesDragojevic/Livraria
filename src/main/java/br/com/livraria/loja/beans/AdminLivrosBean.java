package br.com.livraria.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import br.com.livraria.loja.models.Livro;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter @Setter
public class AdminLivrosBean 
{	
	private Livro livro = new Livro();

	public void salvar()
	{
		System.out.println("Livro salvo com sucesso! " + livro);
	}
}
