package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedBlogImageEntry {
    @Expose
    private MfpNewsFeedLinkDesc link;
    @Expose
    private String text;
    @Expose
    private MfpNewsFeedBlogThumbnail thumbnail;
    @Expose
    private String title;

    public MfpNewsFeedBlogThumbnail getThumbnail() {
        return this.thumbnail;
    }

    public void setThumbnail(MfpNewsFeedBlogThumbnail mfpNewsFeedBlogThumbnail) {
        this.thumbnail = mfpNewsFeedBlogThumbnail;
    }

    public MfpNewsFeedLinkDesc getLink() {
        return this.link;
    }

    public void setLink(MfpNewsFeedLinkDesc mfpNewsFeedLinkDesc) {
        this.link = mfpNewsFeedLinkDesc;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getText() {
        return this.text;
    }

    public void setText(String str) {
        this.text = str;
    }
}
