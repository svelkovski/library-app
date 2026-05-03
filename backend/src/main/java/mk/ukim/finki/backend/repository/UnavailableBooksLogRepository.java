package mk.ukim.finki.backend.repository;

import mk.ukim.finki.backend.model.domain.UnavailableBooksLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UnavailableBooksLogRepository extends JpaRepository<UnavailableBooksLog, Long> {
}
