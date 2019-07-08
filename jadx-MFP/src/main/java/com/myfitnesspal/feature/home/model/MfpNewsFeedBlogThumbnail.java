package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;
import java.util.List;

public class MfpNewsFeedBlogThumbnail {
    @Expose
    private String mainAsset;
    @Expose
    private List<String> sizes;

    public String getMainAsset() {
        return this.mainAsset;
    }

    public void setMainAsset(String str) {
        this.mainAsset = str;
    }

    public List<String> getSizes() {
        return this.sizes;
    }

    public void setSizes(List<String> list) {
        this.sizes = list;
    }
}
