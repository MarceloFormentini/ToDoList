package br.com.marcelo.todolist.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.marcelo.todolist.dto.ErrorMessage;
import br.com.marcelo.todolist.dto.UsersResponse;
import br.com.marcelo.todolist.exception.UsersConflictException;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.service.UsersService;

@RestController
public class UsersController {

	@Autowired
	private UsersService service;
	
	@PostMapping("/users")
	public ResponseEntity<?> addNewUsers(@RequestBody Users newUsers) {
		try {
			UsersResponse response = service.addNewUsers(newUsers);
			if (response != null) {
				return ResponseEntity.ok(response);
			}
		}
		catch(UsersConflictException ex) {
			return ResponseEntity.status(409).body(new ErrorMessage(ex.getMessage()));
		}
		return ResponseEntity.badRequest().build();
	}
	
	@GetMapping("/users/{username}")
	public ResponseEntity<Users> getEventByUsername(@PathVariable String username){
		Users user = service.findByUsername(username);
		if (user != null) {
			return ResponseEntity.ok().body(user);
		}
		return ResponseEntity.notFound().build();
	}
}
