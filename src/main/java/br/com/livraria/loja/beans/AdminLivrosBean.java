package br.com.livraria.loja.beans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.loja.daos.AutorDao;
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
	
	@Inject
	private AutorDao autorDao;
	
	private List<Integer> autoresId = new ArrayList<Integer>();
	
	public void salvar()
	{
		for (Integer autorId : autoresId)
		{
			livro.getAutores().add(new Autor(autorId));
		}
		livroDao.salvar(livro);
		System.out.println("Livro salvo com sucesso! " + livro);
		System.out.println("Autores salvos com sucesso! " + autoresId);
		livro = new Livro();
		autoresId = new ArrayList<Integer>();
	}
	
	public List<Autor> getAutores()
	{
		return autorDao.listar();
	}
}
