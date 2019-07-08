package com.myfitnesspal.feature.registration.model;

import com.google.gson.annotations.Expose;

public class UpdatedTermsInfo {
    @Expose
    private String footer;
    @Expose
    private String header;
    @Expose
    private String summary;

    public UpdatedTermsInfo() {
    }

    public UpdatedTermsInfo(String str, String str2, String str3) {
        this.summary = str;
        this.header = str2;
        this.footer = str3;
    }

    public String getSummary() {
        return this.summary;
    }

    public String getHeader() {
        return this.header;
    }

    public String getFooter() {
        return this.footer;
    }
}
