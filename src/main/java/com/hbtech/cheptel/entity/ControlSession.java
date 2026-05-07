package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "control_sessions")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ControlSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "controller_id", nullable = false)
    private User controller;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @Column(name = "expected_count")
    private Integer expectedCount;

    @Transient
    private Integer detectedCount;

    @Transient
    private Integer missingCount;

    @Transient
    private Integer unknownCount;

    @ElementCollection
    @CollectionTable(name = "control_session_scanned_tags", joinColumns = @JoinColumn(name = "session_id"))
    @Column(name = "rfid_tag")
    private List<String> scannedTags = new ArrayList<>();

    @Column(name = "started_at")
    private LocalDateTime startedAt = LocalDateTime.now();

    @Column(name = "ended_at")
    private LocalDateTime endedAt;

    private String result = "EN_COURS";

    private java.math.BigDecimal latitude;

    private java.math.BigDecimal longitude;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    public User getControleur() {
        return controller;
    }

    public void setControleur(User controleur) {
        this.controller = controleur;
    }
}
