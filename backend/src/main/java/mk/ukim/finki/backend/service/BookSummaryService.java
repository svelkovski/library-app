package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.view.BookSummary;
import mk.ukim.finki.backend.repository.BookSummaryRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BookSummaryService {

    private final BookSummaryRepository bookSummaryRepository;

    public Page<BookSummary> getAll(Pageable pageable) {
        return bookSummaryRepository.findAll(pageable);
    }
}
