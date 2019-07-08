package com.uacf.sync.engine;

public class UacfScheduleProgressInfo<TScheduleGroup> {
    private final int progress;
    private TScheduleGroup scheduleGroup;
    private String scheduleId;
    private final String text;
    private final int total;

    public UacfScheduleProgressInfo(String str) {
        this(str, 0, 0);
    }

    public UacfScheduleProgressInfo(int i, int i2) {
        this(null, i, i2);
    }

    public UacfScheduleProgressInfo(String str, int i, int i2) {
        this.text = str;
        this.progress = i;
        this.total = i2;
    }

    public String getScheduleId() {
        return this.scheduleId;
    }

    public void setScheduleId(String str) {
        this.scheduleId = str;
    }

    public void setScheduleGroup(TScheduleGroup tschedulegroup) {
        this.scheduleGroup = tschedulegroup;
    }

    public String getText() {
        return this.text;
    }
}
