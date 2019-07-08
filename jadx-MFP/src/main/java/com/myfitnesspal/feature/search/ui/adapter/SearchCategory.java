package com.myfitnesspal.feature.search.ui.adapter;

public class SearchCategory {
    private String title;
    private String type;

    public SearchCategory(String str, String str2) {
        this.title = str;
        this.type = str2;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String str) {
        this.type = str;
    }
}
