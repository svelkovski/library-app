package mk.ukim.finki.backend.web.controller;

import lombok.RequiredArgsConstructor;
import mk.ukim.finki.backend.model.domain.ActivityLog;
import mk.ukim.finki.backend.service.ActivityLogService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/activity-logs")
@RequiredArgsConstructor
public class ActivityLogController {

    private final ActivityLogService activityLogService;

    @GetMapping
    public ResponseEntity<Page<ActivityLog>> getActivityLogs(Pageable pageable) {
        Page<ActivityLog> logs = activityLogService.getActivityLogs(pageable);
        return ResponseEntity.ok(logs);
    }
}
