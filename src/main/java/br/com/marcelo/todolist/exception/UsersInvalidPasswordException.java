package br.com.marcelo.todolist.exception;

public class UsersInvalidPasswordException extends RuntimeException{

	public UsersInvalidPasswordException(String msg) {
		super(msg);
	}
}
