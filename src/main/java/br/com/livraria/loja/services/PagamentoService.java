package br.com.livraria.loja.services;

import java.net.URI;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import br.com.livraria.loja.daos.CompraDao;
import br.com.livraria.loja.models.Compra;

@Path("/pagamento")
public class PagamentoService 
{
	@Context
	private ServletContext servletContext;
	
	@Inject
	private CompraDao compraDao;
	
	@Inject
	private PagamentoGateway pagamentoGateway;
	
	private static ExecutorService executor = Executors.newFixedThreadPool(50);
	
	@POST
	public void pagar(@Suspended final AsyncResponse ar, @QueryParam("uuid") String uuid)
	{
		Compra compra = compraDao.getByUuid(uuid);
        String contextPath = servletContext.getContextPath();
		try
		{
			executor.submit(() -> 
			{
				String resposta = pagamentoGateway.pagar(compra.getTotal());
				System.out.println(resposta);
				URI uri = UriBuilder
							.fromPath("http://localhost:8080" + contextPath + "/index.xhtml")
							.queryParam("msg", "Compra-realizada-com-sucesso")
							.build();
				Response response = Response.seeOther(uri).build();
				System.out.println("Pagamento finalizado");
				ar.resume(response);
			});
		}
		catch (Exception e)
		{
			System.out.println("Pagamento não finalizado");
			ar.resume(new WebApplicationException(e));
		}
	}
}
