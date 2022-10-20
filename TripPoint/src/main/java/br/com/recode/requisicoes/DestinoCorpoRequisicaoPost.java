package br.com.recode.requisicoes;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class DestinoCorpoRequisicaoPost {
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@NotEmpty(message="País é um campo obrigatório")
	private String pais;
	@NotEmpty(message="Continente é um campo obrigatório")
	private String continente;
	@NotNull(message="Preço é um campo obrigatório")
	private Number preco;
}
