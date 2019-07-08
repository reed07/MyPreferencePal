package com.myfitnesspal.shared.model;

public class CheckableListItem {
    private String description;
    private boolean state;
    private Object tag;

    public CheckableListItem(String str) {
        this(str, false);
    }

    public CheckableListItem(String str, boolean z) {
        this.description = str;
        this.state = z;
    }

    public CheckableListItem(String str, boolean z, Object obj) {
        this.description = str;
        this.state = z;
        this.tag = obj;
    }

    public void setState(boolean z) {
        this.state = z;
    }

    public String getDescription() {
        return this.description;
    }

    public boolean getState() {
        return this.state;
    }

    public <T> T getTag() {
        return this.tag;
    }
}
