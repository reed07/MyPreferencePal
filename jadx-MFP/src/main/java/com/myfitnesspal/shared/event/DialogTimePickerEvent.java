package com.myfitnesspal.shared.event;

import java.util.Calendar;

public class DialogTimePickerEvent {
    private final Calendar calendar;
    private final long id;

    public DialogTimePickerEvent(long j, Calendar calendar2) {
        this.id = j;
        this.calendar = calendar2;
    }

    public long getId() {
        return this.id;
    }

    public Calendar getCalendar() {
        return this.calendar;
    }
}
