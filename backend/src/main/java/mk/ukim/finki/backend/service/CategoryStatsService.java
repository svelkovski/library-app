package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.view.CategoryStats;
import mk.ukim.finki.backend.repository.CategoryStatsRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CategoryStatsService {

    private final CategoryStatsRepository categoryStatsRepository;

    public Page<CategoryStats> getAll(Pageable pageable) {
        return categoryStatsRepository.findAll(pageable);
    }

    @Transactional
    public void refresh() {
        categoryStatsRepository.refresh();
    }
}
