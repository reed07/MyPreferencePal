package com.myfitnesspal.shared.service.syncv2;

public class StartSyncEvent {
    private SyncType syncType;

    public StartSyncEvent() {
        this(SyncType.Incremental);
    }

    public StartSyncEvent(SyncType syncType2) {
        this.syncType = syncType2;
    }

    public SyncType getScheduleType() {
        return this.syncType;
    }
}
