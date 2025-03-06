package br.com.marcelo.todolist.filter;

import java.io.IOException;
import java.util.Base64;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
		
		try {
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

			if (credentials.length != 2) {
                throw new MissingCredentialsException("Formato de autenticação inválido.");
            }

			String email = credentials[0];
			String password = credentials[1];
			
			// validar usuário
			var user = usersRepository.findByEmail(email)
			.orElseThrow(() ->{
				throw new UsersNotFoundException("Usuário não encontrado.");
			});
			
			// validar senha
			if(!BCrypt.verifyer().verify(password.toCharArray(), user.getPassword()).verified) {
				throw new UsersInvalidPasswordException("Senha inválida.");
			}
			
			request.setAttribute("user_id", user.getId());
	
			filterChain.doFilter(request, response);
		}
		catch(UsersNotFoundException ex) {
			handleException(response, ex.getMessage(), HttpStatus.NOT_FOUND);
		}
		catch(UsersInvalidPasswordException ex) {
			handleException(response, ex.getMessage(), HttpStatus.UNAUTHORIZED);
		}
		catch(MissingCredentialsException ex) {
			handleException(response, ex.getMessage(), HttpStatus.BAD_REQUEST);
		}
		catch(Exception ex) {
			handleException(response, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private void handleException(HttpServletResponse response, String message, HttpStatus status) throws IOException{
		response.setContentType("application/json");
		response.setStatus(status.value());
		response.getWriter().write(
			"{\"timestamp\": \"" + java.time.LocalDateTime.now() + "\", " +
			"\"status\": " + status.value() + ", " +
			"\"error\": \"" + status.getReasonPhrase() + "\", " +
			"\"message\": \"" + message + "\"}"
		);
	}

}
