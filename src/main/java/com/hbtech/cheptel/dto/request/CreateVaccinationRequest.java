package com.hbtech.cheptel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

import java.time.LocalDate;

public class CreateVaccinationRequest {

    @Schema(example = "Vaccin Clavelée")
    private String vaccineName;

    @Schema(example = "Vivant atténué")
    private String vaccineType;

    @Schema(example = "SAIDAL")
    private String manufacturer;

    @Schema(example = "LOT-2024-0087")
    private String batchNumber;

    @Schema(example = "2024-05-20")
    private LocalDate vaccinationDate;

    @Schema(example = "2025-05-20")
    private LocalDate expirationDate;

    public String getVaccineName() { return vaccineName; }
    public void setVaccineName(String vaccineName) { this.vaccineName = vaccineName; }

    public String getVaccineType() { return vaccineType; }
    public void setVaccineType(String vaccineType) { this.vaccineType = vaccineType; }

    public String getManufacturer() { return manufacturer; }
    public void setManufacturer(String manufacturer) { this.manufacturer = manufacturer; }

    public String getBatchNumber() { return batchNumber; }
    public void setBatchNumber(String batchNumber) { this.batchNumber = batchNumber; }

    public LocalDate getVaccinationDate() { return vaccinationDate; }
    public void setVaccinationDate(LocalDate vaccinationDate) { this.vaccinationDate = vaccinationDate; }

    public LocalDate getExpirationDate() { return expirationDate; }
    public void setExpirationDate(LocalDate expirationDate) { this.expirationDate = expirationDate; }
}
