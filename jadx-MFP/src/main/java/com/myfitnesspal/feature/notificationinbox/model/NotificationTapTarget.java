package com.myfitnesspal.feature.notificationinbox.model;

public enum NotificationTapTarget {
    UserProfile("user_profile"),
    Primary("primary");
    
    private final String target;

    private NotificationTapTarget(String str) {
        this.target = str;
    }

    public String getTarget() {
        return this.target;
    }
}
