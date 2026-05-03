package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.Author;
import mk.ukim.finki.backend.model.domain.Book;
import mk.ukim.finki.backend.model.dto.CreateBook;
import mk.ukim.finki.backend.model.dto.DisplayBook;
import mk.ukim.finki.backend.model.dto.UpdateBook;
import mk.ukim.finki.backend.model.exception.AuthorNotFoundException;
import mk.ukim.finki.backend.model.exception.BookNotFoundException;
import mk.ukim.finki.backend.repository.AuthorRepository;
import mk.ukim.finki.backend.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final AuthorRepository authorRepository;

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

    public List<DisplayBook> getAll() {
        return bookRepository.findAll()
                .stream()
                .map(DisplayBook::from)
                .toList();
    }

    public DisplayBook getById(Long id) {
        return bookRepository.findById(id)
                .map(DisplayBook::from)
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
}
