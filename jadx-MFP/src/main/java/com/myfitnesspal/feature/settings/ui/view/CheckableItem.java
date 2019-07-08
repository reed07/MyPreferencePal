package com.myfitnesspal.feature.settings.ui.view;

public class CheckableItem {
    private String description;
    private boolean state;

    public CheckableItem(String str, boolean z) {
        this.description = str;
        this.state = z;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getState() {
        return this.state;
    }

    public void setState(boolean z) {
        this.state = z;
    }
}
