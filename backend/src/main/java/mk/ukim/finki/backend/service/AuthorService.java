package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.Author;
import mk.ukim.finki.backend.model.domain.Country;
import mk.ukim.finki.backend.model.dto.CreateAuthor;
import mk.ukim.finki.backend.model.dto.DisplayAuthor;
import mk.ukim.finki.backend.model.exception.AuthorNotFoundException;
import mk.ukim.finki.backend.model.exception.CountryNotFoundException;
import mk.ukim.finki.backend.repository.AuthorRepository;
import mk.ukim.finki.backend.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthorService {

    private final AuthorRepository authorRepository;
    private final CountryRepository countryRepository;

    public DisplayAuthor create(CreateAuthor req) {
        Country country = countryRepository.findById(req.countryId())
                .orElseThrow(() -> new CountryNotFoundException(req.countryId()));

        Author author = Author
                .builder()
                .name(req.name())
                .surname(req.surname())
                .country(country)
                .build();

        authorRepository.save(author);

        return DisplayAuthor.from(author);
    }

    public Page<DisplayAuthor> getAll(Pageable pageable) {
        return authorRepository.findAll(pageable)
                .map(DisplayAuthor::from);
    }

    public DisplayAuthor getById(Long id) {
        return authorRepository.findById(id)
                .map(DisplayAuthor::from)
                .orElseThrow(() -> new AuthorNotFoundException(id));
    }

    public void delete(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new AuthorNotFoundException(id));

        authorRepository.delete(author);
    }
}
