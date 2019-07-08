package com.myfitnesspal.shared.model.v1;

import java.util.Calendar;

public class Measurement extends DatabaseObject {
    private Calendar entryDate;
    private String measurementTypeName;
    private String sourceClientId;
    private Float value;

    public int itemType() {
        return 8;
    }

    public String getMeasurementTypeName() {
        return this.measurementTypeName;
    }

    public void setMeasurementTypeName(String str) {
        this.measurementTypeName = str;
    }

    public Calendar getEntryDate() {
        return this.entryDate;
    }

    public void setEntryDate(Calendar calendar) {
        this.entryDate = calendar;
    }

    public Float getValue() {
        return this.value;
    }

    public void setValue(Float f) {
        this.value = f;
    }

    public String getSourceClientId() {
        return this.sourceClientId;
    }

    public void setSourceClientId(String str) {
        this.sourceClientId = str;
    }
}
