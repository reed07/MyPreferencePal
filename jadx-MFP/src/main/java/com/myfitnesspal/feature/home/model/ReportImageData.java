package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public class ReportImageData {
    @Expose
    private boolean emailCh;
    @Expose
    private String imageId;
    @Expose
    private String reason;

    public ReportImageData(boolean z, String str, String str2) {
        this.emailCh = z;
        this.reason = str;
        this.imageId = str2;
    }

    public boolean isEmailCh() {
        return this.emailCh;
    }

    public void setEmailCh(boolean z) {
        this.emailCh = z;
    }

    public String getReason() {
        return this.reason;
    }

    public void setReason(String str) {
        this.reason = str;
    }

    public String getImageId() {
        return this.imageId;
    }

    public void setImageId(String str) {
        this.imageId = str;
    }
}
