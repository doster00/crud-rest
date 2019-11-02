package br.com.crud.dtos;

import lombok.Data;

import java.util.List;

@Data
public class UsuarioDto extends DtoPadrao {
	private String nome;
	private String email;
	private String senha;
	private List<PermissaoDto> permissoes;
}
