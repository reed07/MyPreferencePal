package com.myfitnesspal.shared.model.v2;

import android.net.Uri;
import android.net.Uri.Builder;
import com.google.gson.annotations.Expose;

public class MfpNewsFeedChallengeLink {
    @Expose
    private String deepLink;
    @Expose
    private String text;

    public String getDeepLink() {
        Builder buildUpon = Uri.parse(this.deepLink).buildUpon();
        buildUpon.appendQueryParameter("withinApp", String.valueOf(true));
        return buildUpon.build().toString();
    }

    public void setDeepLink(String str) {
        this.deepLink = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
