package br.com.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.crud.entities.User;
import br.com.crud.exceptions.SystemException;
import br.com.crud.repositories.UserRepository;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;

	public List<User> listAll() {
		return userRepository.findAll();
	}

	public User findById(Long id) throws SystemException {
		Optional<User> user = userRepository.findById(id);

		if (!user.isPresent()) {
			throw new SystemException("User not found");
		}

		return user.get();
	}

	public User save(User user) throws SystemException {
		checkUser(user);
		return userRepository.save(user);
	}

	public void delete(Long id) throws SystemException {
		User user = findById(id);
		userRepository.delete(user);
	}

	public User update(Long id, User user) throws SystemException {
		User userSaved = findById(id);
		BeanUtils.copyProperties(user, userSaved, "id");
		return userRepository.save(userSaved);
	}

	private void checkUser(User user) throws SystemException {
		User userSaved = userRepository.findByEmail(user.getEmail());

		if (userSaved != null) {
			throw new SystemException("Email already exists");
		}

		userSaved = userRepository.findByLogin(user.getLogin());

		if (userSaved != null) {
			throw new SystemException("Login already exists");
		}
	}

	/*
	 * Getters e Setters
	 */

	public UserRepository getUserRepository() {
		return userRepository;
	}

	public void setUserRepository(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

}
