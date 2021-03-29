package ar.com.ada.second.library.model.mapper;

import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.model.entity.Author;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorMapper extends DataMapper<AuthorDTO, Author>{
    AuthorMapper MAPPER = Mappers.getMapper(AuthorMapper.class);
}
