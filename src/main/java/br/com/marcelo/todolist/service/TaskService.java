package br.com.marcelo.todolist.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.marcelo.todolist.dto.TaskDTO;
import br.com.marcelo.todolist.dto.TaskResponseDTO;
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
	
	public Task addNewTask(TaskDTO taskDTO) {		
		Users user = usersRepository.findById(taskDTO.getUser_id())
				.orElseThrow(() -> new UsersNotFoundException("Código usuário " + taskDTO.getUser_id() + " não encontrado.")); 
		
		Task new_task = new Task(
			taskDTO.getTitle(),
			taskDTO.getDescription(),
			taskDTO.getPriority(),
			taskDTO.getStatus(),
			user
		);
		
		return taskRepository.save(new_task);				
	}
}
