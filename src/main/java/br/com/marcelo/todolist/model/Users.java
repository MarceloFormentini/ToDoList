package br.com.marcelo.todolist.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="users")
public class Users {

	@Id
	@Column(name = "username", length = 255, nullable = false, unique = true)
	private String username;
	
	@Column(name = "name", length = 255, nullable = false)
	private String name;
	
	@Column(name = "password", length = 255, nullable = false)
	private String password;

	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}
