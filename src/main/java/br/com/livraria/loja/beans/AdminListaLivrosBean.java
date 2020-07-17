package br.com.livraria.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Livro;

@Model
public class AdminListaLivrosBean 
{
	@Inject
	private LivroDao livroDao;
	
	private List<Livro> livros = new ArrayList<Livro>();
	
	public List<Livro> getLivros()
	{
		livros = livroDao.listar();
		return livros;
	}
}
