package br.com.livraria.loja.daos;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.livraria.loja.models.User;

public class SecurityDao 
{

	@PersistenceContext
	private EntityManager em;
	
	public User findByEmail(String email) 
	{
		return em.createQuery("SELECT u FROM User u WHERE u.email = :pEmail", User.class)
					.setParameter("pEmail", email)
					.getSingleResult();
	}

}
