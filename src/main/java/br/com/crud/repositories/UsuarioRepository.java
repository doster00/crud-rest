package br.com.crud.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.crud.entities.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

	public Usuario findByEmail(String email);

}
