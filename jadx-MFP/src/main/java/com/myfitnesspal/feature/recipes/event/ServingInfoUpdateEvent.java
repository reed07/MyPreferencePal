package com.myfitnesspal.feature.recipes.event;

import com.myfitnesspal.shared.model.v2.MfpServingSize;

public class ServingInfoUpdateEvent {
    private Double numOfServings;
    private MfpServingSize servingSize;
    private int servingSizeIndex;

    public ServingInfoUpdateEvent(Double d, int i) {
        this.numOfServings = d;
        this.servingSizeIndex = i;
    }

    public ServingInfoUpdateEvent(Double d, MfpServingSize mfpServingSize) {
        this.numOfServings = d;
        this.servingSize = mfpServingSize;
    }

    public Double getNumOfServings() {
        return this.numOfServings;
    }

    public void setNumOfServings(Double d) {
        this.numOfServings = d;
    }

    public int getServingSizeIndex() {
        return this.servingSizeIndex;
    }

    public void setServingSizeIndex(int i) {
        this.servingSizeIndex = i;
    }

    public MfpServingSize getServingSize() {
        return this.servingSize;
    }
}
