package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedWeightLossEntry implements MfpNewsFeedPartnerActivityEntryData {
    @Expose
    private String applicationConnectUri;
    @Expose
    private String applicationId;
    @Expose
    private String applicationName;
    @Expose
    private String text;

    public String getApplicationId() {
        return this.applicationId;
    }

    public String getApplicationName() {
        return this.applicationName;
    }

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedWeightLossEntry setApplicationId(String str) {
        this.applicationId = str;
        return this;
    }

    public MfpNewsFeedWeightLossEntry setApplicationName(String str) {
        this.applicationName = str;
        return this;
    }

    public MfpNewsFeedWeightLossEntry setText(String str) {
        this.text = str;
        return this;
    }

    public String getApplicationConnectUri() {
        return this.applicationConnectUri;
    }

    public void setApplicationConnectUri(String str) {
        this.applicationConnectUri = str;
    }
}
