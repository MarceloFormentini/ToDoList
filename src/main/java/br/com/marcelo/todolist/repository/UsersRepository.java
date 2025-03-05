package br.com.marcelo.todolist.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import br.com.marcelo.todolist.model.Users;

public interface UsersRepository extends CrudRepository<Users, Integer>{
	Optional<Users> findByEmail(String email);
}
