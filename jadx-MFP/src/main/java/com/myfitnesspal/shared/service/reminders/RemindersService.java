package com.myfitnesspal.shared.service.reminders;

import com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import java.util.List;
import javax.annotation.Nonnull;

public interface RemindersService {
    boolean addDefaultRemindersIfNecessary(boolean z);

    List<ReminderObject> availableDefaultReminders();

    ReminderObject defaultNewReminderForUser(String str);

    boolean deleteReminder(ReminderObject reminderObject);

    boolean deleteReminderForUpdate(ReminderObject reminderObject);

    boolean deleteReminderWithMasterId(long j);

    String getEventNameForReminder(ReminderObject reminderObject);

    String getLocalizedFrequencyString(String str);

    ReminderObject getReminderForId(long j);

    @Nonnull
    List<ReminderObject> getReminders();

    boolean insertFromSync(ReminderObject reminderObject);

    boolean insertIfMissing(ReminderObject reminderObject);

    boolean updateStatusFlag(long j, StatusFlag statusFlag);
}
