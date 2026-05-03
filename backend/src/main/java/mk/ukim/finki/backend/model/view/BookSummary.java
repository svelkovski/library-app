package mk.ukim.finki.backend.model.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "book_summary")
@Getter
@NoArgsConstructor
public class BookSummary {

    @Id
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Category category;

    @Enumerated(EnumType.STRING)
    @Column(name = "book_state")
    private BookState bookState;

    @Column(name = "available_copies")
    private Integer availableCopies;

    @Column(name = "author_full_name")
    private String authorFullName;

    @Column(name = "country_name")
    private String countryName;
}
