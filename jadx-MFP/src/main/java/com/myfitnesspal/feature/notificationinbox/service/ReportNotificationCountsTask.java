package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import com.myfitnesspal.feature.notificationinbox.util.NotificationInboxAnalyticsHelper;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class ReportNotificationCountsTask extends EventedTaskBase<Boolean, ApiException> {
    final Lazy<NotificationInboxAnalyticsHelper> inboxAnalyticsHelper;
    final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public ReportNotificationCountsTask(Lazy<NotificationInboxAnalyticsHelper> lazy, UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.notificationSdk = uacfNotificationSdk;
        this.inboxAnalyticsHelper = lazy;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        int countForStates = this.notificationSdk.getCountForStates("PENDING");
        int countForStates2 = this.notificationSdk.getCountForStates("READ");
        ((NotificationInboxAnalyticsHelper) this.inboxAnalyticsHelper.get()).reportNotifInboxDisplayed(countForStates, this.notificationSdk.getCountForStates("UNREAD"), countForStates2, "home_screen");
        return Boolean.valueOf(true);
    }
}
