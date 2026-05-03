package mk.ukim.finki.backend.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.dto.CreateCountry;
import mk.ukim.finki.backend.model.dto.DisplayCountry;
import mk.ukim.finki.backend.service.CountryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api/countries")
@RequiredArgsConstructor
public class CountryController {

    private final CountryService countryService;

    @GetMapping
    public ResponseEntity<List<DisplayCountry>> getAll() {
        return ResponseEntity.ok(countryService.getAll());
    }

    @PostMapping
    public ResponseEntity<DisplayCountry> create(@Valid @RequestBody CreateCountry req) {
        DisplayCountry created = countryService.create(req);
        return ResponseEntity
                .created(URI.create("/api/countries/" + created.id()))
                .body(created);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        countryService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
