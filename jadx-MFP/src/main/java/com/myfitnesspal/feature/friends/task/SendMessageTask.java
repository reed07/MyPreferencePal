package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import dagger.Lazy;

public class SendMessageTask extends EventedTaskBase<Boolean, ApiException> {
    private final InboxMessage inboxMessage;
    private final Lazy<MessageService> messageService;

    public static class CompletedEvent extends TaskEventBase<Boolean, ApiException> {
        private final InboxMessage inboxMessage;

        public CompletedEvent(InboxMessage inboxMessage2) {
            this.inboxMessage = inboxMessage2;
        }

        public InboxMessage getInboxMessage() {
            return this.inboxMessage;
        }
    }

    public SendMessageTask(Lazy<MessageService> lazy, InboxMessage inboxMessage2) {
        super((TaskEventBase) new CompletedEvent(inboxMessage2));
        this.messageService = lazy;
        this.inboxMessage = inboxMessage2;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((MessageService) this.messageService.get()).sendMessage(this.inboxMessage);
        return Boolean.valueOf(true);
    }
}
