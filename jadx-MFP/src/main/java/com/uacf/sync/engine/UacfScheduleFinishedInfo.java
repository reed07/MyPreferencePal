package com.uacf.sync.engine;

public class UacfScheduleFinishedInfo<TScheduleGroup> {
    private final UacfScheduleException lastError;
    private final TScheduleGroup scheduleGroup;
    private final String scheduleId;
    private final int statusCode;
    private final String statusMessage;
    private final boolean successful;

    public UacfScheduleFinishedInfo(String str, TScheduleGroup tschedulegroup, UacfScheduleException uacfScheduleException, boolean z, int i, String str2) {
        this.scheduleId = str;
        this.scheduleGroup = tschedulegroup;
        this.successful = z;
        this.statusCode = i;
        this.statusMessage = str2;
        this.lastError = uacfScheduleException;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public TScheduleGroup getScheduleGroup() {
        return this.scheduleGroup;
    }

    public boolean isSuccessful() {
        return this.successful;
    }

    public int getStatusCode() {
        return this.statusCode;
    }

    public String getStatusMessage() {
        return this.statusMessage;
    }

    public UacfScheduleException getLastError() {
        return this.lastError;
    }
}
