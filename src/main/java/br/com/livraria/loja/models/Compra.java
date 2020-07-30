package br.com.livraria.loja.models;

import java.math.BigDecimal;
import java.util.UUID;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;

import lombok.Setter;

import lombok.Getter;

@Entity
@Getter @Setter
public class Compra 
{
	@Id @GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@ManyToOne(cascade = CascadeType.PERSIST)
	private Usuario usuario;
	
	@Lob
	private String itens;
	
	private String uuid;
	
	private BigDecimal total;

	@PrePersist
	public void createUuid() 
	{
		uuid = UUID.randomUUID().toString();
	}
}
