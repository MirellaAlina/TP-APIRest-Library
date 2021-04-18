package ar.com.ada.second.library.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@Embeddable
public class AuthorHasBookId implements Serializable {

    @Column(name = "Author_id")
    private Long authorId;

    @Column(name = "Book_id")
    private Long bookId;
}
