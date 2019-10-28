package br.com.crud.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.crud.entities.Usuario;
import br.com.crud.exceptions.NegocioException;
import br.com.crud.repositories.UsuarioRepository;

@Service
public class UsuarioService {

	@Autowired
	private UsuarioRepository usuarioRepository;

	public List<Usuario> listarTodos() {
		return usuarioRepository.findAll();
	}

	public Usuario buscarPorId(Long id) throws NegocioException {
		Optional<Usuario> usuario = usuarioRepository.findById(id);

		if (!usuario.isPresent()) {
			throw new NegocioException("Usuário não encontrado");
		}

		return usuario.get();
	}

	public Usuario salvar(Usuario usuario) throws NegocioException {
		verificarUsuario(usuario);
		return usuarioRepository.save(usuario);
	}

	public void excluir(Long id) throws NegocioException {
		Usuario usuario = buscarPorId(id);
		usuarioRepository.delete(usuario);
	}

	public Usuario atualizar(Long id, Usuario usuario) throws NegocioException {
		Usuario usuarioSalvo = buscarPorId(id);
		BeanUtils.copyProperties(usuario, usuarioSalvo, "id");
		return usuarioRepository.save(usuarioSalvo);
	}

	private void verificarUsuario(Usuario usuario) throws NegocioException {
		Usuario usuarioSalvo = usuarioRepository.findByEmail(usuario.getEmail());

		if (usuarioSalvo != null) {
			throw new NegocioException("O e-mail já está sendo utilizado");
		}
	}

	public Usuario buscarPorEmail(String email) throws UsernameNotFoundException {
		Usuario usuario = usuarioRepository.findByEmail(email);

		if (usuario == null) {
			throw new UsernameNotFoundException("Usuário não encontrado");
		}

		return usuario;
	}

	/*
	 * Getters e Setters
	 */

	public UsuarioRepository getUserRepository() {
		return usuarioRepository;
	}

	public void setUserRepository(UsuarioRepository userRepository) {
		this.usuarioRepository = userRepository;
	}

}
