package br.com.crud.controllers;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.entities.User;
import br.com.crud.services.UserService;

@RestController
@RequestMapping("api/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping	
	public ResponseEntity<List<User>> listAll() {
		List<User> users = userService.listAll();
		return CollectionUtils.isEmpty(users) ? ResponseEntity.notFound().build() : ResponseEntity.ok(users);
	}

	@GetMapping("/{id}")
	public ResponseEntity<User> findById(@PathVariable Long id) throws Exception {
		User user = userService.findById(id);
		return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
	}

	@PostMapping
	public ResponseEntity<Object> save(@Valid @RequestBody User user) throws Exception {
		User userSaved = userService.save(user);
		return ResponseEntity.status(HttpStatus.CREATED).body(userSaved);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) throws Exception {
		userService.delete(codigo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable Long id, @Valid @RequestBody User user) throws Exception {
		User userSaved = userService.update(id, user);
		return ResponseEntity.ok(userSaved);
	}

	/*
	 * Getters e Setters
	 */

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

}
