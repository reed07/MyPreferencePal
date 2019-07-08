package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentCompletedDiaryEventEntry implements MfpTimelineContentData {
    @Expose
    private MfpEnergySummary energySummary;

    public MfpEnergySummary getEnergySummary() {
        return this.energySummary;
    }

    public void setEnergySummary(MfpEnergySummary mfpEnergySummary) {
        this.energySummary = mfpEnergySummary;
    }
}
