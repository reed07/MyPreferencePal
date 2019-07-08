package com.myfitnesspal.shared.event;

public class PerformanceTimerStopEvent extends MfpEventBase {
    private final String name;

    public PerformanceTimerStopEvent(String str) {
        this.name = str;
    }

    public String getName() {
        return this.name;
    }
}
