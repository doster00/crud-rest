package br.com.crud.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tb_permissao")
public class Permissao extends EntidadePadrao {

	private static final long serialVersionUID = 1L;

	@Column(name = "descricao")
	private String descricao;

}
