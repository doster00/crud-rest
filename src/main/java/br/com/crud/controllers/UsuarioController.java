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
import org.springframework.web.bind.annotation.RestController;

import br.com.crud.dtos.UsuarioDto;
import br.com.crud.entities.Usuario;
import br.com.crud.mapper.UsuarioMapper;
import br.com.crud.services.UsuarioService;

@RestController
@RequestMapping("api/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listAll() {
		List<UsuarioDto> usuarioDtos = UsuarioMapper.INSTANCE.usuariosToUsuariosDto(usuarioService.listarTodos());
		return CollectionUtils.isEmpty(usuarioDtos) ? ResponseEntity.ok().build() : ResponseEntity.ok(usuarioDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		return usuario != null ? ResponseEntity.ok(UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuario))
				: ResponseEntity.ok().build();
	}

	@PostMapping("/signup")
	public ResponseEntity<UsuarioDto> signup(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuario = UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto);
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuarioSalvo));
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuarioSalvo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo) throws Exception {
		usuarioService.excluir(codigo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto)
			throws Exception {
		Usuario usuarioSalvo = usuarioService.atualizar(id, UsuarioMapper.INSTANCE.usuarioDtoToUsuario(usuarioDto));
		return ResponseEntity.ok(UsuarioMapper.INSTANCE.usuarioToUsuarioDto(usuarioSalvo));
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
