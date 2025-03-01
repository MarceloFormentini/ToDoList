package br.com.marcelo.todolist.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.marcelo.todolist.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	public Users findByEmail(String email);
}
