package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.Country;
import mk.ukim.finki.backend.model.dto.CreateCountry;
import mk.ukim.finki.backend.model.dto.DisplayCountry;
import mk.ukim.finki.backend.model.exception.CountryNotFoundException;
import mk.ukim.finki.backend.repository.CountryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<DisplayCountry> getAll() {
        return countryRepository.findAll()
                .stream()
                .map(DisplayCountry::from)
                .toList();
    }

    public void delete(Long id) {
        Country country = countryRepository.findById(id)
                .orElseThrow(() -> new CountryNotFoundException(id));
        countryRepository.delete(country);
    }
}
