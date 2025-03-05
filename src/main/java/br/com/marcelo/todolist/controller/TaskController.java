package br.com.marcelo.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelo.todolist.dto.ErrorMessage;
import br.com.marcelo.todolist.dto.TaskDTO;
import br.com.marcelo.todolist.dto.TaskResponse;
import br.com.marcelo.todolist.exception.UsersNotFoundException;
import br.com.marcelo.todolist.service.TaskService;
import jakarta.validation.Valid;

@RestController
public class TaskController {

	@Autowired
	private TaskService service;
	
	@PostMapping("/task")
	public ResponseEntity<?> addNewTask(@Valid @RequestBody TaskDTO taskDTO, BindingResult result){
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
	        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	        return ResponseEntity.badRequest().body(errors);
		}

		try {
			TaskResponse response = service.addNewTask(taskDTO);
			if (response != null) {
				return ResponseEntity.ok(response);
			}
		}
		catch(UsersNotFoundException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}

		return ResponseEntity.badRequest().build();
	}
}
