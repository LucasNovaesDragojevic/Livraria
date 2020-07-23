package br.com.livraria.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.daos.LivroDao;
import br.com.livraria.loja.models.Livro;
import lombok.Getter;
import lombok.Setter;

@Model
@Getter @Setter
public class LivroDetalheBean 
{
	@Inject
	private LivroDao livroDao;
	
	private Livro livro;
	
	private Integer id;
	
	public void carregarDetalhe()
	{
		livro = livroDao.buscarPorId(id);
	}
}
