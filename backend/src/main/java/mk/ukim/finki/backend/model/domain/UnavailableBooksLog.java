package mk.ukim.finki.backend.model.domain;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "unavailable_books_log")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UnavailableBooksLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "book_id", nullable = false)
    private Long bookId;

    @Column(name = "book_name", nullable = false)
    private String bookName;

    @Column(name = "log_time", nullable = false, insertable = false)
    private Instant logTime;
}
