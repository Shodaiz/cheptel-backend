package com.hbtech.cheptel.dto.request;

import com.hbtech.cheptel.entity.RecordType;
import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDateTime;

public class CreateHealthRecordRequest {

    @Schema(example = "Checkup", allowableValues = {"Vaccination", "Treatment", "Disease", "Checkup", "Surgery", "LabTest", "Injury"})
    private RecordType recordType;

    @Schema(example = "Légère anémie détectée")
    private String diagnosis;

    @Schema(example = "Fatigue, perte d'appétit")
    private String symptoms;

    @Schema(example = "Fer injectable 2ml pendant 5 jours")
    private String treatment;

    @Schema(example = "2024-06-01T09:00:00")
    private LocalDateTime visitDate;

    @Schema(example = "2024-06-15T09:00:00")
    private LocalDateTime nextVisitDate;

    public RecordType getRecordType() { return recordType; }
    public void setRecordType(RecordType recordType) { this.recordType = recordType; }

    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }

    public String getSymptoms() { return symptoms; }
    public void setSymptoms(String symptoms) { this.symptoms = symptoms; }

    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }

    public LocalDateTime getVisitDate() { return visitDate; }
    public void setVisitDate(LocalDateTime visitDate) { this.visitDate = visitDate; }

    public LocalDateTime getNextVisitDate() { return nextVisitDate; }
    public void setNextVisitDate(LocalDateTime nextVisitDate) { this.nextVisitDate = nextVisitDate; }
}
