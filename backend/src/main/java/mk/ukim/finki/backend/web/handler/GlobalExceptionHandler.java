package mk.ukim.finki.backend.web.handler;

import mk.ukim.finki.backend.model.dto.ApiError;
import mk.ukim.finki.backend.model.exception.AuthorNotFoundException;
import mk.ukim.finki.backend.model.exception.BookNotFoundException;
import mk.ukim.finki.backend.model.exception.CountryNotFoundException;
import mk.ukim.finki.backend.model.exception.EmailTakenException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.Instant;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({
            BookNotFoundException.class,
            AuthorNotFoundException.class,
            CountryNotFoundException.class
    })
    public ResponseEntity<ApiError> handleNotFound(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(new ApiError(
                        HttpStatus.NOT_FOUND.value(),
                        ex.getMessage(),
                        Instant.now()
                ));
    }

    @ExceptionHandler(EmailTakenException.class)
    public ResponseEntity<ApiError> handleTaken(RuntimeException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(new ApiError(
                        HttpStatus.CONFLICT.value(),
                        ex.getMessage(),
                        Instant.now()
                ));
    }
}
