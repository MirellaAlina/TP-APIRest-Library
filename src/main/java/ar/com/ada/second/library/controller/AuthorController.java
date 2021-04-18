package ar.com.ada.second.library.controller;

import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "authors")
public class AuthorController {

    @Autowired
    private AuthorService authorService;

    @GetMapping({ "/", "" })
    public ResponseEntity getAuthorsMethod(){

        List<AuthorDTO> authors = authorService.getAll();

        return ResponseEntity
                .ok()
                .body(authors);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity getAuthorByIdMethod(@PathVariable Long id){

        AuthorDTO authorById = authorService.getById(id);

        return ResponseEntity
                .ok()
                .body(authorById);
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postAuthorMethod(@Valid @RequestBody AuthorDTO dto) throws URISyntaxException {

        AuthorDTO newAuthor = authorService.createNew(dto);

        URI uri = new URI("/author/" + newAuthor.getId());

        return ResponseEntity
                .created(uri)
                .body(newAuthor);
    }

    @PatchMapping({ "/{id}", "/{id}/" })
    public ResponseEntity patchAuthorByIdMethod(@RequestBody AuthorDTO dto, @PathVariable Long id){

        AuthorDTO authorUpdated = authorService.update(dto, id);

        return ResponseEntity
                .ok()
                .body(authorUpdated);

    }

    @DeleteMapping({ "/{id}", "/{id}/" })
    public ResponseEntity deleteAuthorByIdMethod(@PathVariable Long id){

        authorService.remove(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
