package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.view.CategoryStats;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryStatsRepository extends JpaRepository<CategoryStats, Long> {

    @Modifying
    @Query(value = "refresh materialized view category_stats", nativeQuery = true)
    void refresh();
}
