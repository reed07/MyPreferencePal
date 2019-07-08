package com.myfitnesspal.shared.event;

import com.myfitnesspal.shared.service.userdata.UserWeightService.WeightType;

public class DialogWeightEvent extends MfpEventBase {
    private String startingWeightDate;
    private float weight;
    private final WeightType weightType;

    public DialogWeightEvent(float f, WeightType weightType2) {
        this(f, weightType2, "");
    }

    public DialogWeightEvent(float f, WeightType weightType2, String str) {
        this.weight = f;
        this.weightType = weightType2;
        this.startingWeightDate = str;
    }

    public float getWeight() {
        return this.weight;
    }

    public WeightType getWeightType() {
        return this.weightType;
    }

    public String getStartingWeightDate() {
        return this.startingWeightDate;
    }
}
