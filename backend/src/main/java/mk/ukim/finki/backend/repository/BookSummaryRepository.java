package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.view.BookSummary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BookSummaryRepository extends JpaRepository<BookSummary, Long> {
}
