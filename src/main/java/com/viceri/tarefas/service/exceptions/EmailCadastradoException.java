package com.viceri.tarefas.service.exceptions;

public class EmailCadastradoException extends RuntimeException {

	
	private static final long serialVersionUID = 1L;

	public EmailCadastradoException(String email) {
		super("Email " + email + " já existente na base de dados!"); 
	}
}
