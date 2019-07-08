package com.myfitnesspal.feature.goals.event;

public class PickerValuesUpdatedEvent {
    private boolean isTotalValid;

    public PickerValuesUpdatedEvent(boolean z) {
        this.isTotalValid = z;
    }

    public boolean isTotalValid() {
        return this.isTotalValid;
    }

    public void setTotalValid(boolean z) {
        this.isTotalValid = z;
    }
}
