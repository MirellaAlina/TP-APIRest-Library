package ar.com.ada.second.library.model.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import java.io.Serializable;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AuthorDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "name contains invalid characters")
    private String name;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "lastName contains invalid characters")
    private String lastName;

    @JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "is required")
    @PastOrPresent(message = "the birthday must be past or present")
    private Date birthday;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "birthPlace contains invalid characters")
    private String birthPlace;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "biography contains not allowed characters")
    private String biography;

    public Boolean hasNullOrEmptyAttributes() {
        return name == null || name.trim().isEmpty()
                || lastName == null || lastName.trim().isEmpty()
                || birthday == null
                || birthPlace == null || birthPlace.trim().isEmpty()
                || biography == null || biography.trim().isEmpty();

    }
}
