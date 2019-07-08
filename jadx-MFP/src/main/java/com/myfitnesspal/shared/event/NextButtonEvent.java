package com.myfitnesspal.shared.event;

public class NextButtonEvent {
    private boolean enable;
    private boolean hide;

    public boolean isHidden() {
        return this.hide;
    }

    public NextButtonEvent setHidden(boolean z) {
        this.hide = z;
        return this;
    }

    public boolean isEnabled() {
        return this.enable;
    }

    public NextButtonEvent setEnabled(boolean z) {
        this.enable = z;
        return this;
    }
}
