package ar.com.ada.second.library.service;

import ar.com.ada.second.library.component.BusinessLogicExceptionComponent;
import ar.com.ada.second.library.model.dto.AuthorDTO;
import ar.com.ada.second.library.model.dto.LoanDTO;
import ar.com.ada.second.library.model.entity.Author;
import ar.com.ada.second.library.model.entity.Loan;
import ar.com.ada.second.library.model.mapper.AvoidingMappingContext;
import ar.com.ada.second.library.model.mapper.LoanMapper;
import ar.com.ada.second.library.model.repository.AuthorRepository;
import ar.com.ada.second.library.model.repository.LoanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LoanService implements Services<LoanDTO, Loan>{

    private LoanMapper loanMapper = LoanMapper.MAPPER;

    @Autowired
    private BusinessLogicExceptionComponent logicExceptionComponent;

    @Autowired
    private AvoidingMappingContext context;

    @Autowired
    private LoanRepository loanRepository;

    @Override
    public LoanDTO createNew(LoanDTO dto) {
        Loan loan = loanMapper.toEntity(dto, context);

        loanRepository.save(loan);

        LoanDTO loanSaved = loanMapper.toDTO(loan, context);

        return loanSaved;
    }

    @Override
    public List<LoanDTO> getAll() {

        List<Loan> loansList = loanRepository.findAll();

        List<LoanDTO> loanDTOS = loanMapper.toDTO(loansList, context);

        return loanDTOS;
    }

    @Override
    public LoanDTO getById(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);

        Loan loan = loanOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Loan", id));

        LoanDTO loanDTO = loanMapper.toDTO(loan, context);

        return loanDTO;
    }

    @Override
    public LoanDTO update(LoanDTO dto, Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);

        Loan loanById = loanOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Loan", id));

        mergeData(loanById, dto);

        loanRepository.save(loanById);

        LoanDTO loanUpdated = loanMapper.toDTO(loanById, context);

        return loanUpdated;
    }

    @Override
    public void remove(Long id) {
        Optional<Loan> loanOptional = loanRepository.findById(id);

        Loan loanById = loanOptional
                .orElseThrow(() -> logicExceptionComponent.getExceptionEntityNotFound("Loan", id));

        loanRepository.deleteById(id);
    }

    @Override
    public void mergeData(Loan entity, LoanDTO dto) {
        if (dto.hasNullOrEmptyAttributes())
            throw logicExceptionComponent.getExceptionEntityEmptyValues("Loan");

        if (!entity.getLoanDate().equals(dto.getLoanDate()))
            entity.setLoanDate(dto.getLoanDate());

        if (!entity.getReturnDate().equals(dto.getReturnDate()))
            entity.setReturnDate(dto.getReturnDate());
    }
}
