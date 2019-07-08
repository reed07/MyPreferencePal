package com.myfitnesspal.feature.addentry.event;

import com.myfitnesspal.shared.model.v2.MfpServingSize;

public class EditServingsDialogCloseEvent {
    private boolean needsPatch;
    private float numOfServings;
    private MfpServingSize servingSize;
    private int servingSizeIndexForLogging;

    public EditServingsDialogCloseEvent(MfpServingSize mfpServingSize, float f, boolean z, int i) {
        this.servingSize = mfpServingSize;
        this.numOfServings = f;
        this.needsPatch = z;
        this.servingSizeIndexForLogging = i;
    }

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }

    public float getNumOfServings() {
        return this.numOfServings;
    }

    public boolean isNeedsPatch() {
        return this.needsPatch;
    }

    public int getServingSizeIndexForLogging() {
        return this.servingSizeIndexForLogging;
    }
}
