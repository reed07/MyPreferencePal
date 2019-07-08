package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.core.api.UacfApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class DeleteNotificationForCursorTask extends EventedTaskBase<Boolean, ApiException> {
    final UacfNotificationSdk notificationSdk;
    final String uacfNotificationId;

    public static class CompletedEvent extends TaskEventBase<Boolean, UacfApiException> {
    }

    public DeleteNotificationForCursorTask(String str, UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.uacfNotificationId = str;
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        return Boolean.valueOf(this.notificationSdk.deleteNotification(this.uacfNotificationId));
    }
}
