package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.api.ApiException;
import com.myfitnesspal.shared.model.v1.InboxMessage;
import dagger.Lazy;
import java.util.List;

public class FetchMessagesTask extends EventedTaskBase<List<InboxMessage>, ApiException> {
    private final int limit;
    private final Lazy<MessageService> messageService;
    private final int type;

    public static class CompletedEvent extends TaskEventBase<List<InboxMessage>, ApiException> {
    }

    public static FetchMessagesTask newInstanceForReceivedMessages(Lazy<MessageService> lazy, int i) {
        return new FetchMessagesTask(lazy, 1, i);
    }

    public static FetchMessagesTask newInstanceForSentMessages(Lazy<MessageService> lazy, int i) {
        return new FetchMessagesTask(lazy, 2, i);
    }

    private FetchMessagesTask(Lazy<MessageService> lazy, int i, int i2) {
        this.messageService = lazy;
        this.type = i;
        this.limit = i2;
    }

    /* access modifiers changed from: protected */
    public List<InboxMessage> exec(Context context) throws ApiException {
        return ((MessageService) this.messageService.get()).fetchMessagesOfType(this.type, this.limit);
    }
}
