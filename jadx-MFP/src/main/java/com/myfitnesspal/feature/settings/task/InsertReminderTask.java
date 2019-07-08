package com.myfitnesspal.feature.settings.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.Lazy;
import java.util.List;

public class InsertReminderTask extends Unchecked<Boolean> {
    private final ReminderObject reminder;
    private final Lazy<RemindersService> remindersService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<List<ReminderObject>> {
    }

    public InsertReminderTask(Lazy<RemindersService> lazy, ReminderObject reminderObject) {
        super((TaskEventBase) new CompletedEvent());
        this.remindersService = lazy;
        this.reminder = reminderObject;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        return Boolean.valueOf(((RemindersService) this.remindersService.get()).insertIfMissing(this.reminder));
    }
}
