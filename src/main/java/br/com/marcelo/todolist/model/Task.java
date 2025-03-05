package br.com.marcelo.todolist.model;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="task")
public class Task {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="title", length=100, nullable=false)
	private String title;
	
	@Column(name="description", length=255, nullable=false)
	private String description;
	
	@Column(name="priority", length=1, nullable=false)
	private String priority;
	
	@Column(name="status", length=1, nullable=false)
	private String status;
	
	@Column(name="created_at")
	@CreationTimestamp
	private LocalDateTime created_at;
	
	@ManyToOne
	@JoinColumn(name="user_id", nullable=false)
	private Users users_id;
	
	public Task(String title, String description, String priority, String status, Users users_id) {
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.users_id = users_id;
    }

    public Task() {}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public Users getUsers_id() {
		return users_id;
	}

	public void setUsers_id(Users users_id) {
		this.users_id = users_id;
	}

}
