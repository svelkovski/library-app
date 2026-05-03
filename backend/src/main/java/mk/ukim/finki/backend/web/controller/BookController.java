package mk.ukim.finki.backend.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.CreateBook;
import mk.ukim.finki.backend.model.dto.DisplayBook;
import mk.ukim.finki.backend.model.dto.UpdateBook;
import mk.ukim.finki.backend.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/books")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;

    @GetMapping
    public ResponseEntity<List<DisplayBook>> getAll() {
        return ResponseEntity.ok(bookService.getAll());
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
