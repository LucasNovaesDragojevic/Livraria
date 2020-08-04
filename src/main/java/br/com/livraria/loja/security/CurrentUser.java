package br.com.livraria.loja.security;

import java.security.Principal;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import br.com.livraria.loja.daos.SecurityDao;
import br.com.livraria.loja.models.User;

@Model
public class CurrentUser 
{
	@Inject
	private HttpServletRequest request;
	
	@Inject
	private SecurityDao securityDao;
	
	private User user;
	
	public User get()
	{
		return user;
	}
	
	public Boolean hasRole(String role)
	{
		return request.isUserInRole(role);
	}
	
	public String logout()
	{
		request.getSession().invalidate();
		return "/livros/lista?faces-redirect=true";
	}
	
	@PostConstruct
	public void loadUser()
	{
		Principal principal = request.getUserPrincipal();
		if (principal != null)
		{
			user = securityDao.findByEmail(request.getUserPrincipal().getName());
		}
	}
}
