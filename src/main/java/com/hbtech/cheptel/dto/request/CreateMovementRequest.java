package com.hbtech.cheptel.dto.request;

import com.hbtech.cheptel.entity.MovementType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class CreateMovementRequest {

    @Schema(example = "1")
    private Long animalId;

    @Schema(example = "TRANSFER", allowableValues = {"SALE", "TRANSFER", "SLAUGHTER", "DEATH"})
    private MovementType movementType;

    @Schema(example = "1")
    private Long fromFarmId;

    @Schema(example = "2")
    private Long toFarmId;

    @Schema(example = "2024-06-01T08:00:00")
    private LocalDateTime movementDate;

    @Schema(example = "85000.00")
    private BigDecimal price;

    @Schema(example = "Ferme El Oued")
    private String counterpartyName;

    @Schema(example = "0661234567")
    private String counterpartyPhone;

    @Schema(example = "DOC-2024-0042")
    private String documentReference;

    @Schema(example = "36.7372")
    private Double latitude;

    @Schema(example = "3.0865")
    private Double longitude;

    @Schema(example = "Transfert suite à vente")
    private String notes;

    public Long getAnimalId() { return animalId; }
    public void setAnimalId(Long animalId) { this.animalId = animalId; }

    public MovementType getMovementType() { return movementType; }
    public void setMovementType(MovementType movementType) { this.movementType = movementType; }

    public Long getFromFarmId() { return fromFarmId; }
    public void setFromFarmId(Long fromFarmId) { this.fromFarmId = fromFarmId; }

    public Long getToFarmId() { return toFarmId; }
    public void setToFarmId(Long toFarmId) { this.toFarmId = toFarmId; }

    public LocalDateTime getMovementDate() { return movementDate; }
    public void setMovementDate(LocalDateTime movementDate) { this.movementDate = movementDate; }

    public BigDecimal getPrice() { return price; }
    public void setPrice(BigDecimal price) { this.price = price; }

    public String getCounterpartyName() { return counterpartyName; }
    public void setCounterpartyName(String counterpartyName) { this.counterpartyName = counterpartyName; }

    public String getCounterpartyPhone() { return counterpartyPhone; }
    public void setCounterpartyPhone(String counterpartyPhone) { this.counterpartyPhone = counterpartyPhone; }

    public String getDocumentReference() { return documentReference; }
    public void setDocumentReference(String documentReference) { this.documentReference = documentReference; }

    public Double getLatitude() { return latitude; }
    public void setLatitude(Double latitude) { this.latitude = latitude; }

    public Double getLongitude() { return longitude; }
    public void setLongitude(Double longitude) { this.longitude = longitude; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
