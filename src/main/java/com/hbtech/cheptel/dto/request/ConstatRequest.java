package com.hbtech.cheptel.dto.request;

import com.hbtech.cheptel.entity.ConstatType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ConstatRequest {

    @Schema(example = "1")
    private Long farmId;

    @Schema(example = "2")
    private Long controlSessionId;

    @Schema(example = "FRAUDE", allowableValues = {"FRAUDE", "MANQUANT", "DOUBLON", "AUTRE"})
    private ConstatType type;

    @Schema(example = "Animal non enregistré dans le système détecté sur site")
    private String description;

    @Schema(example = "36.7372")
    private Double latitude;

    @Schema(example = "3.0865")
    private Double longitude;

    @Schema(example = "Ferme El Oued, parcelle nord")
    private String localisationText;

    @Schema(example = "[\"https://storage.example.com/photos/img1.jpg\", \"https://storage.example.com/photos/img2.jpg\"]")
    private List<String> imageUrls;
}
