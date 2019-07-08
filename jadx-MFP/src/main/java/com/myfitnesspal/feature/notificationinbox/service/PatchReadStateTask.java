package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import io.uacf.inbox.sdk.UacfNotificationSdk;
import io.uacf.inbox.sdk.model.UacfNotification;

public class PatchReadStateTask extends EventedTaskBase<Boolean, ApiException> {
    private final boolean markRead;
    private final UacfNotificationSdk notificationSdk;
    private final int numOfNotificationsVisible;
    private final int position;
    private final UacfNotification uacfNotification;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        private String deeplinkToLaunch;
        private boolean markRead;
        private int position;

        public CompletedEvent(int i, boolean z, String str) {
            this.position = i;
            this.markRead = z;
            this.deeplinkToLaunch = str;
        }

        public int getPosition() {
            return this.position;
        }

        public boolean isMarkRead() {
            return this.markRead;
        }

        public String getDeeplinkToLaunch() {
            return this.deeplinkToLaunch;
        }
    }

    public PatchReadStateTask(UacfNotification uacfNotification2, int i, int i2, boolean z, UacfNotificationSdk uacfNotificationSdk, String str) {
        super((TaskEventBase) new CompletedEvent(i, z, str));
        this.uacfNotification = uacfNotification2;
        this.position = i;
        this.numOfNotificationsVisible = i2;
        this.markRead = z;
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        boolean updateNotification = this.notificationSdk.updateNotification(this.uacfNotification.getEngagementId(), this.markRead ? "READ" : "UNREAD");
        if (updateNotification) {
            this.notificationSdk.reportNotificationRead(this.uacfNotification.getEngagementId(), this.position, this.numOfNotificationsVisible);
        }
        return Boolean.valueOf(updateNotification);
    }
}
