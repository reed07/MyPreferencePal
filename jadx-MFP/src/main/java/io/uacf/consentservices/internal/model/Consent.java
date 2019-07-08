package io.uacf.consentservices.internal.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Consent {
    @SerializedName("contentSummary")
    @Expose
    private String contentSummary;
    @SerializedName("contentUrl")
    @Expose
    private String contentUrl;
    @Expose
    private String id;
    private boolean isAccepted;
    private boolean isRequired;
    @Expose
    private String title;

    public String getId() {
        return this.id;
    }

    public String getTitle() {
        return this.title;
    }

    public String getContentSummary() {
        return this.contentSummary;
    }

    public String getContentUrl() {
        return this.contentUrl;
    }

    public boolean isRequired() {
        return this.isRequired;
    }

    public void setRequired(boolean z) {
        this.isRequired = z;
    }

    public boolean isAccepted() {
        return this.isAccepted;
    }

    public void setAccepted(boolean z) {
        this.isAccepted = z;
    }
}
