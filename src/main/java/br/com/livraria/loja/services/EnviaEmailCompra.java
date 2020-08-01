package br.com.livraria.loja.services;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.MessageDriven;
import javax.inject.Inject;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.TextMessage;

import br.com.livraria.loja.daos.CompraDao;
import br.com.livraria.loja.infra.MailSender;
import br.com.livraria.loja.models.Compra;

@MessageDriven
(
		activationConfig = 
		{
				@ActivationConfigProperty
				(
						propertyName = "destinationLookup", 
						propertyValue = "java:/jms/topics/CarrinhoComprasTopico"
				),
				@ActivationConfigProperty
				(
					propertyName = "destinationType",
					propertyValue = "javax.jms.Topic"
				)
		}
)
public class EnviaEmailCompra implements MessageListener
{
	@Inject
	private MailSender mailSender;
	
	@Inject
	private CompraDao compraDao;
	
	public void onMessage(Message message)
	{
		try 
		{
			TextMessage textMessage = (TextMessage) message;
			Compra compra;
			compra = compraDao.getByUuid(textMessage.getText());
			mailSender.send("compras@casacodigo.com.br", compra.getUsuario().getEmail(), "Nova compra na livraria.", "Sua compra foi foi realizada com sucesso com o número de pedido: " + compra.getUuid());
			System.out.println("Pagamento finalizado");
		} 
		catch (JMSException e) 
		{
			throw new RuntimeException(e);
		}
	}
}
