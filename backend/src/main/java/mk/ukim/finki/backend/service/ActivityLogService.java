package mk.ukim.finki.backend.service;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.ActivityLog;
import mk.ukim.finki.backend.repository.ActivityLogRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityLogService {

    private final ActivityLogRepository activityLogRepository;

    public Page<ActivityLog> getActivityLogs(Pageable pageable) {
        return activityLogRepository.findAll(pageable);
    }
}
