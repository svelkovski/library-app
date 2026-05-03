package mk.ukim.finki.backend.event;

public record BookRentedEvent(
        Long id,
        String name,
        Integer availableCopies
) {
}
