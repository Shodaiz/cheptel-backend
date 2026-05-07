package com.hbtech.cheptel.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "rfid_tags")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RfidTag {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "rfid_code", nullable = false, unique = true, length = 100)
    private String rfidCode;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag_type")
    private TagType tagType = TagType.UHF;

    @Enumerated(EnumType.STRING)
    @Column(name = "tag_status")
    private TagStatus tagStatus = TagStatus.InStock;

    @Column(name = "created_at")
    private LocalDateTime createdAt = LocalDateTime.now();
}
