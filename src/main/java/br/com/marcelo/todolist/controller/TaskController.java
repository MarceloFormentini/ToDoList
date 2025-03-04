package br.com.marcelo.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelo.todolist.dto.ErrorMessage;
import br.com.marcelo.todolist.dto.TaskResponse;
import br.com.marcelo.todolist.exception.TaskDescriptionInvalidException;
import br.com.marcelo.todolist.exception.TaskPriorityInvalidException;
import br.com.marcelo.todolist.exception.TaskStatusInvalidException;
import br.com.marcelo.todolist.exception.TaskTitleInvalidException;
import br.com.marcelo.todolist.exception.UsersNotFoundException;
import br.com.marcelo.todolist.model.Task;
import br.com.marcelo.todolist.service.TaskService;

@RestController
public class TaskController {

	@Autowired
	private TaskService service;
	
	@PostMapping("/task")
	public ResponseEntity<?> addNewTask(@RequestBody Task newTask){
		try {
			TaskResponse response = service.addNewTask(newTask);
			if (response != null) {
				return ResponseEntity.ok(response);
			}
		}
		catch(UsersNotFoundException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(TaskTitleInvalidException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(TaskDescriptionInvalidException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(TaskPriorityInvalidException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(TaskStatusInvalidException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		return ResponseEntity.badRequest().build();
	}
}
