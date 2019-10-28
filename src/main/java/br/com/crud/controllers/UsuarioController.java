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

import br.com.crud.entities.Usuario;
import br.com.crud.services.UsuarioService;

@RestController
@RequestMapping("api/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<Usuario>> listAll() {
		List<Usuario> usuarios = usuarioService.listarTodos();
		return CollectionUtils.isEmpty(usuarios) ? ResponseEntity.notFound().build() : ResponseEntity.ok(usuarios);
	}

	@GetMapping("/{id}")
	public ResponseEntity<Usuario> findById(@PathVariable Long id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		return usuario != null ? ResponseEntity.ok(usuario) : ResponseEntity.notFound().build();
	}

	@PostMapping("/signup")
	public void signup(@RequestBody Usuario usuario) {
		System.out.println("aqui o cara se cadastra");
	}

	@PostMapping
	public ResponseEntity<Object> save(@Valid @RequestBody Usuario usuario) throws Exception {
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioSalvo);
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) throws Exception {
		usuarioService.excluir(codigo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Usuario> update(@PathVariable Long id, @Valid @RequestBody Usuario usuario) throws Exception {
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuario);
		return ResponseEntity.ok(usuarioSalvo);
	}

	/*
	 * Getters e Setters
	 */

	public UsuarioService getUserService() {
		return usuarioService;
	}

	public void setUserService(UsuarioService userService) {
		this.usuarioService = userService;
	}

}
