package br.com.marcelo.todolist.exception;

public class TaskDescriptionInvalidException extends RuntimeException{

	public TaskDescriptionInvalidException(String msg) {
		super(msg);
	}
}
