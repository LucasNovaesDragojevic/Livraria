package br.com.livraria.loja.beans;

import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Autor;
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
	
	public List<Autor> getAutores()
	{
		return Arrays.asList(new Autor(1, "Lucas"), new Autor(2, "Luiza"));
	}
}
