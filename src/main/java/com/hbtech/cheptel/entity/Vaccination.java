package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vaccinations")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Vaccination {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private Animal animal;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "health_record_id", nullable = false)
    private HealthRecord healthRecord;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "administered_by")
    private User administeredBy;

    @Column(name = "vaccine_name", nullable = false)
    private String vaccineName;

    @Column(name = "vaccine_type")
    private String vaccineType;

    private String manufacturer;

    @Column(name = "batch_number")
    private String batchNumber;

    @Transient
    private LocalDate vaccinationDate;

    @Column(name = "expiration_date")
    private LocalDate expirationDate;

    @Column(name = "next_dose_date")
    private LocalDate nextVaccinationDate;

    private String dose;

    @Transient
    private String notes;

    @Column(name = "created_at")
    @Builder.Default
    private LocalDateTime createdAt = LocalDateTime.now();

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
    }

    public Animal getAnimal() {
        return healthRecord != null ? healthRecord.getAnimal() : animal;
    }

    public void setAnimal(Animal animal) {
        this.animal = animal;
    }

    public User getVeterinarian() {
        return administeredBy;
    }

    public void setVeterinarian(User veterinarian) {
        this.administeredBy = veterinarian;
    }
}
