package com.myfitnesspal.shared.model;

import com.google.gson.annotations.Expose;

public class NotificationAps {
    @Expose
    private String alert;
    @Expose
    private String title;

    public void setAlert(String str) {
        this.alert = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getAlert() {
        return this.alert;
    }

    public String getTitle() {
        return this.title;
    }
}
