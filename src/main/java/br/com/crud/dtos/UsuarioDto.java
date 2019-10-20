package br.com.crud.dtos;

public class UsuarioDto {

	private String login;
	private String password;

	public UsuarioDto() {

	}

	public UsuarioDto(String login, String password) {
		this.login = login;
		this.password = password;
	}

	/*
	 * Getters e Setters
	 */

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
