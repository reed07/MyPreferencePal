package com.myfitnesspal.feature.diary.event;

import java.util.Date;

public class CopyEntriesToDateEvent {
    private final String sectionName;
    private final Date targetDate;

    public CopyEntriesToDateEvent(Date date, String str) {
        this.targetDate = date;
        this.sectionName = str;
    }

    public Date getTargetDate() {
        return this.targetDate;
    }

    public String getSectionName() {
        return this.sectionName;
    }
}
