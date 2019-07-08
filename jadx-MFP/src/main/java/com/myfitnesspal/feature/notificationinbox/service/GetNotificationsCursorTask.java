package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class GetNotificationsCursorTask extends EventedTaskBase<Cursor, ApiException> {
    final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<Cursor, ApiException> {
    }

    public GetNotificationsCursorTask(UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Cursor exec(Context context) throws ApiException {
        return this.notificationSdk.getAllNotificationsAsCursor();
    }
}
