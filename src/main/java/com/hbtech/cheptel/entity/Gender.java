package com.hbtech.cheptel.entity;

public enum Gender {
    Male,
    Female,
    Unknown;

    public static final Gender MALE = Male;
    public static final Gender FEMALE = Female;
    public static final Gender UNKNOWN = Unknown;

    public static Gender fromInput(String value) {
        if (value == null) {
            return null;
        }
        return switch (value.trim().toUpperCase()) {
            case "MALE", "M" -> Male;
            case "FEMALE", "F" -> Female;
            case "UNKNOWN" -> Unknown;
            default -> Gender.valueOf(value.trim());
        };
    }
}
