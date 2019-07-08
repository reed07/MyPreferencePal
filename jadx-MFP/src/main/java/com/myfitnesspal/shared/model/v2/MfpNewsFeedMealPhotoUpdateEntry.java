package com.myfitnesspal.shared.model.v2;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedMealPhotoUpdateEntry extends MfpNewsFeedImageStatusUpdateEntry {
    @Expose
    String foodId;
    @Expose
    String originalImageId;

    public String getFoodId() {
        return this.foodId;
    }

    public String getOriginalImageId() {
        return this.originalImageId;
    }
}
