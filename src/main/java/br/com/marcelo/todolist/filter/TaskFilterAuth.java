package br.com.marcelo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import at.favre.lib.crypto.bcrypt.BCrypt;
import br.com.marcelo.todolist.exception.MissingCredentialsException;
import br.com.marcelo.todolist.exception.UsersInvalidPasswordException;
import br.com.marcelo.todolist.exception.UsersNotFoundException;
import br.com.marcelo.todolist.repository.UsersRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class TaskFilterAuth extends OncePerRequestFilter{

	@Autowired
	private UsersRepository usersRepository; 

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		if (!request.getServletPath().equals("/task")) {
            filterChain.doFilter(request, response); // Permite que a requisição continue sem bloqueios
            return;
        }
		
		// obter dados da autenticação
		var authorization = request.getHeader("Authorization");

		if (authorization == null || !authorization.startsWith("Basic ")) {
			throw new MissingCredentialsException("Esta requisição requer autenticação.");
		}

		//decode base64
		var auth_encoded = authorization.substring("Basic".length()).trim();
		
		byte[] auth_decode = Base64.getDecoder().decode(auth_encoded);
		
		var auth_string = new String(auth_decode);
		
		String[] credentials = auth_string.split(":");
		String email = credentials[0];
		String password = credentials[1];
		
		// validar usuário
		var user = usersRepository.findByEmail(email)
		.orElseThrow(() ->{
			throw new UsersNotFoundException("Usuário " + email + " não cadastrado.");
		});
		
		// validar senha
		if(!BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
			throw new UsersInvalidPasswordException("Senha do usuário " + email + " inválida.");
		}
		
		request.setAttribute("user_id", user.getId());

		filterChain.doFilter(request, response);
		
	}


}
