package br.com.livraria.loja.models;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter @Setter @ToString
public class Livro 
{
	private String titulo;
	private String descricao;
	private BigDecimal preco;
	private Integer numeroPaginas;
}
