package br.com.livraria.loja.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Pagamento 
{
	private BigDecimal value;
	
	public Pagamento(BigDecimal value)
	{
		this.value = value;
	}
}
