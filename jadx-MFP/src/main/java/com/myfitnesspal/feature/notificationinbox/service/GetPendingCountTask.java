package com.myfitnesspal.feature.notificationinbox.service;

import android.content.Context;
import android.support.annotation.NonNull;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.util.ConfigUtils;
import dagger.Lazy;
import io.uacf.inbox.sdk.UacfNotificationSdk;

public class GetPendingCountTask extends EventedTaskBase<Integer, ApiException> {
    private static final int MESSAGE_LIMIT = 50;
    final ConfigService configService;
    final Lazy<MessageService> messageService;
    final UacfNotificationSdk notificationSdk;

    public static class CompletedEvent extends TaskEventBase<Integer, ApiException> {
    }

    public GetPendingCountTask(@NonNull ConfigService configService2, @NonNull Lazy<MessageService> lazy, @NonNull UacfNotificationSdk uacfNotificationSdk) {
        super((TaskEventBase) new CompletedEvent());
        this.configService = configService2;
        this.messageService = lazy;
        this.notificationSdk = uacfNotificationSdk;
    }

    /* access modifiers changed from: protected */
    public Integer exec(Context context) throws ApiException {
        int countForStates = this.notificationSdk.getCountForStates("PENDING");
        if (ConfigUtils.isAppNavUpdateHomeEnabled(this.configService)) {
            countForStates += InboxMessage.getUnreadCount(((MessageService) this.messageService.get()).fetchMessagesOfType(1, 50));
        }
        return Integer.valueOf(countForStates);
    }
}
