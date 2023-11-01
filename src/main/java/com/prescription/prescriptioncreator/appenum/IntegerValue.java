package com.prescription.prescriptioncreator.appenum;

public enum IntegerValue {
    LONG_DELAY(5000),
    SHORT_DELAY(3000),
    LONG_FADE_IN_DELAY(1000),
    SHORT_FADE_IN_DELAY(500),
    LONG_FADE_OUT_DELAY(1000),
    SHORT_FADE_OUT_DELAY(500),
    SUCCESS(200),
    ERROR(500);
    private Integer value;

    public Integer val() {
        return value;
    }

    IntegerValue(Integer value){
        this.value = value;
    }
}
