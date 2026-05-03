package mk.ukim.finki.backend.model.view;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import mk.ukim.finki.backend.model.enums.Category;
import org.hibernate.annotations.Immutable;

@Entity
@Immutable
@Table(name = "category_stats")
@Getter
@NoArgsConstructor
public class CategoryStats {

    @Id
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(name = "total_books")
    private Long totalBooks;

    @Column(name = "total_available_copies")
    private Long totalAvailableCopies;

    @Column(name = "bad_books")
    private Long badBooks;
}
