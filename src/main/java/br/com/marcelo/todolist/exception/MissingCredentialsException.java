package br.com.marcelo.todolist.exception;

public class MissingCredentialsException extends RuntimeException{

	public MissingCredentialsException(String msg) {
		super(msg);
	}
}
