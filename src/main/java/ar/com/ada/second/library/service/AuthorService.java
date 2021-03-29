package ar.com.ada.second.library.service;

import ar.com.ada.second.library.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.model.entity.Author;
import ar.com.ada.second.library.model.mapper.AuthorMapper;
import ar.com.ada.second.library.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.library.model.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService implements Services<AuthorDTO, Author> {

    private AuthorMapper authorMapper = AuthorMapper.MAPPER;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private AuthorRepository authorRepository;


    @Override
    public AuthorDTO createNew(AuthorDTO dto) {

        Author author = authorMapper.toEntity(dto, context);

        authorRepository.save(author);

        AuthorDTO authorSaved = authorMapper.toDTO(author, context);

        return authorSaved;

    }

    @Override
    public List<AuthorDTO> getAll() {

        List<Author> authorList = authorRepository.findAll();

        List<AuthorDTO> authorDTOS = authorMapper.toDTO(authorList, context);

        return authorDTOS;

    }

    @Override
    public AuthorDTO getById(Long id) {
        Optional<Author> authorOptional = authorRepository.findById(id);

        Author author = authorOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        AuthorDTO authorDTO = authorMapper.toDTO(author, context);

        return authorDTO;

    }

    @Override
    public AuthorDTO update(AuthorDTO dto, Long id) {

        Optional<Author> authorOptional = authorRepository.findById(id);

        Author authorById = authorOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        mergeData(authorById, dto);

       authorRepository.save(authorById);

        AuthorDTO authorUpdated = authorMapper.toDTO(authorById, context);

        return authorUpdated;
    }

    @Override
    public void remove(Long id) {

        Optional<Author> authorByIdToDelete = authorRepository.findById(id);

        Author author = authorByIdToDelete
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Author", id));

        authorRepository.deleteById(id);
    }

    @Override
    public void mergeData(Author entity, AuthorDTO dto) {
        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Author");

        if (!entity.getName().equals(dto.getName()))
            entity.setName(dto.getName());

        if (!entity.getLastName().equals(dto.getLastName()))
            entity.setLastName(dto.getLastName());

        if (!entity.getBirthday().equals(dto.getBirthday()))
            entity.setBirthday(dto.getBirthday());

        if (!entity.getBirthPlace().equals(dto.getBirthPlace()))
            entity.setBirthPlace(dto.getBirthPlace());

        if (!entity.getBiography().equals(dto.getBiography()))
            entity.setBiography(dto.getBiography());
    }
}
