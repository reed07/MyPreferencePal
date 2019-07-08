package com.myfitnesspal.shared.event;

public class SyncServiceIncrementalFailedEvent extends SyncServiceEventBase {
    private final String message;
    private final int statusCode;

    public SyncServiceIncrementalFailedEvent(String str, int i, String str2) {
        super(str);
        this.message = str2;
        this.statusCode = i;
    }

    public String getStatusMessage() {
        return this.message;
    }

    public int getStatusCode() {
        return this.statusCode;
    }
}
