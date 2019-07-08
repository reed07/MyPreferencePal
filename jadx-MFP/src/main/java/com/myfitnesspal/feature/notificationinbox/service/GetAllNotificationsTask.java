package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.model.UacfNotification;
import java.util.List;

public class GetAllNotificationsTask extends EventedTaskBase<List<UacfNotification>, ApiException> {
    final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<List<UacfNotification>, ApiException> {
    }

    public GetAllNotificationsTask(UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public List<UacfNotification> exec(Context context) throws ApiException {
        return this.notificationSdk.getAllNotifications();
    }
}
