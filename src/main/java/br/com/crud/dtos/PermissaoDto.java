package br.com.crud.dtos;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PermissaoDto extends DtoPadrao {

	private static final long serialVersionUID = 1L;

	private String descricao;

}
