package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.Author;
import mk.ukim.finki.backend.model.domain.Book;
import mk.ukim.finki.backend.model.dto.BookFilter;
import mk.ukim.finki.backend.model.dto.CreateBook;
import mk.ukim.finki.backend.model.dto.DisplayBook;
import mk.ukim.finki.backend.model.dto.UpdateBook;
import mk.ukim.finki.backend.model.exception.AuthorNotFoundException;
import mk.ukim.finki.backend.model.exception.BookNotFoundException;
import mk.ukim.finki.backend.model.projections.BookDetails;
import mk.ukim.finki.backend.repository.AuthorRepository;
import mk.ukim.finki.backend.repository.BookRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

    private static final Set<String> ALLOWED_SORT_FIELDS = Set.of("name", "createdAt");

    public DisplayBook create(CreateBook req) {
        Author author = authorRepository.findById(req.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(req.authorId()));

        Book book = Book
                .builder()
                .name(req.name())
                .category(req.category())
                .author(author)
                .bookState(req.bookState())
                .availableCopies(req.availableCopies())
                .build();

        bookRepository.save(book);

        return DisplayBook.from(book);
    }

    public DisplayBook update(Long id, UpdateBook req) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        Author author = authorRepository.findById(req.authorId())
                .orElseThrow(() -> new AuthorNotFoundException(req.authorId()));

        book.setName(req.name());
        book.setCategory(req.category());
        book.setAuthor(author);
        book.setBookState(req.bookState());
        book.setAvailableCopies(req.availableCopies());

        bookRepository.save(book);

        return DisplayBook.from(book);
    }

    public Page<DisplayBook> getAll(BookFilter filter, Pageable pageable) {
        validateSortField(pageable);

        Specification<Book> specification = Specification
                .where(BookSpecifications.hasCategory(filter.category()))
                .and(BookSpecifications.hasBookState(filter.bookState()))
                .and(BookSpecifications.hasAuthor(filter.authorId()))
                .and(BookSpecifications.isAvailable(filter.available()));

        return bookRepository.findAll(specification, pageable)
                .map(DisplayBook::from);
    }

    public BookDetails getById(Long id) {
        return bookRepository.findBookDetailsById(id)
                .orElseThrow(() -> new BookNotFoundException(id));
    }

    public void delete(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        bookRepository.delete(book);
    }

    public DisplayBook rent(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new BookNotFoundException(id));

        if (book.getAvailableCopies() > 0) {
            book.setAvailableCopies(book.getAvailableCopies() - 1);
            bookRepository.save(book);
        }

        return DisplayBook.from(book);
    }

    private void validateSortField(Pageable pageable) {
        pageable.getSort().forEach(order -> {
            if (!ALLOWED_SORT_FIELDS.contains(order.getProperty())) {
                throw new IllegalArgumentException("Sorting by '" + order.getProperty() + "' is not allowed.");
            }
        });
    }
}
