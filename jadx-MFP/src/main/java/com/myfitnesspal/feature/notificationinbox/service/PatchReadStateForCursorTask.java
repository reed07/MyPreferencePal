package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.model.UacfNotification;

public class PatchReadStateForCursorTask extends EventedTaskBase<Boolean, ApiException> {
    final boolean markRead;
    final UacfNotificationSdk notificationSdk;
    final UacfNotification uacfNotification;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        private String deeplink;
        private boolean markRead;

        public CompletedEvent(boolean z, String str) {
            this.markRead = z;
            this.deeplink = str;
        }

        public String getDeeplink() {
            return this.deeplink;
        }

        public boolean isMarkRead() {
            return this.markRead;
        }
    }

    public PatchReadStateForCursorTask(UacfNotification uacfNotification2, boolean z, UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent(z, uacfNotification2.getBodyDeepLink()));
        this.uacfNotification = uacfNotification2;
        this.markRead = z;
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        return Boolean.valueOf(this.notificationSdk.updateNotification(this.uacfNotification.getEngagementId(), this.markRead ? "READ" : "UNREAD"));
    }
}
