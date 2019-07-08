package com.myfitnesspal.shared.event;

public class SearchTermTooShortEvent extends MfpEventBase {
    private final int minLength;

    public SearchTermTooShortEvent(int i) {
        this.minLength = i;
    }

    public int getMinLength() {
        return this.minLength;
    }
}
