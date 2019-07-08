package com.myfitnesspal.feature.settings.task;

import android.content.Context;
import com.myfitnesspal.framework.taskrunner.EventedTaskBase.Unchecked;
import com.myfitnesspal.framework.taskrunner.TaskEventBase;
import com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.reminders.RemindersService;
import com.uacf.taskrunner.Runner.CacheMode;
import dagger.Lazy;

public class UpdateReminderTask extends Unchecked<Boolean> {
    private final ReminderObject reminder;
    private final Lazy<RemindersService> remindersService;

    public static class CompletedEvent extends TaskEventBase.Unchecked<Boolean> {
        /* access modifiers changed from: private */
        public long newId = -1;
        /* access modifiers changed from: private */
        public long originalId = -1;

        public long getOriginalId() {
            return this.originalId;
        }

        public long getNewId() {
            return this.newId;
        }
    }

    public UpdateReminderTask(Lazy<RemindersService> lazy, ReminderObject reminderObject) {
        super((TaskEventBase) new CompletedEvent());
        this.remindersService = lazy;
        this.reminder = reminderObject;
    }

    /* access modifiers changed from: protected */
    public Boolean exec(Context context) {
        CompletedEvent completedEvent = (CompletedEvent) getEvent();
        completedEvent.originalId = this.reminder.getLocalId();
        ((RemindersService) this.remindersService.get()).deleteReminderForUpdate(this.reminder);
        this.reminder.setMasterId(0);
        this.reminder.setUid(null);
        this.reminder.setStatusFlag(StatusFlag.DEFAULT);
        ((RemindersService) this.remindersService.get()).insertIfMissing(this.reminder);
        completedEvent.newId = this.reminder.getLocalId();
        return Boolean.valueOf(true);
    }

    /* access modifiers changed from: protected */
    public CacheMode getDefaultCacheMode() {
        return super.getDefaultCacheMode();
    }
}
