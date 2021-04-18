package ar.com.ada.second.library.model.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.Year;
import java.util.Date;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "User")
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;
    
    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date birthday;

    @Column(nullable = false)
    private String address;

    @Column(nullable = false)
    private String educationLevel;

    @Column(nullable = false)
    private Boolean isStudying;

    @Column(nullable = false)
    private Boolean isWorking;

    @OneToMany(mappedBy = "user")
    private Set<Loan> loans;
}
