package com.myfitnesspal.shared.event;

import com.myfitnesspal.shared.model.InAppNotifications;

public class ItemTalliesUpdatedEvent {
    private InAppNotifications notifications;

    public ItemTalliesUpdatedEvent(InAppNotifications inAppNotifications) {
        this.notifications = inAppNotifications;
    }

    public InAppNotifications getNotifications() {
        return this.notifications;
    }
}
