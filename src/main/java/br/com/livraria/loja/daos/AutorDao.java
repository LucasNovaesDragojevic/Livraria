package br.com.livraria.loja.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.loja.models.Autor;

public class AutorDao 
{
	@PersistenceContext
	private EntityManager em;

	public List<Autor> listar()
	{
		return em
				.createQuery("SELECT a FROM Autor a", Autor.class)
				.getResultList();
	}
}
