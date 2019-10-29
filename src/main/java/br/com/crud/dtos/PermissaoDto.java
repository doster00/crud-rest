package br.com.crud.dtos;

public class PermissaoDto extends DtoPadrao {

	private static final long serialVersionUID = 1L;

	private Long id;
	private String descricao;

	/*
	 * Gettes e Setters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

}
