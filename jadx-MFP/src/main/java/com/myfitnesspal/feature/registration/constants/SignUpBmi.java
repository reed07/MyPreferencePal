package com.myfitnesspal.feature.registration.constants;

public enum SignUpBmi {
    Under(-1),
    Normal(0),
    Over(1);
    
    int value;

    private SignUpBmi(int i) {
        this.value = i;
    }

    public int toInt() {
        return this.value;
    }

    public static SignUpBmi fromInt(int i) {
        if (i == Under.toInt()) {
            return Under;
        }
        if (i == Normal.toInt()) {
            return Normal;
        }
        if (i == Over.toInt()) {
            return Over;
        }
        throw new IllegalArgumentException("value");
    }
}
