package br.com.marcelo.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelo.todolist.dto.ErrorMessage;
import br.com.marcelo.todolist.dto.UsersResponse;
import br.com.marcelo.todolist.exception.UsersConflictException;
import br.com.marcelo.todolist.exception.UsersEmailInvalidException;
import br.com.marcelo.todolist.exception.UsersNameException;
import br.com.marcelo.todolist.exception.UsersPasswordException;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.service.UsersService;

@RestController
public class UsersController {
	
	@Autowired
	private UsersService service;
	
	@PostMapping("/users")
	public ResponseEntity<?> addNewUsers(@RequestBody Users newUsers){
		try {
			UsersResponse response = service.addNewUser(newUsers);
			if (response != null) {
				return ResponseEntity.ok(response);
			}
		}
		catch(UsersConflictException ex) {
			return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
		}
		catch(UsersEmailInvalidException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(UsersNameException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		catch(UsersPasswordException ex) {
			return ResponseEntity.status(400).body(new ErrorMessage(ex.getMessage()));
		}
		
		return ResponseEntity.badRequest().build();
	}

}
