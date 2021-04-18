package ar.com.ada.second.library.controller;

import ar.com.ada.second.library.model.dto.BookDTO;
import ar.com.ada.second.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class BookController {

    @Autowired
    private BookService bookService;

    @GetMapping({ "/books", "/books/" })
    public ResponseEntity getBookMethod(){

        List<BookDTO> books = bookService.getAll();

        return ResponseEntity
                .ok()
                .body(books);
    }

    @GetMapping({ "/books/{id}", "/books/{id}/" })
    public ResponseEntity getBookByIdMethod(@PathVariable Long id){

        BookDTO bookById = bookService.getById(id);

        return ResponseEntity
                .ok()
                .body(id);
    }

    @PostMapping({ "/authors/{authorId}/books", "/authors/{authorsId}/books/" })
    public ResponseEntity postBookMethod(@Valid @RequestBody BookDTO dto,
                                         @PathVariable Long authorId) throws URISyntaxException {

        BookDTO bookSaved = bookService.createNew(dto);

        URI uri = new URI("/book/" + bookSaved.getId());

        return ResponseEntity
                .created(uri)
                .body(bookSaved);
    }

    @PatchMapping({ "/authors/{authorId}/books/{bookId}", "/authors/{authorId}/books/{bookId}/" })
    public ResponseEntity patchBookByIdMethod(@RequestBody BookDTO dto,
                                              @PathVariable Long authorId,
                                              @PathVariable Long bookId){

        BookDTO bookUpdated = bookService.update(dto, authorId, bookId);

        return ResponseEntity
                .ok()
                .body(bookUpdated);
    }

    @DeleteMapping({ "/books/{id}", "/books/{id}/" })
    public ResponseEntity deleteBookByIdMethod(@PathVariable Long id){

        bookService.remove(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}

