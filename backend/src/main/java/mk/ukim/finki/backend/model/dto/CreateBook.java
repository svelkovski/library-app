package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;

public record CreateBook(
        @NotBlank String name,
        @NotBlank Category category,
        @NotNull Long authorId,
        @NotBlank BookState bookState,
        @NotNull @PositiveOrZero Integer availableCopies
) {
}
