package br.com.marcelo.todolist.exception;

import java.time.LocalDateTime;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

	// Método genérico para criar um Map de resposta
    private Map<String, Object> createErrorResponse(HttpStatus status, String message) {
        return Map.of(
                "timestamp", LocalDateTime.now(),
                "status", status.value(),
                "message", message
        );
    }
    
    @ExceptionHandler(UsersNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleUserNotFound(UsersNotFoundException ex) {
    	System.out.println("handleUserNotFound");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage()));
    }
    
    // Tratamento para email já informado
    @ExceptionHandler(UsersConflictException.class)
    public ResponseEntity<Map<String, Object>> handleUserConflict(UsersConflictException ex) {
    	System.out.println("handleUserConflict");
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(createErrorResponse(HttpStatus.CONFLICT, ex.getMessage()));
    }

    // Tratamento para senha inválida
    @ExceptionHandler(UsersInvalidPasswordException.class)
    public ResponseEntity<Map<String, Object>> handleInvalidPassword(UsersInvalidPasswordException ex) {
    	System.out.println("handleInvalidPassword");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(createErrorResponse(HttpStatus.UNAUTHORIZED, ex.getMessage()));
    }
    
    // Tratamento para Authorization não informada
    @ExceptionHandler(MissingCredentialsException.class)
    public ResponseEntity<Map<String, Object>> handleMissingCredentials(MissingCredentialsException ex) {
    	System.out.println("handleMissingCredentials");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(createErrorResponse(HttpStatus.BAD_REQUEST, ex.getMessage()));
    }

    // Tratamento genérico para exceções inesperadas
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGenericException(Exception ex) {
    	System.out.println("handleGenericException");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "Erro interno no servidor"));
    }
}
