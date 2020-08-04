package br.com.livraria.loja.beans;

import javax.enterprise.inject.Model;
import javax.inject.Inject;

import br.com.livraria.loja.models.Promo;
import br.com.livraria.loja.websockets.PromosEndpoint;
import lombok.Getter;
import lombok.Setter;

@Model
@Getter @Setter
public class AdminPromosBean 
{
	private Promo promo = new Promo();
	
	@Inject
	private PromosEndpoint promosEndpoint;
	
	public void enviar()
	{
		promosEndpoint.send(promo);
	}
}
