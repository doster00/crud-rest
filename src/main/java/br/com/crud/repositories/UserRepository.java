package br.com.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	public User findByEmail(String email);

}
