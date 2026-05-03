package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.domain.Country;

public record DisplayCountry(
        Long id,
        String name,
        String continent
) {
    public static DisplayCountry from(Country country) {
        return new DisplayCountry(
                country.getId(),
                country.getName(),
                country.getContinent()
        );
    }
}
