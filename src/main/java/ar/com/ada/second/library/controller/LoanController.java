package ar.com.ada.second.library.controller;

import ar.com.ada.second.library.model.dto.LoanDTO;
import ar.com.ada.second.library.service.LoanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping(value = "loans")
public class LoanController {

    @Autowired
    private LoanService loanService;

    @GetMapping({ "/", "" })
    public ResponseEntity getLoanMethod(){

        List<LoanDTO> loans = loanService.getAll();

        return ResponseEntity
                .ok()
                .body(loans);
    }

    @GetMapping({ "/{id}", "/{id}/" })
    public ResponseEntity getLoanByIdMethod(@PathVariable Long id){

        LoanDTO loanById = loanService.getById(id);

        return ResponseEntity
                .ok()
                .body(id);
    }

    @PostMapping({ "/", "" })
    public ResponseEntity postLoanMethod(@Valid @RequestBody LoanDTO dto) throws URISyntaxException{

        LoanDTO newLoan = loanService.createNew(dto);

        URI uri = new URI("/loan/" + newLoan.getId());

        return ResponseEntity
                .created(uri)
                .body(newLoan);
    }

    @PatchMapping({ "/{id}", "/{id}/" })
    public ResponseEntity patchLoanByIdMethod(@RequestBody LoanDTO dto, @PathVariable Long id){

        LoanDTO loanUpdated = loanService.update(dto, id);

        return ResponseEntity
                .ok()
                .body(loanUpdated);
    }

    @DeleteMapping({ "/{id}", "/{id}/" })
    public ResponseEntity deleteLoanByIdMethod(@PathVariable Long id){

        loanService.remove(id);

        return ResponseEntity
                .noContent()
                .build();
    }
}
