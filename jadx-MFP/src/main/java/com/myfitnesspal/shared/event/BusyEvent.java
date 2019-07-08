package com.myfitnesspal.shared.event;

import com.myfitnesspal.shared.constants.Constants.BusyStates;

public class BusyEvent {
    private boolean isBusy;
    private int mask;

    public BusyEvent(boolean z) {
        this(BusyStates.ALL, z);
    }

    public BusyEvent(int i, boolean z) {
        this.mask = i;
        this.isBusy = z;
    }

    public int getMask() {
        return this.mask;
    }

    public boolean isBusy() {
        return this.isBusy;
    }
}
