package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedImageStatusUpdateEntry extends MfpNewsFeedStatusUpdateEntry {
    @Expose
    String imageId;

    public String getImageId() {
        return this.imageId;
    }
}
