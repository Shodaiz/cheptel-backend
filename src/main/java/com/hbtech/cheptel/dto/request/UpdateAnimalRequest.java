package com.hbtech.cheptel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class UpdateAnimalRequest {

    @Schema(example = "Ouled Djellal")
    private String breed;

    @Schema(example = "Female", allowableValues = {"Male", "Female", "Unknown"})
    private String gender;

    @Schema(example = "48.0")
    private Double weight;

    @Schema(example = "Noir et blanc")
    private String color;

    @Schema(example = "Active", allowableValues = {"Active", "Sold", "Lost", "Dead", "Slaughtered"})
    private String status;

    @Schema(example = "2023-03-15")
    private String birthDate;

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}
