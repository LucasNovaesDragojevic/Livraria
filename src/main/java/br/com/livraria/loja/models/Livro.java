package br.com.livraria.loja.models;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Cacheable;
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
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import org.hibernate.validator.constraints.Length;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@XmlRootElement @XmlAccessorType(XmlAccessType.FIELD)
@Entity @Cacheable
@Getter @Setter @ToString @EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Livro 
{
	@EqualsAndHashCode.Include
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotBlank
	private String titulo;
	
	private String capa;
	
	@Lob
	@Length(min = 10) @NotBlank
	private String descricao;
	
	@DecimalMin("20")
	private BigDecimal preco;
	
	@Min(50)
	private Integer numeroPaginas;
	
	private LocalDate dataPublicacao;
	
	@XmlElementWrapper(name = "autores") @XmlElement(name = "autor")
	@ManyToMany
	@Size(min = 1) @NotNull
	private List<Autor> autores = new ArrayList<Autor>();
}
