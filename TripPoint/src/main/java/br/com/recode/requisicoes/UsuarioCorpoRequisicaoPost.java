package br.com.recode.requisicoes;

import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Data
public class UsuarioCorpoRequisicaoPost {
	@NotEmpty(message="Nome é um campo obrigatório")
	private String nome;
	@NotEmpty(message="Sobrenome é um campo obrigatório")
	private String sobrenome;
	@NotEmpty(message="Email é um campo obrigatório")
	private String email;
	@NotEmpty(message="Senha é um campo obrigatório")
	private String senha;
}
