package mk.ukim.finki.backend.model.domain;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table(name = "books")
public class Book extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Category category;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id", nullable = false)
    private Author author;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private BookState bookState;

    @Column(nullable = false)
    private Integer availableCopies;
}
