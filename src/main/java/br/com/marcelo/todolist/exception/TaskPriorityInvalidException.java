package br.com.marcelo.todolist.exception;

public class TaskPriorityInvalidException extends RuntimeException{

	public TaskPriorityInvalidException(String msg) {
		super(msg);
	}
}
