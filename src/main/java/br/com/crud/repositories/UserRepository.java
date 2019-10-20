package br.com.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByEmail(String email);

	User findByLogin(String login);

}
