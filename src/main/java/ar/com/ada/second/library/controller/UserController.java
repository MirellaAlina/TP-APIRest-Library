package ar.com.ada.second.library.controller;

import ar.com.ada.second.library.model.dto.UserDTO;
import ar.com.ada.second.library.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "users")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping({ "/", "" })
    public ResponseEntity getUserMethod(){

        List<UserDTO> users = userService.getAll();

        return ResponseEntity
                .ok()
                .body(users);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity getUserByIdMethod(@PathVariable Long id){

        UserDTO userById = userService.getById(id);

        return ResponseEntity
                .ok()
                .body(id);
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postUserMethod(@Valid @RequestBody UserDTO dto) throws URISyntaxException{

        UserDTO newUser = userService.createNew(dto);

        URI uri = new URI("/user/" + newUser.getId());

        return ResponseEntity
                .created(uri)
                .body(newUser);
    }

    @PatchMapping({ "/{id}", "/{id}/" })
    public ResponseEntity patchUserByIdMethod(@RequestBody UserDTO dto, @PathVariable Long id){

        UserDTO userUpdated = userService.update(dto, id);

        return ResponseEntity
                .ok()
                .body(userUpdated);
    }

    @DeleteMapping({ "/{id}", "/{id}/" })
    public ResponseEntity deleteUserByIdMethod(@PathVariable Long id){

        userService.remove(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
