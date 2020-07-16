package br.com.livraria.loja.models;

import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Livro 
{
	@Id @GeneratedValue
	private Integer id;
	private String titulo;
	@Lob
	private String descricao;
	private BigDecimal preco;
	private Integer numeroPaginas;
}
