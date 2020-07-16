package br.com.livraria.loja.beans;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Livro;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter @Setter
public class AdminLivrosBean 
{	
	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;
	
	public void salvar()
	{
		livroDao.salvar(livro);
		System.out.println("Livro salvo com sucesso! " + livro);
	}
}
