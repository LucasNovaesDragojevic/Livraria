package br.com.livraria.loja.beans;

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
	
	@Inject
	private FacesContext facesContext;
	
	public String salvar()
	{
		livroDao.salvar(livro);
		facesContext.getExternalContext()
					.getFlash()
					.setKeepMessages(true);
		facesContext.addMessage(null, new FacesMessage("Livro salvo com sucesso."));
		return "/livros/lista?faces-redirect=true";
	}
	
	public List<Autor> getAutores()
	{
		return autorDao.listar();
	}
}
