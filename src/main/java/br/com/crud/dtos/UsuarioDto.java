package br.com.crud.dtos;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioDto extends DtoPadrao {

	private static final long serialVersionUID = 1L;

	private String nome;
	private String email;
	private String senha;
	private List<PermissaoDto> permissoes;

}
