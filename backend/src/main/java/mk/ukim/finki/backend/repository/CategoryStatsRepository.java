package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.view.CategoryStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStatsRepository extends JpaRepository<CategoryStats, Long> {
}
