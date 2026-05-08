package com.hbtech.cheptel.dto.request;

import com.hbtech.cheptel.entity.ReproductionRecordStatus;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CreateReproductionRequest {

    @Schema(example = "3")
    private Long femaleId;

    @Schema(example = "5")
    private Long maleId;

    @Schema(example = "2024-01-10")
    private LocalDate breedingDate;

    @Schema(example = "2024-06-10")
    private LocalDate expectedBirthDate;

    @Schema(example = "2024-06-08")
    private LocalDate actualBirthDate;

    @Schema(example = "2")
    private Integer offspringCount;

    @Schema(example = "SUCCESSFUL", allowableValues = {"IN_PROGRESS", "SUCCESSFUL", "FAILED", "ABORTED"})
    private ReproductionRecordStatus status;

    @Schema(example = "Mise bas sans complications")
    private String notes;

    public Long getFemaleId() { return femaleId; }
    public void setFemaleId(Long femaleId) { this.femaleId = femaleId; }

    public Long getMaleId() { return maleId; }
    public void setMaleId(Long maleId) { this.maleId = maleId; }

    public LocalDate getBreedingDate() { return breedingDate; }
    public void setBreedingDate(LocalDate breedingDate) { this.breedingDate = breedingDate; }

    public LocalDate getExpectedBirthDate() { return expectedBirthDate; }
    public void setExpectedBirthDate(LocalDate expectedBirthDate) { this.expectedBirthDate = expectedBirthDate; }

    public LocalDate getActualBirthDate() { return actualBirthDate; }
    public void setActualBirthDate(LocalDate actualBirthDate) { this.actualBirthDate = actualBirthDate; }

    public Integer getOffspringCount() { return offspringCount; }
    public void setOffspringCount(Integer offspringCount) { this.offspringCount = offspringCount; }

    public ReproductionRecordStatus getStatus() { return status; }
    public void setStatus(ReproductionRecordStatus status) { this.status = status; }

    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}
