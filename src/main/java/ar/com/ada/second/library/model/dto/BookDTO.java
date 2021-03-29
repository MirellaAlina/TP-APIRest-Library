package ar.com.ada.second.library.model.dto;


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

@NoArgsConstructor
@AllArgsConstructor
@Setter @Getter
public class BookDTO implements Serializable {

    private Long id;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[0-9a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "title contains not allowed characters")
    private String title;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "editorial contains invalid characters")
    private String editorial;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "editionPlace contains invalid characters")
    private String editionPlace;

    @NotNull(message = "is required")
    @PastOrPresent(message = "the year must be past or present")
    private Year editionYear;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[0-9\\s]*$", message = "numberCopies contains not allowed characters")
    private Integer numberCopies;

    @NotBlank(message = "is required")
    @Pattern(regexp = "^[a-zA-ZáéíóúÁÉÍÓÚÜüñÑ\\s]*$", message = "editionPlace contains invalid characters")
    private String bookCondition;

    public Boolean hasNullOrEmptyAttributes() {
        return title == null || title.trim().isEmpty()
                || editorial == null || editorial.trim().isEmpty()
                || editionPlace == null || editionPlace.trim().isEmpty()
                || editionYear == null
                || numberCopies == null
                || bookCondition == null || bookCondition.trim().isEmpty();
    }
}
