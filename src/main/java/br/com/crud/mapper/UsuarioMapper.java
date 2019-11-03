package br.com.crud.mapper;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import br.com.crud.dtos.UsuarioDto;
import br.com.crud.entities.Usuario;

@Mapper
public interface UsuarioMapper {

	UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

	Usuario usuarioDtoToUsuario(UsuarioDto usuarioDto);

	UsuarioDto usuarioToUsuarioDto(Usuario usuario);

	List<UsuarioDto> usuariosToUsuariosDto(List<Usuario> usuarios);

	List<Usuario> usuariosDtoToUsuarios(List<UsuarioDto> usuariosDto);

}
