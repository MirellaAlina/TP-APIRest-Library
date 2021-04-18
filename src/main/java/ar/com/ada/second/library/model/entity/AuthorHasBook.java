package ar.com.ada.second.library.model.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
@ToString
@Entity
@Table(name = "Author_has_Book")
public class AuthorHasBook implements Serializable {

    @EmbeddedId
    private AuthorHasBookId id;

    @ManyToOne
    @MapsId("authorId")
    @JoinColumn(name = "Author_id", foreignKey = @ForeignKey(name = "fk_Author_has_Book_Author"))
    private Author author;

    @ManyToOne
    @MapsId("bookId")
    @JoinColumn(name = "Book_id", foreignKey = @ForeignKey(name = "fk_Author_has_Book_Book"))
    private Book book;
}
