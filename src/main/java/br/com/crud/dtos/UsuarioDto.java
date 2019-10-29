package br.com.crud.dtos;

import java.util.List;

public class UsuarioDto extends DtoPadrao {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String nome;
	private String email;
	private String senha;
	private List<PermissaoDto> permissoes;

	/*
	 * Gettes e Setters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public List<PermissaoDto> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<PermissaoDto> permissoes) {
		this.permissoes = permissoes;
	}

}
