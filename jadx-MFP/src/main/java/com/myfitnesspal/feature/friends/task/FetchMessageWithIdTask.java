package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import dagger.Lazy;

public class FetchMessageWithIdTask extends EventedTaskBase<InboxMessage, ApiException> {
    private final long messageId;
    private final Lazy<MessageService> messageService;

    public static class CompletedEvent extends TaskEventBase<InboxMessage, ApiException> {
    }

    public FetchMessageWithIdTask(Lazy<MessageService> lazy, long j) {
        super((TaskEventBase) new CompletedEvent());
        this.messageService = lazy;
        this.messageId = j;
    }

    /* access modifiers changed from: protected */
    public InboxMessage exec(Context context) throws ApiException {
        return ((MessageService) this.messageService.get()).fetchMessageWithId(this.messageId);
    }
}
