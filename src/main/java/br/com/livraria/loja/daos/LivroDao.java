package br.com.livraria.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.hibernate.jpa.QueryHints;

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
		return em.createQuery("SELECT l FROM Livro l JOIN FETCH l.autores ORDER BY l.id DESC", Livro.class)
					.setMaxResults(5)
					.getResultList();
	}

	public List<Livro> demaisLivros() 
	{
		return em.createQuery("SELECT l FROM Livro l ORDER BY l.id DESC", Livro.class)
					.setFirstResult(5)
					.setHint(QueryHints.HINT_CACHEABLE, true)
					.getResultList();
	}

	public Livro buscarPorId(Integer id) 
	{
		return em.createQuery("SELECT l FROM Livro l JOIN FETCH l.autores WHERE l.id = :pId", Livro.class)
					.setParameter("pId", id)	
					.getSingleResult();
	}
}
