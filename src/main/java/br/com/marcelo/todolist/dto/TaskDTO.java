package br.com.marcelo.todolist.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class TaskDTO {
	
	@NotBlank(message="O título não pode estar vazio")
    @Size(min=3, message="O título deve ter no mínimo 3 caracteres")
    private String title;

    @NotBlank(message="A descrição não pode estar vazia")
    @Size(min=3, message="A descrição deve ter no mínimo 3 caracteres")
    private String description;

    @NotBlank(message="A prioridade não pode estar vazia")
    private String priority;

    @NotBlank(message="O status não pode estar vazio")
    private String status;

    @NotNull(message="O código do usuário é obrigatório")
    private Integer user_id;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getUser_id() {
		return user_id;
	}

	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}

}
