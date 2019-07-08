package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedDiaryCompleteEntry implements MfpNewsFeedActivityEntryData {
    @Expose
    private boolean showDiary;
    @Expose
    private String text;

    public String getText() {
        return this.text;
    }

    public MfpNewsFeedDiaryCompleteEntry setText(String str) {
        this.text = str;
        return this;
    }

    public boolean isShowDiary() {
        return this.showDiary;
    }

    public MfpNewsFeedDiaryCompleteEntry setShowDiary(boolean z) {
        this.showDiary = z;
        return this;
    }
}
