package mk.ukim.finki.backend.service;

import mk.ukim.finki.backend.model.domain.Book;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;
import org.springframework.data.jpa.domain.Specification;

public class BookSpecifications {

    public static Specification<Book> hasCategory(Category category) {
        return (root, query, cb) ->
                category == null ? null : cb.equal(root.get("category"), category);
    }

    public static Specification<Book> hasBookState(BookState bookState) {
        return (root, query, cb) ->
                bookState == null ? null : cb.equal(root.get("bookState"), bookState);
    }

    public static Specification<Book> hasAuthor(Long authorId) {
        return (root, query, cb) ->
                authorId == null ? null : cb.equal(root.get("author").get("id"), authorId);
    }

    public static Specification<Book> isAvailable(Boolean availableOnly) {
        return (root, query, cb) ->
        {
            if (availableOnly == null || !availableOnly) return null;
            return cb.greaterThan(root.get("availableCopies"), 0);
        };
    }
}
