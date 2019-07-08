package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;

public class DeleteMessageTask extends EventedTaskBase<Boolean, ApiException> {
    private final long messageId;
    private final Lazy<MessageService> messageService;
    private final String messageUid;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
    }

    public DeleteMessageTask(Lazy<MessageService> lazy, long j, String str) {
        super((TaskEventBase) new CompletedEvent());
        this.messageService = lazy;
        this.messageId = j;
        this.messageUid = str;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((MessageService) this.messageService.get()).deleteMessage(this.messageId, this.messageUid);
        return Boolean.valueOf(true);
    }
}
