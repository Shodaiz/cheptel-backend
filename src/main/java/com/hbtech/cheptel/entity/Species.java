package com.hbtech.cheptel.entity;

public enum Species {
    Ovin,
    Bovin,
    Caprin,
    Autre;

    public static final Species OVIN = Ovin;
    public static final Species BOVIN = Bovin;
    public static final Species CAPRIN = Caprin;
    public static final Species AUTRE = Autre;

    public static Species fromInput(String value) {
        if (value == null) {
            return null;
        }
        return switch (value.trim().toUpperCase()) {
            case "OVIN" -> Ovin;
            case "BOVIN" -> Bovin;
            case "CAPRIN" -> Caprin;
            case "AUTRE", "OTHER" -> Autre;
            default -> Species.valueOf(value.trim());
        };
    }
}
