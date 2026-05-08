package com.hbtech.cheptel.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;

public class CreateAnimalRequest {

    @Schema(example = "DZA-2024-00123")
    private String rfidTag;

    @Schema(example = "Ovin", allowableValues = {"Ovin", "Bovin", "Caprin", "Autre"})
    private String species;

    @Schema(example = "Ouled Djellal")
    private String breed;

    @Schema(example = "Male", allowableValues = {"Male", "Female", "Unknown"})
    private String gender;

    @Schema(example = "42.5")
    private Double weight;

    @Schema(example = "Blanc")
    private String color;

    @Schema(example = "2023-03-15")
    private String birthDate;

    public String getRfidTag() { return rfidTag; }
    public void setRfidTag(String rfidTag) { this.rfidTag = rfidTag; }

    public String getSpecies() { return species; }
    public void setSpecies(String species) { this.species = species; }

    public String getBreed() { return breed; }
    public void setBreed(String breed) { this.breed = breed; }

    public String getGender() { return gender; }
    public void setGender(String gender) { this.gender = gender; }

    public Double getWeight() { return weight; }
    public void setWeight(Double weight) { this.weight = weight; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getBirthDate() { return birthDate; }
    public void setBirthDate(String birthDate) { this.birthDate = birthDate; }
}
