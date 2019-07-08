package com.myfitnesspal.feature.diary.event;

public class MoreItemEventSelected {
    private int eventId;
    private String mealName;

    public MoreItemEventSelected(int i, String str) {
        this.eventId = i;
        this.mealName = str;
    }

    public int getEventId() {
        return this.eventId;
    }

    public String getMealName() {
        return this.mealName;
    }
}
