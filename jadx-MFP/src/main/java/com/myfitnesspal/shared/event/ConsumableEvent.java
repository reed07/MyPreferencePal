package com.myfitnesspal.shared.event;

public class ConsumableEvent {
    private boolean consumed;

    public void consume() {
        this.consumed = true;
    }

    public boolean consumed() {
        return this.consumed;
    }
}
