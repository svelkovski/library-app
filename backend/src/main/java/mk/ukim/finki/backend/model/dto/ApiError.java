package mk.ukim.finki.backend.model.dto;

import java.time.Instant;

public record ApiError(
        Integer status,
        String message,
        Instant timestamp
) {
}
