package br.com.recode.requisicoes;

import lombok.Data;

@Data
public class UsuarioCorpoRequisicaoPost {
	private String nome;
	private String sobrenome;
	private String email;
	private String senha;
}
