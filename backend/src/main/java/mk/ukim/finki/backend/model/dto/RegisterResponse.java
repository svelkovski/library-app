package mk.ukim.finki.backend.model.dto;

import mk.ukim.finki.backend.model.domain.User;
import mk.ukim.finki.backend.model.enums.UserRole;

public record RegisterResponse(
        Long id,
        String firstName,
        String lastName,
        String email,
        UserRole role,
        String jwtToken
) {
    public static RegisterResponse from(User user, String jwtToken) {
        return new RegisterResponse(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole(),
                jwtToken
        );
    }
}
