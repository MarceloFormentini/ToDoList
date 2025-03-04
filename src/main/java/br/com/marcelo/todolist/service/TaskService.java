package br.com.marcelo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcelo.todolist.dto.TaskResponse;
import br.com.marcelo.todolist.exception.TaskDescriptionInvalidException;
import br.com.marcelo.todolist.exception.TaskPriorityInvalidException;
import br.com.marcelo.todolist.exception.TaskStatusInvalidException;
import br.com.marcelo.todolist.exception.TaskTitleInvalidException;
import br.com.marcelo.todolist.exception.UsersNotFoundException;
import br.com.marcelo.todolist.model.Task;
import br.com.marcelo.todolist.model.Users;
import br.com.marcelo.todolist.repository.TaskRepository;
import br.com.marcelo.todolist.repository.UsersRepository;

@Service
public class TaskService {

	@Autowired
	private TaskRepository taskRepository;
	
	@Autowired
	private UsersRepository usersRepository;
	
	public TaskResponse addNewTask(Task newTask) {
		if (newTask.getTitle() == null || newTask.getTitle().trim().isEmpty()) {
			throw new TaskTitleInvalidException("O título não pode estar vazia");
		}
		
		if (newTask.getDescription() == null || newTask.getDescription().trim().isEmpty()) {
			throw new TaskDescriptionInvalidException("A descrição não pode estar vazia");
		}
		
		if (newTask.getPriority() == null || newTask.getPriority().trim().isEmpty()) {
			throw new TaskPriorityInvalidException("A prioridade não pode estar vazia");
		}
		
		if (newTask.getStatus() == null || newTask.getStatus().trim().isEmpty()) {
			throw new TaskStatusInvalidException("O status não pode estar vazio");
		}
		
		Users user = usersRepository.findById(newTask.getUser_id().getId()).orElse(null); 

		if (user == null) {
			throw new UsersNotFoundException("Código usuário " + newTask.getUser_id().getId() + " não encontrado.");
		}
		
		newTask.setUser_id(user);
		Task new_task = taskRepository.save(newTask);
				
		return new TaskResponse(
			new_task.getTitle(),
			new_task.getDescription(),
			new_task.getPriority(),
			new_task.getCreated_at()
		);				
	}
}
