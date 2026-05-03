package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public record CreateCountry(
        @NotBlank String name,
        @NotBlank String continent
) {
}
