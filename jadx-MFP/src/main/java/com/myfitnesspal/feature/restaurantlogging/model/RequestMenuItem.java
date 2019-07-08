package com.myfitnesspal.feature.restaurantlogging.model;

import com.myfitnesspal.shared.model.CheckableListItem;

public class RequestMenuItem extends CheckableListItem {
    private final String key;

    public RequestMenuItem(String str, String str2) {
        super(str);
        this.key = str2;
    }

    public String getKey() {
        return this.key;
    }
}
