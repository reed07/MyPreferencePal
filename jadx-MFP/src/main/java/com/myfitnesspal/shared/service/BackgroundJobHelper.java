package com.myfitnesspal.shared.service;

public interface BackgroundJobHelper {
    boolean isJobRunning(int i);

    void updateInAppNotifications();
}
