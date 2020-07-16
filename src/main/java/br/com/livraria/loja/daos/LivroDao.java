package br.com.livraria.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.loja.models.Livro;

public class LivroDao 
{
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Livro livro)
	{
		em.persist(livro);
	}
}
