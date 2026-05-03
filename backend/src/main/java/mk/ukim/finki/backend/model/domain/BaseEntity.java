package mk.ukim.finki.backend.model.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@MappedSuperclass
@Getter
public class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, updatable = false, insertable = false)
    private Instant createdAt;

    @Column(nullable = false, updatable = false, insertable = false)
    private Instant updatedAt;
}
