package com.uacf.sync.engine;

public class UacfScheduleFailedInfo<TScheduleGroup> {
    private final Exception exception;
    private final TScheduleGroup scheduleGroup;
    private final String scheduleId;

    public UacfScheduleFailedInfo(String str, TScheduleGroup tschedulegroup, Exception exc) {
        this.scheduleId = str;
        this.scheduleGroup = tschedulegroup;
        this.exception = exc;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public TScheduleGroup getScheduleGroup() {
        return this.scheduleGroup;
    }

    public Exception getException() {
        return this.exception;
    }
}
