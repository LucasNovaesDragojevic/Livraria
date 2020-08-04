package br.com.livraria.loja.models;

import javax.json.Json;
import javax.json.JsonObjectBuilder;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Promo 
{	
	private String titulo;
	private Livro livro = new Livro();
	
	public String toJson() 
	{
		JsonObjectBuilder promo = Json.createObjectBuilder();
		promo.add("titulo", titulo)
				.add("livroId", livro.getId());
		return promo.build().toString();	
	}
}
