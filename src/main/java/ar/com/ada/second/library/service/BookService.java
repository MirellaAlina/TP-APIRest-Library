package ar.com.ada.second.library.service;

import ar.com.ada.second.library.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.model.dto.BookDTO;
import ar.com.ada.second.library.model.entity.Author;
import ar.com.ada.second.library.model.entity.Book;
import ar.com.ada.second.library.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.library.model.mapper.BookMapper;
import ar.com.ada.second.library.model.repository.AuthorRepository;
import ar.com.ada.second.library.model.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookService implements Services<BookDTO, Book>{

    private BookMapper bookMapper = BookMapper.MAPPER;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private BookRepository bookRepository;

    @Override
    public BookDTO createNew(BookDTO dto) {
        Book book = bookMapper.toEntity(dto, context);

        bookRepository.save(book);

        BookDTO bookSaved = bookMapper.toDTO(book, context);

        return bookSaved;
    }

    @Override
    public List<BookDTO> getAll() {
        List<Book> bookList = bookRepository.findAll();

        List<BookDTO> bookDTOS = bookMapper.toDTO(bookList, context);

        return bookDTOS;
    }

    @Override
    public BookDTO getById(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        Book book = bookOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Book", id));

        BookDTO bookDTO = bookMapper.toDTO(book, context);

        return bookDTO;
    }

    @Override
    public BookDTO update(BookDTO dto, Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        Book bookById = bookOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Book", id));

        mergeData(bookById, dto);

        bookRepository.save(bookById);

        BookDTO bookUpdated = bookMapper.toDTO(bookById, context);

        return bookUpdated;
    }

    @Override
    public void remove(Long id) {
        Optional<Book> bookOptional = bookRepository.findById(id);

        Book bookById = bookOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Book", id));

        bookRepository.deleteById(id);
    }

    @Override
    public void mergeData(Book entity, BookDTO dto) {
        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Author");

        if (!entity.getTitle().equals(dto.getTitle()))
            entity.setTitle(dto.getTitle());

        if (!entity.getEditorial().equals(dto.getEditorial()))
            entity.setEditorial(dto.getEditorial());

        if (!entity.getEditionPlace().equals(dto.getEditionPlace()))
            entity.setEditionPlace(dto.getEditionPlace());

        if (!entity.getEditionYear().equals(dto.getEditionYear()))
            entity.setEditionYear(dto.getEditionYear());

        if (!entity.getNumberCopies().equals(dto.getNumberCopies()))
            entity.setNumberCopies(dto.getNumberCopies());

        if (!entity.getBookCondition().equals(dto.getBookCondition()))
            entity.setBookCondition(dto.getBookCondition());
    }
}
