package com.myfitnesspal.feature.friends.task;

import android.content.Context;
import com.myfitnesspal.feature.friends.service.MessageService;
import com.myfitnesspal.framework.taskrunner.TaskBase;
import com.myfitnesspal.shared.api.ApiException;
import dagger.Lazy;

public class MarkMessageReadTask extends TaskBase<Boolean, ApiException> {
    private final long messageId;
    private final Lazy<MessageService> messageService;
    private final String messageUid;

    public MarkMessageReadTask(Lazy<MessageService> lazy, long j, String str) {
        this.messageService = lazy;
        this.messageId = j;
        this.messageUid = str;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws ApiException {
        ((MessageService) this.messageService.get()).markMessageRead(this.messageId, this.messageUid);
        return Boolean.valueOf(true);
    }
}
