package br.com.crud.controllers;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import br.com.crud.mapper.UsuarioMapper;
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

import br.com.crud.dtos.PermissaoDto;
import br.com.crud.dtos.UsuarioDto;
import br.com.crud.entities.Permissao;
import br.com.crud.entities.Usuario;
import br.com.crud.services.UsuarioService;

@RestController
@RequestMapping("api/users")
public class UsuarioController {

	@Autowired
	private UsuarioService usuarioService;

	@GetMapping
	public ResponseEntity<List<UsuarioDto>> listAll() {
//		List<Usuario> usuarios = usuarioService.listarTodos();

		List<UsuarioDto> usuarioDtos = UsuarioMapper.INSTANCE.toUsuariosDto(usuarioService.listarTodos());

//		List<UsuarioDto> usuariosDto = new ArrayList<UsuarioDto>();
//		usuarioDtos.forEach(usuario -> usuariosDto.add(usuarioToUsuarioDto(usuario)));
		return CollectionUtils.isEmpty(usuarioDtos) ? ResponseEntity.ok().build() : ResponseEntity.ok(usuarioDtos);
	}

	@GetMapping("/{id}")
	public ResponseEntity<UsuarioDto> findById(@PathVariable Long id) throws Exception {
		Usuario usuario = usuarioService.buscarPorId(id);
		return usuario != null ? ResponseEntity.ok(usuarioToUsuarioDto(usuario)) : ResponseEntity.ok().build();
	}

	@PostMapping("/signup")
	public ResponseEntity<UsuarioDto> signup(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuario = usuarioDtoToUsuario(usuarioDto);
		Usuario usuarioSalvo = usuarioService.salvar(usuario);
		return ResponseEntity.status(HttpStatus.CREATED).body(usuarioToUsuarioDto(usuarioSalvo));
	}

	@PostMapping
	public ResponseEntity<UsuarioDto> save(@Valid @RequestBody UsuarioDto usuarioDto) throws Exception {
		Usuario usuarioSalvo = usuarioService.salvar(UsuarioMapper.INSTANCE.dtoToDomain(usuarioDto));
		return ResponseEntity.status(HttpStatus.CREATED).body(UsuarioMapper.INSTANCE.domainToDto(usuarioSalvo));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> remover(@PathVariable Long codigo) throws Exception {
		usuarioService.excluir(codigo);
		return ResponseEntity.ok().build();
	}

	@PutMapping("/{id}")
	public ResponseEntity<UsuarioDto> update(@PathVariable Long id, @Valid @RequestBody UsuarioDto usuarioDto)
			throws Exception {
		Usuario usuarioSalvo = usuarioService.atualizar(id, usuarioDtoToUsuario(usuarioDto));
		return ResponseEntity.ok(usuarioToUsuarioDto(usuarioSalvo));
	}

	// TODO melhorar depois
	private Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto) {
		Usuario usuario = new Usuario();
		usuario.setId(usuarioDto.getId());
		usuario.setEmail(usuarioDto.getEmail());
		usuario.setNome(usuarioDto.getNome());
		usuario.setSenha(usuarioDto.getSenha());

		usuario.setPermissoes(new ArrayList<Permissao>());
		usuarioDto.getPermissoes()
				.forEach(permissao -> usuario.getPermissoes().add(permissaoDtoToPermissao(permissao)));

		return usuario;
	}

	private UsuarioDto usuarioToUsuarioDto(Usuario usuario) {
		UsuarioDto usuarioDto = new UsuarioDto();
		usuarioDto.setId(usuario.getId());
		usuarioDto.setEmail(usuario.getEmail());
		usuarioDto.setNome(usuario.getNome());
		usuarioDto.setSenha(usuario.getSenha());
		usuarioDto.setPermissoes(new ArrayList<PermissaoDto>());
		usuario.getPermissoes()
				.forEach(permissao -> usuarioDto.getPermissoes().add(permissaoToPermissaoDto(permissao)));
		return usuarioDto;
	}

	private Permissao permissaoDtoToPermissao(PermissaoDto permissaoDto) {
		Permissao permissao = new Permissao();
		permissao.setId(permissaoDto.getId());
		permissao.setDescricao(permissaoDto.getDescricao());
		return permissao;
	}

	private PermissaoDto permissaoToPermissaoDto(Permissao permissao) {
		PermissaoDto permissaoDto = new PermissaoDto();
		permissaoDto.setId(permissao.getId());
		permissaoDto.setDescricao(permissao.getDescricao());
		return permissaoDto;
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
