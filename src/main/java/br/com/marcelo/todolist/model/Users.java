package br.com.marcelo.todolist.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name="users")
public class Users {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@NotBlank(message="O nome não pode estar vazio")
	@Size(min=3, message="O nome deve ter no mínimo 3 caracteres")
	@Column(name="name", length=255, nullable=false)
	private String name;
	
	@NotBlank(message = "O e-mail não pode estar vazio")
	@Email(message = "O e-mail informado é inválido")
	@Column(name="email", length=255, nullable=false)
	private String email;
	
	@NotBlank(message = "A senha não pode estar vazia")
	@Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres")
	@Column(name="password", length=255, nullable=false)
	private String password;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime created_at;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}


}
