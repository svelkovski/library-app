package mk.ukim.finki.backend.event.listener;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.backend.event.BookRentedEvent;
import mk.ukim.finki.backend.model.domain.ActivityLog;
import mk.ukim.finki.backend.model.domain.UnavailableBooksLog;
import mk.ukim.finki.backend.model.enums.ActivityType;
import mk.ukim.finki.backend.repository.ActivityLogRepository;
import mk.ukim.finki.backend.repository.UnavailableBooksLogRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
@Slf4j
public class BookOutOfStockListener {

    private final UnavailableBooksLogRepository unavailableBooksLogRepository;
    private final ActivityLogRepository activityLogRepository;

    @TransactionalEventListener(condition = "#event.availableCopies() == 0")
    public void onOutOfStock(BookRentedEvent event) {
        log.warn("Book is now out of stock - id: {}, name: {}", event.id(), event.name());

        UnavailableBooksLog logEntry = UnavailableBooksLog
                .builder()
                .bookId(event.id())
                .bookName(event.name())
                .build();

        ActivityLog activityLog = ActivityLog
                .builder()
                .bookName(event.name())
                .activityType(ActivityType.BOOK_OUT_OF_STOCK)
                .build();

        unavailableBooksLogRepository.save(logEntry);
        activityLogRepository.save(activityLog);
    }
}
