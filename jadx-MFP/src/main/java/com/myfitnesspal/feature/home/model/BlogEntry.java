package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;

public class BlogEntry implements MfpNewsFeedActivityEntryData {
    private int blogNumber;
    @Expose
    MfpNewsFeedBlogImageEntry post;

    public String getText() {
        return null;
    }

    public MfpNewsFeedBlogImageEntry getPost() {
        return this.post;
    }

    public int getBlogNumber() {
        return this.blogNumber;
    }

    public void setBlogNumber(int i) {
        this.blogNumber = i;
    }
}
