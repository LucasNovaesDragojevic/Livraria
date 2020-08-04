package br.com.livraria.loja.websockets;

import java.util.List;

import javax.inject.Inject;
import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import br.com.livraria.loja.models.Promo;

@ServerEndpoint("/canal/promos")
public class PromosEndpoint 
{
	@Inject
	private UsuariosSession usuarios;
	
	@OnOpen
	public void onMessage(Session session)
	{
		usuarios.add(session);
	}
	
	public void send(Promo promo)
	{
		List<Session> sessions = usuarios.getUsuarios();
		for (Session session : sessions)
		{
			if (session.isOpen())
			{
				try
				{
					session.getBasicRemote().sendText(promo.toJson());
					System.out.println("Terminado no websocket");
				}
				catch (Exception e)
				{
					throw new RuntimeException(e);
				}
			}
		}
	}
	
	@OnClose
	public void onClose(Session session, CloseReason closeReason)
	{
		usuarios.remove(session);
		System.out.println(closeReason.getCloseCode());
	}
}
