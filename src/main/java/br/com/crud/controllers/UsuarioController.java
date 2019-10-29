package br.com.crud.controllers;

import java.util.ArrayList;
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

import br.com.crud.dtos.UsuarioDto;
import br.com.crud.entities.Usuario;
import br.com.crud.services.UsuarioService;
import br.com.crud.utils.DtosEntitiesConverter;

@RestController
@RequestMapping("api/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@Autowired
	private DtosEntitiesConverter<Usuario, UsuarioDto> converter;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listAll() {
		List<Usuario> usuarios = usuarioService.listarTodos();
		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
		usuarios.forEach(usuario -> usuariosDto.add(converter.convertToDto(usuario, new UsuarioDto())));

		return CollectionUtils.isEmpty(usuariosDto) ? ResponseEntity.notFound().build()
				: ResponseEntity.ok(usuariosDto);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		return usuario != null ? ResponseEntity.ok(converter.convertToDto(usuario, new UsuarioDto()))
				: ResponseEntity.notFound().build();
	}

	@PostMapping("/signup")
	public ResponseEntity<UsuarioDto> signup(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuarioSalvo = usuarioService.salvar(converter.convertToEntity(new Usuario(), usuarioDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToDto(usuarioSalvo, new UsuarioDto()));
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuarioSalvo = usuarioService.salvar(converter.convertToEntity(new Usuario(), usuarioDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(converter.convertToDto(usuarioSalvo, new UsuarioDto()));
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) throws Exception {
		usuarioService.excluir(codigo);
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto)
			throws Exception {
		Usuario usuarioSalvo = usuarioService.atualizar(id, converter.convertToEntity(new Usuario(), usuarioDto));
		return ResponseEntity.ok(converter.convertToDto(usuarioSalvo, new UsuarioDto()));
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
