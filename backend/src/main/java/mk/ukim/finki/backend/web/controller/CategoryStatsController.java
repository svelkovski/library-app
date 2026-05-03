package mk.ukim.finki.backend.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.view.CategoryStats;
import mk.ukim.finki.backend.service.CategoryStatsService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/category-stats")
@RequiredArgsConstructor
public class CategoryStatsController {

    private final CategoryStatsService categoryStatsService;

    @GetMapping
    public ResponseEntity<Page<CategoryStats>> getAll(
            @PageableDefault(size = 20, sort = "category")
            Pageable pageable
    ) {
        return ResponseEntity.ok(categoryStatsService.getAll(pageable));
    }
}
