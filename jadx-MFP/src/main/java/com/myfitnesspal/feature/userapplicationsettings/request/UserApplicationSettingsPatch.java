package com.myfitnesspal.feature.userapplicationsettings.request;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting;
import java.util.Map;

public class UserApplicationSettingsPatch {
    @Expose
    private Map<String, UserApplicationSetting> settings;
    @Expose
    private String userId;

    public UserApplicationSettingsPatch(String str, Map<String, UserApplicationSetting> map) {
        this.userId = str;
        this.settings = map;
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String str) {
        this.userId = str;
    }

    public Map<String, UserApplicationSetting> getSettings() {
        return this.settings;
    }

    public void setSettings(Map<String, UserApplicationSetting> map) {
        this.settings = map;
    }
}
