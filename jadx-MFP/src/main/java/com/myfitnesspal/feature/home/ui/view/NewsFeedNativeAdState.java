package com.myfitnesspal.feature.home.ui.view;

public enum NewsFeedNativeAdState {
    AUTO_PLAY("auto_play"),
    CLICK_TO_PLAY("click_to_play"),
    STATIC("static");
    
    private String stateName;

    private NewsFeedNativeAdState(String str) {
        this.stateName = str;
    }

    public String getStateName() {
        return this.stateName;
    }
}
