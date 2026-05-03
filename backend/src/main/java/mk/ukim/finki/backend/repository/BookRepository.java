package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.domain.Book;
import mk.ukim.finki.backend.model.projections.BookDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long>, JpaSpecificationExecutor<Book> {

    Optional<BookDetails> findBookDetailsById(Long id);
}
