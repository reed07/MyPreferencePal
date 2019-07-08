package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentLoggedWeightEntry implements MfpTimelineContentData {
    @Expose
    private MfpMeasuredValue value;

    public MfpMeasuredValue getValue() {
        return this.value;
    }

    public void setValue(MfpMeasuredValue mfpMeasuredValue) {
        this.value = mfpMeasuredValue;
    }
}
