package mk.ukim.finki.backend.model.projections;

import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;

public interface BookDetails {
    Long getId();

    String getName();

    Category getCategory();

    BookState getBookState();

    Integer getAvailableCopies();

    AuthorInfo getAuthor();

    interface AuthorInfo {
        String getName();

        String getSurname();

        CountryInfo getCountry();

        interface CountryInfo {
            String getName();
        }
    }
}
