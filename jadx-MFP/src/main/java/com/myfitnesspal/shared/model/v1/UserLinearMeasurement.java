package com.myfitnesspal.shared.model.v1;

public class UserLinearMeasurement extends DatabaseObject {
    private float inches;
    private boolean isForHeight;

    public UserLinearMeasurement() {
    }

    public UserLinearMeasurement(float f, boolean z) {
        this.inches = f;
        this.isForHeight = z;
    }

    public float getInches() {
        return this.inches;
    }

    public void setInches(float f) {
        this.inches = f;
    }

    public boolean isForHeight() {
        return this.isForHeight;
    }

    public void setIsForHeight(boolean z) {
        this.isForHeight = z;
    }

    public UserLinearMeasurement initWithInches(float f) {
        this.inches = f;
        this.isForHeight = false;
        return this;
    }

    public float centimetres() {
        return (float) (((double) this.inches) * 2.54d);
    }
}
