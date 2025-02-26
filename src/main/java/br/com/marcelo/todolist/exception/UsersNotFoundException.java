package br.com.marcelo.todolist.exception;

public class UsersNotFoundException extends RuntimeException{

	public UsersNotFoundException(String msg) {
		super(msg);
	}
}
