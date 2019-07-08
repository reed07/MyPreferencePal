package com.myfitnesspal.feature.recipes.api;

import com.google.gson.annotations.Expose;

public class RecipeParseRequest {
    @Expose
    private String url;

    public RecipeParseRequest(String str) {
        this.url = str;
    }

    public String getUrl() {
        return this.url;
    }

    public void setUrl(String str) {
        this.url = str;
    }
}
