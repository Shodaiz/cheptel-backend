package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true, nullable = false)
    private String username;

    @Column(nullable = false, length = 191)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Column(name = "phone")
    private String phone;

    @Column(name = "is_active")
    private boolean active = true;

    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    @Builder.Default
    private List<Farm> farms = new ArrayList<>();

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();

    @Column(name = "updated_at")
    private LocalDateTime updatedAt;

    public String getPhoneNumber() {
        return phone;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phone = phoneNumber;
    }

    public boolean isEnabled() {
        return active;
    }

    public void setEnabled(boolean enabled) {
        this.active = enabled;
    }

    @Transient
    public Farm getFarm() {
        return farms == null || farms.isEmpty() ? null : farms.get(0);
    }

    public void setFarm(Farm farm) {
        if (this.farms == null) {
            this.farms = new ArrayList<>();
        }
        this.farms.clear();
        if (farm != null) {
            farm.setOwner(this);
            this.farms.add(farm);
        }
    }

    @PrePersist
    public void prePersist() {
        if (createdAt == null) createdAt = LocalDateTime.now();
        if (updatedAt == null) updatedAt = LocalDateTime.now();
        if (firstName == null || firstName.isBlank()) firstName = username;
        if (lastName == null || lastName.isBlank()) lastName = "-";
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}
