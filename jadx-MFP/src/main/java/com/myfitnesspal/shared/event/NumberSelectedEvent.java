package com.myfitnesspal.shared.event;

public class NumberSelectedEvent extends MfpEventBase {
    private int day;

    public NumberSelectedEvent(int i) {
        this.day = i;
    }

    public int getDay() {
        return this.day;
    }
}
