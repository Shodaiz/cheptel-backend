package com.hbtech.cheptel.entity;

public enum Role {
    Administrator,
    Veterinarian,
    Farmer,
    Inspector;

    public static final Role ADMIN = Administrator;
    public static final Role VETERINAIRE = Veterinarian;
    public static final Role FERMIER = Farmer;
    public static final Role CONTROLEUR = Inspector;

    public static Role fromInput(String value) {
        if (value == null) {
            throw new IllegalArgumentException("Role is required");
        }
        return switch (value.trim().toUpperCase()) {
            case "ADMIN", "ADMINISTRATOR" -> Administrator;
            case "VETERINAIRE", "VETERINARIAN" -> Veterinarian;
            case "FERMIER", "FARMER" -> Farmer;
            case "CONTROLEUR", "INSPECTOR" -> Inspector;
            default -> Role.valueOf(value.trim());
        };
    }

    public String legacyAuthority() {
        return switch (this) {
            case Administrator -> "ADMIN";
            case Veterinarian -> "VETERINAIRE";
            case Farmer -> "FERMIER";
            case Inspector -> "CONTROLEUR";
        };
    }
}
