package com.myfitnesspal.shared.event;

public class SyncServiceEventBase {
    private final String syncId;

    public SyncServiceEventBase(String str) {
        this.syncId = str;
    }

    public String getSyncId() {
        return this.syncId;
    }
}
