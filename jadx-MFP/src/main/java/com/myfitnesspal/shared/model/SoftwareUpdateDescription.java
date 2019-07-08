package com.myfitnesspal.shared.model;

import com.uacf.core.util.Strings;

public class SoftwareUpdateDescription {
    private String body;
    private int buttonType;
    private String title;
    private String token;
    private String url;

    public static final class ButtonTypes {
        public static final int DISMISS = 1;
        public static final int UPGRADE = 0;
    }

    public SoftwareUpdateDescription(String str) {
        if (!Strings.isEmpty(str)) {
            String[] split = str.split("|");
            if (split.length != 0) {
                this.token = split[0];
                if (split.length != 1) {
                    this.title = split[1];
                    if (split.length != 2) {
                        this.body = split[2];
                        if (split.length != 3) {
                            this.url = split[3];
                            if (split.length != 4) {
                                this.buttonType = Strings.equals(split[4], "dismiss") ? 1 : 0;
                            }
                        }
                    }
                }
            }
        }
    }

    public String getToken() {
        return this.token;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getBody() {
        return this.body;
    }

    public void setBody(String str) {
        this.body = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public int getButtonType() {
        return this.buttonType;
    }

    public void setButtonType(int i) {
        this.buttonType = i;
    }
}
