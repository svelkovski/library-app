package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.Country;
import mk.ukim.finki.backend.model.dto.CreateCountry;
import mk.ukim.finki.backend.model.dto.DisplayCountry;
import mk.ukim.finki.backend.model.exception.CountryNotFoundException;
import mk.ukim.finki.backend.repository.CountryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CountryService {

    private final CountryRepository countryRepository;

    public DisplayCountry create(CreateCountry req) {
        Country country = Country
                .builder()
                .name(req.name())
                .continent(req.continent())
                .build();

        countryRepository.save(country);

        return DisplayCountry.from(country);
    }

    public Page<DisplayCountry> getAll(Pageable pageable) {
        return countryRepository.findAll(pageable)
                .map(DisplayCountry::from);
    }

    public void delete(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        countryRepository.delete(country);
    }
}
