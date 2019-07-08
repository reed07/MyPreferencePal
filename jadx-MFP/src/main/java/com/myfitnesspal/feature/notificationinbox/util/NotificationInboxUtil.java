package com.myfitnesspal.feature.notificationinbox.util;

import com.myfitnesspal.feature.notificationinbox.model.NotificationTypes;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.model.UacfNotification;

public class NotificationInboxUtil {
    public static NotificationTypes getNotificationType(UacfNotification uacfNotification) {
        if (Strings.isEmpty(uacfNotification.getSecondaryImageUri())) {
            return NotificationTypes.ImageTextType;
        }
        return NotificationTypes.ImageTextImageType;
    }

    public static void setMaxPriorityCountSetting(int i) {
        if (i < 0) {
            Ln.e("Max priority count for the Notification Inbox sdk must be a positive number", new Object[0]);
        } else {
            UacfNotificationSdk.getSettings().setMaxPriorityCount(i);
        }
    }

    public static void setLimitPriorityToOnePerCategorySetting(boolean z) {
        UacfNotificationSdk.getSettings().setLimitPriorityToOnePerCategory(z);
    }
}
