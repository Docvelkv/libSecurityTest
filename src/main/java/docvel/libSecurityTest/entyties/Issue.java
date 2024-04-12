package docvel.libSecurityTest.entyties;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@Table(name = "issues")
public class Issue {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "reader")
    private Reader reader;

    @ManyToOne
    @JoinColumn(name = "book")
    private Book book;

    @Column(name = "date_of_issuance")
    private LocalDate dateOfIssuance;

    @Column(name = "date_of_return")
    private LocalDate dateOfReturn;
}
