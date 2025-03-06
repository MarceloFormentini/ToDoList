package br.com.marcelo.todolist.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelo.todolist.dto.ErrorMessageDTO;
import br.com.marcelo.todolist.dto.UsersResponseDTO;
import br.com.marcelo.todolist.exception.UsersConflictException;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.service.UsersService;
import jakarta.validation.Valid;

@RestController
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@PostMapping("/users")
	public ResponseEntity<?> addNewUsers(@Valid @RequestBody Users newUsers, BindingResult result){
		if (result.hasErrors()) {
			Map<String, String> errors = new HashMap<>();
	        result.getFieldErrors().forEach(error -> errors.put(error.getField(), error.getDefaultMessage()));
	        return ResponseEntity.badRequest().body(errors);
		}
		
		Users userSaved = service.addNewUser(newUsers);
		
		return ResponseEntity.ok(
			new UsersResponseDTO(
				userSaved.getName(),
				userSaved.getEmail()
			)
		);
	}

}
