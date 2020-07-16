package br.com.livraria.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.livraria.loja.models.Livro;

@Transactional
public class LivroDao 
{
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Livro livro)
	{
		em.persist(livro);
	}
}
