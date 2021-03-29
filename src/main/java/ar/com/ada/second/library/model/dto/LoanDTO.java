package ar.com.ada.second.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class LoanDTO implements Serializable {

    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    @PastOrPresent(message = "the loanDate must be past or present")
    private Date loanDate;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    @PastOrPresent(message = "the returnDate must be past or present")
    private Date returnDate;

    public Boolean hasNullOrEmptyAttributes() {
        return loanDate == null
                || returnDate == null;
    }

}
