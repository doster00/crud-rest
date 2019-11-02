package br.com.crud.mapper;

import br.com.crud.dtos.UsuarioDto;
import br.com.crud.entities.Usuario;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface UsuarioMapper {

    UsuarioMapper INSTANCE = Mappers.getMapper(UsuarioMapper.class);

    Usuario dtoToDomain(UsuarioDto usuarioDto);
    UsuarioDto domainToDto(Usuario usuario);
    List<UsuarioDto> toUsuariosDto(List<Usuario> usuarios);

}
