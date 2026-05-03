package mk.ukim.finki.backend.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import mk.ukim.finki.backend.service.CategoryStatsService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class CategoryStatsRefresher {

    private final CategoryStatsService categoryStatsService;

    @Scheduled(fixedRate = 3600000)
    public void refreshCategoryStats() {
        log.info("Starting category stats materialized view refresh");
        long start = System.currentTimeMillis();

        try {
            categoryStatsService.refresh();
            long duration = System.currentTimeMillis() - start;
            log.info("Finished category stats refresh in {} ms", duration);
        } catch (Exception e) {
            log.error("Error refreshing category stats: {}", e.getMessage());
        }
    }
}
