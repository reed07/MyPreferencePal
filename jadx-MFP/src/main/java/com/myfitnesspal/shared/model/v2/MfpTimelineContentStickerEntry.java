package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpTimelineContentStickerEntry implements MfpTimelineContentData {
    @Expose
    private String id;
    @Expose
    private String url;

    public String getUrl() {
        return this.url;
    }

    public String getId() {
        return this.id;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setId(String str) {
        this.id = str;
    }
}
