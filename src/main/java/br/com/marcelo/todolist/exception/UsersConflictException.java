package br.com.marcelo.todolist.exception;

public class UsersConflictException extends RuntimeException{

	public UsersConflictException(String msg) {
		super(msg);
	}
}
