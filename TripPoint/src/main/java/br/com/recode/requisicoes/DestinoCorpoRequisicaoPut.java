package br.com.recode.requisicoes;

import javax.validation.constraints.NotEmpty;

import lombok.Data;

@Data
public class DestinoCorpoRequisicaoPut {
	@NotEmpty(message="Id é um campo obrigatório")
	private Long id;
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@NotEmpty(message="País é um campo obrigatório")
	private String pais;
	@NotEmpty(message="Continente é um campo obrigatório")
	private String continente;
	@NotEmpty(message="Preço é um campo obrigatório")
	private Number preco;
}
