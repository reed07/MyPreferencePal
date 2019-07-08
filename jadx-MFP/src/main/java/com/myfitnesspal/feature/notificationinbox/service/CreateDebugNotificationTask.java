package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import io.uacf.core.api.UacfApiException;
import io.uacf.inbox.internal.notification.NotificationService;

public class CreateDebugNotificationTask extends EventedTaskBase<Boolean, UacfApiException> {
    final String debugPayloadContent;
    final NotificationService notificationService;

    public static class CompletedEvent extends TaskEventBase<Boolean, UacfApiException> {
    }

    public CreateDebugNotificationTask(String str, NotificationService notificationService2) {
        super((TaskEventBase) new CompletedEvent());
        this.debugPayloadContent = str;
        this.notificationService = notificationService2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws UacfApiException {
        this.notificationService.createDebugNotification(this.debugPayloadContent);
        return Boolean.valueOf(true);
    }
}
