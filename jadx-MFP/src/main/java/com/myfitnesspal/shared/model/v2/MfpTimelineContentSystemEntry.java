package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentSystemEntry implements MfpTimelineContentData {
    @Expose
    private String changeType;
    @Expose
    private MfpTimelineContent object;

    public String getChangeType() {
        return this.changeType;
    }

    public void setChangeType(String str) {
        this.changeType = str;
    }

    public MfpTimelineContent getObject() {
        return this.object;
    }

    public void setObject(MfpTimelineContent mfpTimelineContent) {
        this.object = mfpTimelineContent;
    }
}
