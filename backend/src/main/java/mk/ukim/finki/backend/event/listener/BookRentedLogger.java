package mk.ukim.finki.backend.event.listener;

import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.backend.event.BookRentedEvent;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@Slf4j
public class BookRentedLogger {

    @TransactionalEventListener
    public void onBookRented(BookRentedEvent bookRentedEvent) {
        log.info("Book rented: id={}, name={}, availableCopies={}",
                bookRentedEvent.id(),
                bookRentedEvent.name(),
                bookRentedEvent.availableCopies());
    }
}
