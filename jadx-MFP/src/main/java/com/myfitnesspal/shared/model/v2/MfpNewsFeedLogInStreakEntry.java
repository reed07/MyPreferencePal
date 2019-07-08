package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedLogInStreakEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    private String text;

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedLogInStreakEntry setText(String str) {
        this.text = str;
        return this;
    }
}
