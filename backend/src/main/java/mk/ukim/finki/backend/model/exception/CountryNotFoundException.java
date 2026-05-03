package mk.ukim.finki.backend.model.exception;

public class CountryNotFoundException extends RuntimeException {
    public CountryNotFoundException(Long id) {
        super("Country with id: %d does not exist".formatted(id));
    }
}
