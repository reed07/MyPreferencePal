package com.myfitnesspal.feature.progress.event;

public class MeasurementValueChangeEvent {
    private final String measurementType;
    private final State state;
    private final float value;

    public enum State {
        Valid,
        Invalid,
        OutOfRange,
        Zero
    }

    public MeasurementValueChangeEvent(State state2, String str, float f) {
        this.state = state2;
        this.measurementType = str;
        this.value = f;
    }

    public float getValue() {
        return this.value;
    }

    public String getMeasurementType() {
        return this.measurementType;
    }

    public State getState() {
        return this.state;
    }
}
