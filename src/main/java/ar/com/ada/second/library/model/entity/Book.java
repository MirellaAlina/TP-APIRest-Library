package ar.com.ada.second.library.model.entity;

import ar.com.ada.second.library.model.mapper.converter.YearAttributeConverter;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Book")

public class Book implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String editorial;

    @Column(nullable = false)
    private String editionPlace;

    @Column(nullable = false, columnDefinition = "smallint")
    @Convert(converter = YearAttributeConverter.class)
    private Year editionYear;

    @Column(nullable = false)
    private Integer numberCopies;

    @Column(nullable = false)
    private String bookCondition;

}
