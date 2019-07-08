package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class HeroCardTwoButtonLayout extends HeroCardLayoutBase {
    @SerializedName("button_deeplink_1")
    @Expose
    private String buttonDeeplink1;
    @SerializedName("button_deeplink_2")
    @Expose
    private String buttonDeeplink2;
    @SerializedName("button_text_1")
    @Expose
    private String buttonText1;
    @SerializedName("button_text_2")
    @Expose
    private String buttonText2;

    public boolean hasImage() {
        return false;
    }

    public String getButtonText1() {
        return this.buttonText1;
    }

    public String getButtonText2() {
        return this.buttonText2;
    }

    public String getButtonDeeplink1() {
        return this.buttonDeeplink1;
    }

    public String getButtonDeeplink2() {
        return this.buttonDeeplink2;
    }
}
