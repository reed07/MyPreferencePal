package com.uacf.sync.engine;

public class UacfScheduleEnqueuedInfo<TScheduleGroup> {
    private final TScheduleGroup scheduleGroup;
    private final String scheduleId;

    public UacfScheduleEnqueuedInfo(String str, TScheduleGroup tschedulegroup) {
        this.scheduleId = str;
        this.scheduleGroup = tschedulegroup;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public TScheduleGroup getScheduleGroup() {
        return this.scheduleGroup;
    }
}
