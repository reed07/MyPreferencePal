package com.myfitnesspal.feature.home.model;

import com.google.gson.annotations.Expose;

public abstract class HeroCardLayoutBase {
    @Expose
    protected String description;
    @Expose
    protected String title;

    public abstract boolean hasImage();

    public String getTitle() {
        return this.title;
    }

    public String getDescription() {
        return this.description;
    }
}
