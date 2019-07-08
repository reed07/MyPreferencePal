package com.myfitnesspal.shared.model.unitconv;

public final class MajorMinorUnits<T> {
    private final String majorDisplayValue;
    private final int majorValue;
    private final String minorDisplayValue;
    private final int minorValue;
    private final T unit;

    public MajorMinorUnits(T t, int i, String str, int i2, String str2) {
        this.unit = t;
        this.majorValue = i;
        this.majorDisplayValue = str;
        this.minorValue = i2;
        this.minorDisplayValue = str2;
    }

    public T getUnit() {
        return this.unit;
    }

    public String getMajorDisplayValue() {
        return this.majorDisplayValue;
    }

    public String getMinorDisplayValue() {
        return this.minorDisplayValue;
    }

    public int getMajorValue() {
        return this.majorValue;
    }

    public int getMinorValue() {
        return this.minorValue;
    }
}
