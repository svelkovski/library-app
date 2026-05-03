package mk.ukim.finki.backend.model.domain;

import jakarta.persistence.*;
import lombok.*;
import mk.ukim.finki.backend.model.enums.ActivityType;

import java.time.Instant;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ActivityLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String bookName;

    @Column(nullable = false, insertable = false)
    private Instant occurredAt;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ActivityType activityType;
}
