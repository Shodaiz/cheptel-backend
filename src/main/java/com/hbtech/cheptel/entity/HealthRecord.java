package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "health_records")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class HealthRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Enumerated(EnumType.STRING)
    @Column(name = "record_type", nullable = false)
    private RecordType recordType;

    @Column(columnDefinition = "TEXT")
    private String diagnosis;

    @Column(columnDefinition = "TEXT")
    private String symptoms;

    @Column(name = "treatment_plan", columnDefinition = "TEXT")
    private String treatment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "veterinarian_id")
    private User veterinarian;

    @Column(name = "visit_date")
    private LocalDateTime visitDate;

    @Column(name = "next_visit_date")
    private java.time.LocalDate nextVisitDate;

    @Column(name = "is_validated")
    private Boolean validated = false;

    @Column(name = "geo_latitude")
    private java.math.BigDecimal geoLatitude;

    @Column(name = "geo_longitude")
    private java.math.BigDecimal geoLongitude;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }

        if (validated == null) {
            validated = false;
        }
    }

    public LocalDateTime getNextVisitDate() {
        return nextVisitDate == null ? null : nextVisitDate.atStartOfDay();
    }

    public void setNextVisitDate(LocalDateTime nextVisitDate) {
        this.nextVisitDate = nextVisitDate == null ? null : nextVisitDate.toLocalDate();
    }
}
