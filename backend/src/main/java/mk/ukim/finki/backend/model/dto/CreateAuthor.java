package mk.ukim.finki.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateAuthor(
        @NotBlank String name,
        @NotBlank String surname,
        @NotNull Long countryId
) {
}
