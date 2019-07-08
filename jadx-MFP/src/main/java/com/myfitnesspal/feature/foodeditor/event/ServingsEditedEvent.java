package com.myfitnesspal.feature.foodeditor.event;

import com.myfitnesspal.shared.model.v2.MfpServingSize;

public class ServingsEditedEvent {
    private float numServings;
    private int selectedFoodIndex;
    private MfpServingSize servingSize;

    public ServingsEditedEvent(MfpServingSize mfpServingSize, float f) {
        this(mfpServingSize, f, 0);
    }

    public ServingsEditedEvent(float f) {
        this(null, f, 0);
    }

    public ServingsEditedEvent(float f, int i) {
        this(null, f, i);
    }

    private ServingsEditedEvent(MfpServingSize mfpServingSize, float f, int i) {
        this.servingSize = mfpServingSize;
        this.numServings = f;
        this.selectedFoodIndex = i;
    }

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }

    public float getNumServings() {
        return this.numServings;
    }

    public int getSelectedFoodIndex() {
        return this.selectedFoodIndex;
    }
}
