package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;
import java.util.Date;

public class MfpExerciseMetadataForSteps implements MfpExerciseMetadata {
    @Expose
    private String clientId;
    private Date date;
    @Expose
    private String deviceId;
    @Expose
    private int steps;

    public MfpExerciseMetadataForSteps() {
    }

    public MfpExerciseMetadataForSteps(int i, String str, String str2, Date date2) {
        this.steps = i;
        this.clientId = str;
        this.deviceId = str2;
        this.date = date2;
    }

    public int getSteps() {
        return this.steps;
    }

    public void setSteps(int i) {
        this.steps = i;
    }

    public String getClientId() {
        return this.clientId;
    }

    public Date getDate() {
        return this.date;
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDate(Date date2) {
        this.date = date2;
    }
}
