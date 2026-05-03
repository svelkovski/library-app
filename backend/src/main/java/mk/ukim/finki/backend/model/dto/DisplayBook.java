package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.domain.Book;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;

public record DisplayBook(
        Long id,
        String name,
        Category category,
        DisplayAuthor author,
        BookState bookState,
        Integer availableCopies
) {
    public static DisplayBook from(Book book) {
        return new DisplayBook(
                book.getId(),
                book.getName(),
                book.getCategory(),
                DisplayAuthor.from(book.getAuthor()),
                book.getBookState(),
                book.getAvailableCopies()
        );
    }
}