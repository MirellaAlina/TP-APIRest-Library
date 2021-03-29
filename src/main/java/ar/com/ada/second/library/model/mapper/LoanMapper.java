package ar.com.ada.second.library.model.mapper;

import ar.com.ada.second.library.model.dto.LoanDTO;
import ar.com.ada.second.library.model.entity.Loan;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper (componentModel = "spring")
public interface LoanMapper extends DataMapper<LoanDTO, Loan>{
    LoanMapper MAPPER = Mappers.getMapper(LoanMapper.class);
}
