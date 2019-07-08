package com.myfitnesspal.feature.home.model;

public class EmptyListDisplayItem implements NewsFeedItem {
    private final ItemType itemType;

    public enum ItemType {
        EmptyTimeline,
        ErrorFetchingTimeline,
        PrivateTimeline
    }

    public EmptyListDisplayItem(ItemType itemType2) {
        this.itemType = itemType2;
    }

    public ItemType getItemType() {
        return this.itemType;
    }
}
