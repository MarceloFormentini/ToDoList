package br.com.marcelo.todolist.exception;

public class TaskStatusInvalidException extends RuntimeException{

	public TaskStatusInvalidException(String msg) {
		super(msg);
	}
}
