package mk.ukim.finki.backend.event.listener;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.event.BookRentedEvent;
import mk.ukim.finki.backend.model.domain.ActivityLog;
import mk.ukim.finki.backend.model.enums.ActivityType;
import mk.ukim.finki.backend.repository.ActivityLogRepository;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;

@Component
@RequiredArgsConstructor
public class BookRentedListener {

    private final ActivityLogRepository activityLogRepository;

    @TransactionalEventListener
    public void onBookRented(BookRentedEvent event) {
        ActivityLog log = ActivityLog
                .builder()
                .bookName(event.name())
                .activityType(ActivityType.BOOK_RENTED)
                .build();

        activityLogRepository.save(log);
    }
}
