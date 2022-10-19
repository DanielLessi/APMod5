package br.com.recode.excecoes;

import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus
@SuppressWarnings("serial")
public class BadRequestException extends RuntimeException{
	public BadRequestException(String mensagemErro) {
		super(mensagemErro);
	}
}
