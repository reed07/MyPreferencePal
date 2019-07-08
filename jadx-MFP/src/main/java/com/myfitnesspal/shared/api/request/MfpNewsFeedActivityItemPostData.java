package com.myfitnesspal.shared.api.request;

import com.google.gson.annotations.Expose;

public class MfpNewsFeedActivityItemPostData {
    @Expose
    private Object item;

    public MfpNewsFeedActivityItemPostData(Object obj) {
        this.item = obj;
    }

    public void setItem(Object obj) {
        this.item = obj;
    }

    public Object getItem() {
        return this.item;
    }
}
