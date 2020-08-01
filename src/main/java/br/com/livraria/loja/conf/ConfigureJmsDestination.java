package br.com.livraria.loja.conf;

import javax.jms.JMSDestinationDefinition;

@JMSDestinationDefinition
(
		name = "java:/jms/topics/CarrinhoComprasTopico", 
		interfaceName = "javax.jms.Topic"
)
public class ConfigureJmsDestination {}
