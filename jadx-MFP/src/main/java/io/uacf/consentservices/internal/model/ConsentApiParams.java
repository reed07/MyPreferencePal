package io.uacf.consentservices.internal.model;

public class ConsentApiParams {
    private boolean isContent;
    private boolean isExpanded;
    private String isoCode;

    public ConsentApiParams(boolean z, boolean z2, String str) {
        this.isContent = z2;
        this.isExpanded = z;
        this.isoCode = str;
    }

    public boolean isExpanded() {
        return this.isExpanded;
    }

    public boolean isContent() {
        return this.isContent;
    }

    public String getIsoCode() {
        return this.isoCode;
    }
}
