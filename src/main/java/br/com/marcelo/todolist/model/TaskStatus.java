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
@Table(name="task_status")
public class TaskStatus {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private Integer id;
	
	@Column(name="status", length=1, nullable=false)
	private String status;
	
	@Column(name="changed_at")
	@CreationTimestamp
	private LocalDateTime changed_at;
	
	@Column(name="priority", length=1, nullable=false)
	private String priority;
	
	@ManyToOne
	@JoinColumn(name="task_id", nullable=false)
	private Task task_id;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDateTime getChanged_at() {
		return changed_at;
	}

	public void setChanged_at(LocalDateTime changed_at) {
		this.changed_at = changed_at;
	}

	public String getPriority() {
		return priority;
	}

	public void setPriority(String priority) {
		this.priority = priority;
	}

	public Task getTask_id() {
		return task_id;
	}

	public void setTask_id(Task task_id) {
		this.task_id = task_id;
	}

}
