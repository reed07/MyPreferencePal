package com.myfitnesspal.shared.notification;

import com.myfitnesspal.shared.model.InAppNotifications;
import io.reactivex.Single;

public interface InAppNotificationManager {
    void clearInAppNotifications();

    Single<InAppNotifications> getCurrentInAppNotifications();

    void setCurrentInAppNotifications(InAppNotifications inAppNotifications);
}
