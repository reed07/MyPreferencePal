package com.myfitnesspal.shared.event;

public class PerformanceTimerStartEvent extends MfpEventBase {
    private final String name;

    public PerformanceTimerStartEvent(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
