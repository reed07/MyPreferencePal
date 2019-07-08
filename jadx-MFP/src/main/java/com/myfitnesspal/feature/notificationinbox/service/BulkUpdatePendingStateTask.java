package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class BulkUpdatePendingStateTask extends EventedTaskBase<Boolean, ApiException> {
    private final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public BulkUpdatePendingStateTask(UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        return Boolean.valueOf(this.notificationSdk.updateNotifications("PENDING", "UNREAD"));
    }
}
