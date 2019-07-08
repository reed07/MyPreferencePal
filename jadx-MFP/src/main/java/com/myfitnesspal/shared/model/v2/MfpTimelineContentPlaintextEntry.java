package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentPlaintextEntry implements MfpTimelineContentData {
    @Expose
    private String text;

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
