package br.com.marcelo.todolist.exception;

public class TaskTitleInvalidException extends RuntimeException{
	
	public TaskTitleInvalidException(String msg) {
		super(msg);
	}
}
