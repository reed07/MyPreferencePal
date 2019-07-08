package com.myfitnesspal.feature.search.event;

public class UpdateFoodSearchBreadcrumbEvent {
    private final String value;

    public UpdateFoodSearchBreadcrumbEvent(String str) {
        this.value = str;
    }

    public String getValue() {
        return this.value;
    }
}
