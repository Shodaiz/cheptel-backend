package com.hbtech.cheptel.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "animals", indexes = {
        @Index(name = "idx_animals_rfid_tag_id", columnList = "rfid_tag_id", unique = true)
})
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Animal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rfid_tag_id", unique = true)
    private RfidTag rfidTagEntity;

    @Transient
    private String rfidTag;

    @Transient
    private String qrCode;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Species species;

    private String breed;

    @Enumerated(EnumType.STRING)
    private Gender gender;

    private BigDecimal weight;

    @Column(name = "color", length = 50)
    private String color;

    @Column(name = "birth_date")
    private LocalDate birthDate;

    @Column(name = "birth_place")
    private String birthPlace;

    @Column(name = "acquisition_place")
    private String acquisitionPlace;

    @Enumerated(EnumType.STRING)
    @Column(name = "life_status", nullable = false)
    @Builder.Default
    private AnimalStatus status = AnimalStatus.ACTIVE;

    @Enumerated(EnumType.STRING)
    @Column(name = "health_status")
    @Builder.Default
    private HealthStatus healthStatus = HealthStatus.HEALTHY;

    @Enumerated(EnumType.STRING)
    @Column(name = "origin_type")
    @Builder.Default
    private OriginType originType = OriginType.Born;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "owner_id", nullable = false)
    private User owner;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "farm_id", nullable = false)
    private Farm farm;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mother_id")
    private Animal mother;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "father_id")
    private Animal father;

    @Transient
    @Builder.Default
    private Boolean isActive = true;

    @Transient
    private LocalDateTime archivedAt;

    @Transient
    @Builder.Default
    private ReproductionStatus reproductionStatus = ReproductionStatus.NONE;

    @Column(columnDefinition = "TEXT")
    private String notes;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    @Builder.Default
    private LocalDateTime updatedAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (isActive == null) isActive = true;
        if (status == null) status = AnimalStatus.ACTIVE;
        if (healthStatus == null) healthStatus = HealthStatus.HEALTHY;
        if (originType == null) originType = OriginType.Born;
        if (owner == null && farm != null) owner = farm.getOwner();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
        if (owner == null && farm != null) owner = farm.getOwner();
    }

    public String getRfidTag() {
        if (rfidTagEntity != null) {
            return rfidTagEntity.getRfidCode();
        }
        return rfidTag;
    }

    public void setRfidTag(String rfidTag) {
        this.rfidTag = rfidTag;
    }

    public Double getWeight() {
        return weight == null ? null : weight.doubleValue();
    }

    public void setWeight(Double weight) {
        this.weight = weight == null ? null : BigDecimal.valueOf(weight);
    }

    public BigDecimal getWeightValue() {
        return weight;
    }

    public void setWeightValue(BigDecimal weight) {
        this.weight = weight;
    }
}
