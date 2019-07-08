package com.myfitnesspal.feature.settings.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.uacf.core.util.CollectionUtils;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class DeleteRemindersTask extends Unchecked<Boolean> {
    private final List<ReminderObject> reminders;
    private final Lazy<RemindersService> remindersService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<List<ReminderObject>> {
    }

    public DeleteRemindersTask(Lazy<RemindersService> lazy, List<ReminderObject> list) {
        super((TaskEventBase) new CompletedEvent());
        this.remindersService = lazy;
        this.reminders = list;
    }

    public DeleteRemindersTask(Lazy<RemindersService> lazy, ReminderObject reminderObject) {
        super((TaskEventBase) new CompletedEvent());
        this.remindersService = lazy;
        this.reminders = new ArrayList(Arrays.asList(new ReminderObject[]{reminderObject}));
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) throws RuntimeException {
        if (CollectionUtils.notEmpty((Collection<?>) this.reminders)) {
            for (ReminderObject deleteReminder : this.reminders) {
                ((RemindersService) this.remindersService.get()).deleteReminder(deleteReminder);
            }
        }
        return Boolean.valueOf(true);
    }
}
