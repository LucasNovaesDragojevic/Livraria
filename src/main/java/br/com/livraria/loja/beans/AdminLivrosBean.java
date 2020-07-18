package br.com.livraria.loja.beans;

import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.livraria.loja.daos.AutorDao;
import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Autor;
import br.com.livraria.loja.models.Livro;
import lombok.Getter;
import lombok.Setter;

@Named
@RequestScoped
@Getter @Setter
public class AdminLivrosBean 
{	
	private Livro livro = new Livro();

	@Inject
	private LivroDao livroDao;
	
	@Inject
	private AutorDao autorDao;
	
	private List<Integer> autoresId = new ArrayList<Integer>();
	
	public String salvar()
	{
		for (Integer autorId : autoresId)
		{
			livro.getAutores().add(new Autor(autorId));
		}
		livroDao.salvar(livro);
		FacesContext.getCurrentInstance()
					.getExternalContext()
					.getFlash()
					.setKeepMessages(true);
		FacesContext.getCurrentInstance()
					.addMessage(null, new FacesMessage("Livro salvo com sucesso."));
		return "/livros/lista?faces-redirect=tre";
	}
	
	public List<Autor> getAutores()
	{
		return autorDao.listar();
	}
}
