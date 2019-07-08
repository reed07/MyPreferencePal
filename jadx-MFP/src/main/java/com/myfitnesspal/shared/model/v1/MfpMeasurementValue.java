package com.myfitnesspal.shared.model.v1;

import java.util.Date;

public class MfpMeasurementValue {
    private long databaseId;
    private Date date;
    private String description;
    private String sourceClientId;
    private String uid;
    private float value;

    public long getDatabaseId() {
        return this.databaseId;
    }

    public void setDatabaseId(long j) {
        this.databaseId = j;
    }

    public String getUid() {
        return this.uid;
    }

    public void setUid(String str) {
        this.uid = str;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }

    public float getValue() {
        return this.value;
    }

    public void setValue(float f) {
        this.value = f;
    }

    public String getSourceClientId() {
        return this.sourceClientId;
    }

    public void setSourceClientId(String str) {
        this.sourceClientId = str;
    }
}
