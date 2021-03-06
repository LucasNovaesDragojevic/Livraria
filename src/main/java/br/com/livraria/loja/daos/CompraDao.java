package br.com.livraria.loja.daos;

import java.io.Serializable;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import br.com.livraria.loja.models.Compra;

@Transactional
public class CompraDao implements Serializable 
{
	private static final long serialVersionUID = 1L;
	
	@PersistenceContext
	private EntityManager em;
	
	public void salvar(Compra compra)
	{
		em.persist(compra);
	}
	
	public Compra getByUuid(String uuid) 
	{
		return em.createQuery("SELECT c FROM Compra c WHERE c.uuid = :pUuid", Compra.class)
					.setParameter("pUuid", uuid)
					.getSingleResult();
	}
}
