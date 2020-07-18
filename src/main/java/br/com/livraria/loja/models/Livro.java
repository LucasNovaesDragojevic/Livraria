package br.com.livraria.loja.models;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter @Setter @ToString
public class Livro 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String titulo;
	
	@Lob
	@Length(min = 10) @NotBlank
	private String descricao;
	
	@DecimalMin("20")
	private BigDecimal preco;
	
	@Min(50)
	private Integer numeroPaginas;
	
	@ManyToMany
	@Size(min = 1)
	@NotNull
	private List<Autor> autores = new ArrayList<Autor>();
}
