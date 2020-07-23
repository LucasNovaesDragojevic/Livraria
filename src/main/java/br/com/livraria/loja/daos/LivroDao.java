package br.com.livraria.loja.daos;

import java.util.List;

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

	public List<Livro> listar() 
	{
		return em
				.createQuery("SELECT distinct(l) FROM Livro l JOIN FETCH l.autores", Livro.class)
				.getResultList();
	}

	public List<Livro> ultimosLancamentos() 
	{
		return em.createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
					.setMaxResults(5)
					.getResultList();
	}

	public List<Livro> demaisLivros() 
	{
		return em.createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
					.setFirstResult(6)
					.getResultList();
	}
}
