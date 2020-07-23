package br.com.livraria.loja.beans;

import java.util.List;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Livro;

@Model
public class HomeBean 
{
	@Inject
	LivroDao livroDao;
	
	public List<Livro> ultimosLancamentos()
	{
		return livroDao.ultimosLancamentos();
	}
	
	public List<Livro> demaisLivros()
	{
		return livroDao.demaisLivros();
	}	
}