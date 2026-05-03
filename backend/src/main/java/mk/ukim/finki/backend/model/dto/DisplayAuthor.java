package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.domain.Author;

public record DisplayAuthor(
        Long id,
        String name,
        String surname,
        DisplayCountry country
) {
    public static DisplayAuthor from(Author author) {
        return new DisplayAuthor(
                author.getId(),
                author.getName(),
                author.getSurname(),
                DisplayCountry.from(author.getCountry())
        );
    }
}
