package com.myfitnesspal.feature.deleteaccount.task;

import android.content.Context;
import com.myfitnesspal.feature.deleteaccount.service.DeleteAccountService;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import dagger.Lazy;

public class DeleteAccountTask extends Unchecked<Boolean> {
    private final Lazy<DeleteAccountService> deleteAccountService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
    }

    public DeleteAccountTask(Lazy<DeleteAccountService> lazy) {
        super((TaskEventBase) new CompletedEvent());
        this.deleteAccountService = lazy;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        return Boolean.valueOf(((DeleteAccountService) this.deleteAccountService.get()).deleteAccount());
    }
}
