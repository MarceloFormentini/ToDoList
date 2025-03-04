package br.com.marcelo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcelo.todolist.dto.TaskResponse;
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
