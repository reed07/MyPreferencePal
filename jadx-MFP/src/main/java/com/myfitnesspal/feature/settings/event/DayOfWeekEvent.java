package com.myfitnesspal.feature.settings.event;

import com.myfitnesspal.shared.event.MfpEventBase;

public class DayOfWeekEvent extends MfpEventBase {
    private String dayOfWeek;

    public DayOfWeekEvent(String str) {
        this.dayOfWeek = str;
    }

    public String getDayOfWeek() {
        return this.dayOfWeek;
    }
}
