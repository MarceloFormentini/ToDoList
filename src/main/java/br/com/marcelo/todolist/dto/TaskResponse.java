package br.com.marcelo.todolist.dto;

import java.time.LocalDateTime;

public record TaskResponse(String title, String description, String priority, LocalDateTime created_at) {

}
