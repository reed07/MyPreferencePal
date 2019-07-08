package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedLinkDesc {
    @Expose
    private String blogSource;
    @Expose
    private String blogSourceDeepLinkUrl;
    @Expose
    private String blogSourceUrl;
    @Expose
    private String deepLink;
    @Expose
    private String postDescription;
    @Expose
    private String text;
    @Expose
    private String webUrl;

    public String getDeepLink() {
        return this.deepLink;
    }

    public void setDeepLink(String str) {
        this.deepLink = str;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public void setWebUrl(String str) {
        this.webUrl = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }

    public String getPostDescription() {
        return this.postDescription;
    }

    public String getBlogSource() {
        return this.blogSource;
    }

    public String getBlogSourceUrl() {
        return this.blogSourceUrl;
    }

    public String getBlogSourceDeepLinkUrl() {
        return this.blogSourceDeepLinkUrl;
    }
}
