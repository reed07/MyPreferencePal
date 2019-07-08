package com.myfitnesspal.feature.home.model;

public class NewsFeedRequestData {
    private final FetchType fetchType;
    private final int maxItems;
    private final String url;
    private final String userId;

    public enum FetchType {
        NewItems,
        OlderItems
    }

    public NewsFeedRequestData(String str, int i, FetchType fetchType2, String str2) {
        this.url = str;
        this.maxItems = i;
        this.fetchType = fetchType2;
        this.userId = str2;
    }

    public String getUrl() {
        return this.url;
    }

    public int getMaxItems() {
        return this.maxItems;
    }

    public FetchType getFetchType() {
        return this.fetchType;
    }

    public String getUserId() {
        return this.userId;
    }
}
