package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;
import com.myfitnesspal.shared.model.v2.MfpNewsFeedActivityEntryData;
import java.util.List;

public class MfpBlogDailySummary implements MfpNewsFeedActivityEntryData {
    @Expose
    List<MfpNewsFeedBlogImageEntry> posts;

    public String getText() {
        return null;
    }

    public List<MfpNewsFeedBlogImageEntry> getPosts() {
        return this.posts;
    }

    public void setPosts(List<MfpNewsFeedBlogImageEntry> list) {
        this.posts = list;
    }
}
