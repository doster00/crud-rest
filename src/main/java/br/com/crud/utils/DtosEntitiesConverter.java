package br.com.crud.utils;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import br.com.crud.dtos.DtoPadrao;
import br.com.crud.entities.EntidadePadrao;

@Component
public class DtosEntitiesConverter<T extends EntidadePadrao, E extends DtoPadrao> {

	public E convertToDto(T entidade, E dto) {
		BeanUtils.copyProperties(entidade, dto);
		return dto;
	}

	public T convertToEntity(T entidade, E dto) {
		BeanUtils.copyProperties(dto, entidade);
		return entidade;
	}

}
