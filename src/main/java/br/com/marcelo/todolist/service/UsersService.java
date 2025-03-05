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
	private UsersRepository usersRepository;
	
	public UsersResponse addNewUser(Users newUsers) {	    
		usersRepository.findByEmail(newUsers.getEmail())
			.ifPresent(user ->{
				throw new UsersConflictException("Email " + user.getEmail() + " já cadastrado.");
			});
		
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, newUsers.getPassword().toCharArray());

		newUsers.setPassword(bcryptHashString);
		Users new_user = usersRepository.save(newUsers);
		
		return new UsersResponse(
			new_user.getName(),
			new_user.getEmail()
		);
	}
}
