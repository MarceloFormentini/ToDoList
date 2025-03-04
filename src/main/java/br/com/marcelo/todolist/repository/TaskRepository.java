package br.com.marcelo.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marcelo.todolist.model.Task;

public interface TaskRepository extends CrudRepository<Task, Integer>{

}
