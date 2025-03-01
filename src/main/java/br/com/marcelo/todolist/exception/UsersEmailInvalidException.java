package br.com.marcelo.todolist.exception;

public class UsersEmailInvalidException extends RuntimeException{

	public UsersEmailInvalidException(String msg) {
		super(msg);
	}
}
