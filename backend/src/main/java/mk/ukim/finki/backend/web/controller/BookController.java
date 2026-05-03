package mk.ukim.finki.backend.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.BookFilter;
import mk.ukim.finki.backend.model.dto.CreateBook;
import mk.ukim.finki.backend.model.dto.DisplayBook;
import mk.ukim.finki.backend.model.dto.UpdateBook;
import mk.ukim.finki.backend.model.enums.BookState;
import mk.ukim.finki.backend.model.enums.Category;
import mk.ukim.finki.backend.service.BookService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<Page<DisplayBook>> getAll(
            @RequestParam(required = false) Category category,
            @RequestParam(required = false) BookState state,
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) Boolean availableOnly,
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable) {

        BookFilter filter = new BookFilter(category, state, authorId, availableOnly);

        return ResponseEntity.ok(bookService.getAll(filter, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisplayBook> getById(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.getById(id));
    }

    @PostMapping
    public ResponseEntity<DisplayBook> create(@Valid @RequestBody CreateBook req) {
        DisplayBook created = bookService.create(req);
        return ResponseEntity
                .created(URI.create("/api/books/" + created.id()))
                .body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisplayBook> update(@PathVariable Long id, @Valid @RequestBody UpdateBook req) {
        DisplayBook updated = bookService.update(id, req);
        return ResponseEntity.ok(updated);
    }

    @PostMapping("/{id}/rent")
    public ResponseEntity<DisplayBook> rent(@PathVariable Long id) {
        return ResponseEntity.ok(bookService.rent(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        bookService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
