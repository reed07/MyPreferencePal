package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentDailyStepsCountEntry implements MfpTimelineContentData {
    @Expose
    private Integer steps;

    public Integer getSteps() {
        return this.steps;
    }

    public void setSteps(Integer num) {
        this.steps = num;
    }
}
