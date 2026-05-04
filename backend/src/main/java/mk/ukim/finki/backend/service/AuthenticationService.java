package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.User;
import mk.ukim.finki.backend.model.dto.LoginRequest;
import mk.ukim.finki.backend.model.dto.LoginResponse;
import mk.ukim.finki.backend.model.dto.RegisterRequest;
import mk.ukim.finki.backend.model.dto.RegisterResponse;
import mk.ukim.finki.backend.model.enums.UserRole;
import mk.ukim.finki.backend.model.exception.EmailTakenException;
import mk.ukim.finki.backend.repository.UserRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public RegisterResponse register(RegisterRequest req) {
        if (userRepository.existsByEmail(req.email())) {
            throw new EmailTakenException(req.email());
        }

        User user = User
                .builder()
                .firstName(req.firstName())
                .lastName(req.lastName())
                .email(req.email())
                .passwordHash(passwordEncoder.encode(req.password()))
                .role(UserRole.USER)
                .build();

        userRepository.save(user);

        String jwtToken = jwtService.generateToken(user);

        return RegisterResponse.from(user, jwtToken);
    }

    public LoginResponse login(LoginRequest req) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.email(), req.password())
        );

        User user = userRepository.findByEmail(req.email())
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        String jwtToken = jwtService.generateToken(user);

        return new LoginResponse(jwtToken);
    }
}
