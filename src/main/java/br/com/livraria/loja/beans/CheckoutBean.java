package br.com.livraria.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.models.Carrinho;
import br.com.livraria.loja.models.Usuario;
import lombok.Getter;
import lombok.Setter;

@Model
@Getter @Setter
public class CheckoutBean 
{
	private Usuario usuario = new Usuario();
	
	@Inject
	private Carrinho carrinho;
	
	public void finalizar()
	{
		carrinho.finalizar(usuario);
	}
}
