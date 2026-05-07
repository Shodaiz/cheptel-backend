package com.hbtech.cheptel.entity;

public enum RecordType {
    Vaccination,
    Treatment,
    Disease,
    Checkup,
    Surgery,
    LabTest,
    Injury;

    public static final RecordType VACCINATION = Vaccination;
    public static final RecordType TREATMENT = Treatment;
    public static final RecordType DISEASE = Disease;
    public static final RecordType CHECKUP = Checkup;
    public static final RecordType SURGERY = Surgery;
    public static final RecordType LAB_TEST = LabTest;
    public static final RecordType INJURY = Injury;
}
