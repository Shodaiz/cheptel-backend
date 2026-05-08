package com.hbtech.cheptel.dto.response;

import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConstatResponse {

    private Long id;
    private String controleurUsername;
    private Long farmId;
    private String farmName;
    private Long controlSessionId;
    private String type;
    private String description;
    private Double latitude;
    private Double longitude;
    private String localisationText;
    private List<String> imageUrls;
    private String status;
    private LocalDateTime createdAt;
}
