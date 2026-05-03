package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;

public record BookFilter(
        Category category,
        BookState bookState,
        Long authorId,
        Boolean available
) {
}
