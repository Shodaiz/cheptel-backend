package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "movements")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Movement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "animal_id", nullable = false)
    private Animal animal;

    @Transient
    private MovementType movementType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_farm_id", nullable = false)
    private Farm fromFarm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "to_farm_id", nullable = false)
    private Farm toFarm;

    @Column(name = "reason")
    private String reason;

    @Transient
    private BigDecimal price;

    @Transient
    @Column(name = "counterparty_name")
    private String counterpartyName;

    @Transient
    @Column(name = "counterparty_phone")
    private String counterpartyPhone;

    @Transient
    @Column(name = "document_reference")
    private String documentReference;

    @Transient
    private Double latitude;

    @Transient
    private Double longitude;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "approved_by")
    private User approvedBy;

    @Enumerated(EnumType.STRING)
    @Column(name = "approval_status")
    private ApprovalStatus approvalStatus = ApprovalStatus.Pending;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    private java.time.LocalDateTime createdAt = java.time.LocalDateTime.now();

    @Transient
    public java.time.LocalDateTime getMovementDate() {
        return createdAt;
    }

    public void setMovementDate(java.time.LocalDateTime movementDate) {
        this.createdAt = movementDate;
    }

    @Transient
    public User getPerformedBy() {
        return approvedBy;
    }

    public void setPerformedBy(User performedBy) {
        this.approvedBy = performedBy;
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = java.time.LocalDateTime.now();
        if (approvalStatus == null) approvalStatus = ApprovalStatus.Pending;
        if (reason == null && movementType != null) reason = movementType.name();
    }
}
