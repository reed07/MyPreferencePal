package com.uacf.sync.engine;

public class UacfScheduleStartedInfo<TScheduleGroup> {
    private final String scheduleId;
    private final TScheduleGroup scheduleType;

    public UacfScheduleStartedInfo(String str, TScheduleGroup tschedulegroup) {
        this.scheduleId = str;
        this.scheduleType = tschedulegroup;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public TScheduleGroup getScheduleType() {
        return this.scheduleType;
    }
}
