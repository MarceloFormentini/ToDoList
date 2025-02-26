package br.com.marcelo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcelo.todolist.dto.UsersResponse;
import br.com.marcelo.todolist.exception.UsersConflictException;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository userRepository;
	
	public UsersResponse addNewUsers(Users users) {
		// recupera usuário pelo username
		Users user_register = userRepository.findByUsername(users.getUsername());
		if (user_register != null) {
			throw new UsersConflictException("Username " + user_register.getUsername() + " já existe.");
		}
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, users.getPassword().toCharArray());

		users.setPassword(bcryptHashString);
		Users new_user = userRepository.save(users);
		
		return new UsersResponse(
			new_user.getUsername(),
			new_user.getName()
		);
	}
	
	public Users findByUsername(String username) {
		return userRepository.findByUsername(username);
	}
}
