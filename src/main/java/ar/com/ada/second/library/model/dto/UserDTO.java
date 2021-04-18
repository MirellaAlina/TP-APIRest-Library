package ar.com.ada.second.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UserDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "name contains invalid characters")
    private String name;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "name contains invalid characters")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    @PastOrPresent(message = "the birthday must be past or present")
    private Date birthday;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "biography contains not allowed characters")
    private String address;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "name contains invalid characters")
    private String educationLevel;

    @NotBlank(message = "is required")
    private Boolean isStudying;

    @NotBlank(message = "is required")
    private Boolean isWorking;

    public Boolean hasNullOrEmptyAttributes() {
        return name == null || name.trim().isEmpty()
                || lastName == null || lastName.trim().isEmpty()
                || birthday == null
                || address == null || address.trim().isEmpty()
                || educationLevel == null || educationLevel.trim().isEmpty()
                || isStudying == null
                || isWorking == null;

    }
}
