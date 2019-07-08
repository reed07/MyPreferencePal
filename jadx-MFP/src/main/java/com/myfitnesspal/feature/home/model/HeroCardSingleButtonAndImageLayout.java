package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public class HeroCardSingleButtonAndImageLayout extends HeroCardLayoutBase {
    @Expose
    private String assetUrl;
    @Expose
    private String buttonDeeplink;
    @Expose
    private String buttonText;

    public boolean hasImage() {
        return true;
    }

    public String getAssetUrl() {
        return this.assetUrl;
    }

    public String getButtonText() {
        return this.buttonText;
    }

    public String getButtonDeeplink() {
        return this.buttonDeeplink;
    }
}
