package com.myfitnesspal.shared.event;

public abstract class MfpEventWithStatus extends MfpEventBase {
    private int errorCode;
    private String statusText;

    public int getErrorCode() {
        return this.errorCode;
    }

    public MfpEventWithStatus withErrorCode(int i) {
        this.errorCode = i;
        return this;
    }

    public String getStatusText() {
        return this.statusText;
    }

    public MfpEventWithStatus withStatusText(String str) {
        this.statusText = str;
        return this;
    }
}
