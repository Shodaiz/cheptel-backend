package com.hbtech.cheptel.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    // ✅ controleur_id n'existe pas dans la table constats en BD
    // Le contrôleur est accessible via controlSession.getController()
    // On garde @Transient pour la compatibilité du service mais on le peuple depuis la session
    @Transient
    private User controleur;

    // ✅ farm_id n'existe pas dans la table constats en BD
    // La ferme est accessible via controlSession.getFarm()
    @Transient
    private Farm farm;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "control_session_id")
    private ControlSession controlSession;

    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @Column(name = "description", nullable = false, columnDefinition = "TEXT")
    private String description;

    @Transient
    private BigDecimal latitude;

    @Transient
    private BigDecimal longitude;

    @Transient
    private String localisationText;

    @Transient
    private String photoUrl;

    @Transient
    private String voiceMemoUrl;

    @Transient
    private String documentUrl;

    // ✅ attachmentsJson n'est pas en BD — on le garde @Transient pour l'API
    @Transient
    private String attachmentsJson;

    @Column(name = "status", length = 30)
    private String status;

    @Column(name = "created_at")
    private LocalDateTime createdAt;

    @Column(name = "resolved_at")
    private LocalDateTime resolvedAt;

    // ── Getters dérivés : controleur et farm via la session ──────────────────

    /**
     * ✅ Retourne le contrôleur depuis la session si non défini explicitement.
     * Cela résout l'incohérence : pas de controleur_id en BD, mais le service en a besoin.
     */
    public User getControleur() {
        if (controleur != null) return controleur;
        if (controlSession != null) return controlSession.getController();
        return null;
    }

    /**
     * ✅ Retourne la ferme depuis la session si non définie explicitement.
     */
    public Farm getFarm() {
        if (farm != null) return farm;
        if (controlSession != null) return controlSession.getFarm();
        return null;
    }

    // ── Getters/Setters Double ↔ BigDecimal ──────────────────────────────────

    public Double getLatitude() {
        return latitude == null ? null : latitude.doubleValue();
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude == null ? null : BigDecimal.valueOf(latitude);
    }

    public Double getLongitude() {
        return longitude == null ? null : longitude.doubleValue();
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude == null ? null : BigDecimal.valueOf(longitude);
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (status == null) status = "PENDING";
    }
}