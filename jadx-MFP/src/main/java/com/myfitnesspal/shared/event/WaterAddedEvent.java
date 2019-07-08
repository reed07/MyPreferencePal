package com.myfitnesspal.shared.event;

public class WaterAddedEvent {
    private int cups;

    public WaterAddedEvent(int i) {
        this.cups = i;
    }

    public int getCups() {
        return this.cups;
    }
}
