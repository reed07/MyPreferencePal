package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentImageEntry implements MfpTimelineContentData {
    @Expose
    private String id;

    public String getId() {
        return this.id;
    }

    public void setId(String str) {
        this.id = str;
    }
}
