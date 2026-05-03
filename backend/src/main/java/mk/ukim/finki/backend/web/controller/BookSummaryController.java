package mk.ukim.finki.backend.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.view.BookSummary;
import mk.ukim.finki.backend.service.BookSummaryService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/book-summaries")
@RequiredArgsConstructor
public class BookSummaryController {

    private final BookSummaryService bookSummaryService;

    @GetMapping
    public ResponseEntity<Page<BookSummary>> getAll(
            @PageableDefault(size = 20, sort = "name")
            Pageable pageable
    ) {
        return ResponseEntity.ok(bookSummaryService.getAll(pageable));
    }
}
