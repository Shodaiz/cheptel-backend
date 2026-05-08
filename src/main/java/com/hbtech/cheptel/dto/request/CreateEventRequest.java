package com.hbtech.cheptel.dto.request;

import com.hbtech.cheptel.entity.EventType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreateEventRequest {

    @Schema(example = "DEATH")
    private EventType eventType;

    @Schema(example = "2024-06-01T14:30:00")
    private LocalDateTime eventDate;

    @Schema(example = "36.7372")
    private Double latitude;

    @Schema(example = "3.0865")
    private Double longitude;

    @Schema(example = "Ferme principale, enclos 3")
    private String location;

    @Schema(example = "Mort naturelle, animal âgé")
    private String notes;
}
