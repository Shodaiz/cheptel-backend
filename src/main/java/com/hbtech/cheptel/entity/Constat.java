package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "constats")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Constat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Transient
    private User controleur;

    @Transient
    private Farm farm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "control_session_id")
    private ControlSession controlSession;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Column(name = "latitude")
    private BigDecimal latitude;

    @Column(name = "longitude")
    private BigDecimal longitude;

    @Column(name = "localisation_text", length = 255)
    private String localisationText;

    @Column(name = "photo_url", length = 500)
    private String photoUrl;

    @Column(name = "voice_memo_url", length = 500)
    private String voiceMemoUrl;

    @Column(name = "document_url", length = 500)
    private String documentUrl;

    @Transient
    private String attachmentsJson;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    @PrePersist
    public void prePersist() {
        if (createdAt == null) {
            createdAt = LocalDateTime.now();
        }
        if (status == null) {
            status = "PENDING";
        }
    }

    public Double getLatitude() {
        return latitude == null ? null : latitude.doubleValue();
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude == null ? null : BigDecimal.valueOf(latitude);
    }

    public BigDecimal getLatitudeValue() {
        return latitude;
    }

    public void setLatitudeValue(BigDecimal latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude == null ? null : longitude.doubleValue();
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude == null ? null : BigDecimal.valueOf(longitude);
    }

    public BigDecimal getLongitudeValue() {
        return longitude;
    }

    public void setLongitudeValue(BigDecimal longitude) {
        this.longitude = longitude;
    }
}
