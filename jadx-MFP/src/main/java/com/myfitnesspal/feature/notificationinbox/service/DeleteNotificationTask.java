package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class DeleteNotificationTask extends EventedTaskBase<Boolean, ApiException> {
    final String notificationId;
    final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        private int position;

        public CompletedEvent(int i) {
            this.position = i;
        }

        public int getPosition() {
            return this.position;
        }
    }

    public DeleteNotificationTask(int i, String str, UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent(i));
        this.notificationId = str;
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        return Boolean.valueOf(this.notificationSdk.deleteNotification(this.notificationId));
    }
}
