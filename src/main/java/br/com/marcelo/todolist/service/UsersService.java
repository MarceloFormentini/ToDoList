package br.com.marcelo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcelo.todolist.dto.UsersResponse;
import br.com.marcelo.todolist.exception.UsersConflictException;
import br.com.marcelo.todolist.exception.UsersEmailInvalidException;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.repository.UsersRepository;

@Service
public class UsersService {

	@Autowired
	private UsersRepository usersRepository;
	
	private static final String EMAIL_REGEX = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
	
	public UsersResponse addNewUser(Users newUsers) {
		Users user_register = usersRepository.findByEmail(newUsers.getEmail());
		if (user_register != null) {
			throw new UsersConflictException("Email " + user_register.getEmail() + " já cadastrado.");
		}
		
		if (isValidEmail(newUsers.getEmail()) == false) {
			throw new UsersEmailInvalidException("Email " + newUsers.getEmail() + " é inválido");
		}
		String bcryptHashString = BCrypt.withDefaults().hashToString(12, newUsers.getPassword().toCharArray());

		newUsers.setPassword(bcryptHashString);
		Users new_user = usersRepository.save(newUsers);
		
		return new UsersResponse(
			new_user.getName(),
			new_user.getEmail()
		);
	}
	
	private static boolean isValidEmail(String email) {
        return email != null && email.matches(EMAIL_REGEX);
    }
}
