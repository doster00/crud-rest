package br.com.crud.dtos;

import java.io.Serializable;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@EqualsAndHashCode
public abstract class DtoPadrao implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long id;

}
