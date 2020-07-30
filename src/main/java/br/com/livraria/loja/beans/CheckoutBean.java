package br.com.livraria.loja.beans;

import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import br.com.livraria.loja.models.Carrinho;
import br.com.livraria.loja.models.Compra;
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
	
	@Inject
	private FacesContext facesContext;
	
	public void finalizar()
	{
		Compra compra = new Compra();
		compra.setUsuario(usuario);
		carrinho.finalizar(compra);
		
		String contextName = facesContext.getExternalContext().getRequestContextPath();
		HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse();
		response.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		response.setHeader("Location", contextName + "/service/pagamento?uuid=" + compra.getUuid());
	}
}
