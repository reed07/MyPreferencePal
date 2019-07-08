package com.myfitnesspal.feature.settings.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import dagger.Lazy;
import java.util.List;

public class FetchRemindersTask extends Unchecked<List<ReminderObject>> {
    private final Lazy<RemindersService> remindersService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<List<ReminderObject>> {
    }

    public FetchRemindersTask(Lazy<RemindersService> lazy) {
        super((TaskEventBase) new CompletedEvent());
        this.remindersService = lazy;
    }

    /* access modifiers changed from: protected */
    public List<ReminderObject> exec(Context context) throws RuntimeException {
        return ((RemindersService) this.remindersService.get()).getReminders();
    }
}
