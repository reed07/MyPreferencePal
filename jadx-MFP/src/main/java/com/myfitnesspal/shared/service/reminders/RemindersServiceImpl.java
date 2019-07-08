package com.myfitnesspal.shared.service.reminders;

import android.content.Context;
import android.support.annotation.Nullable;
import com.myfitnesspal.android.R;
import com.myfitnesspal.shared.constants.Constants.ABTest.TrackWeightNotifications201510;
import com.myfitnesspal.shared.constants.Constants.Analytics.Events;
import com.myfitnesspal.shared.constants.Constants.DateTime;
import com.myfitnesspal.shared.constants.Constants.Reminder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.RemindersTable;
import com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.myfitnesspal.shared.service.config.ConfigService;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.LocalizedStringsUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function1;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

public class RemindersServiceImpl implements RemindersService {
    private static final int[] ANY_FOOD_REMINDER_INTERVALS = {1, 3, 7};
    private static final int ONLY_THREE_MEALS = 3;
    private static final int REMINDER_INTERVAL_1_DAY = 1;
    private static final int REMINDER_INTERVAL_3_DAYS = 3;
    private static final int REMINDER_INTERVAL_7_DAYS = 7;
    private final Lazy<ConfigService> configService;
    private final Context context;
    private final Lazy<DbConnectionManager> dbConnectionManager;
    private final Lazy<LocalizedStringsUtil> localizedStringsUtil;
    private final RemindersTable remindersTable;
    private final Lazy<Session> session;

    public RemindersServiceImpl(Context context2, RemindersTable remindersTable2, Lazy<ConfigService> lazy, Lazy<Session> lazy2, Lazy<LocalizedStringsUtil> lazy3, Lazy<DbConnectionManager> lazy4) {
        this.context = context2;
        this.remindersTable = remindersTable2;
        this.configService = lazy;
        this.session = lazy2;
        this.localizedStringsUtil = lazy3;
        this.dbConnectionManager = lazy4;
    }

    /* JADX WARNING: Removed duplicated region for block: B:30:0x007c A[Catch:{ Exception -> 0x00a6 }] */
    /* JADX WARNING: Removed duplicated region for block: B:53:0x0041 A[SYNTHETIC] */
    @android.support.annotation.NonNull
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.myfitnesspal.shared.model.v15.ReminderObject> getReminders() {
        /*
            r10 = this;
            r0 = 0
            com.myfitnesspal.shared.db.table.RemindersTable r1 = r10.remindersTable     // Catch:{ Exception -> 0x00a6 }
            com.myfitnesspal.shared.model.User r2 = r10.getCurrentUser()     // Catch:{ Exception -> 0x00a6 }
            long r2 = r2.getLocalId()     // Catch:{ Exception -> 0x00a6 }
            java.util.List r1 = r1.getRemindersForUser(r2)     // Catch:{ Exception -> 0x00a6 }
            java.util.HashSet r2 = new java.util.HashSet     // Catch:{ Exception -> 0x00a6 }
            r2.<init>()     // Catch:{ Exception -> 0x00a6 }
            java.util.List r3 = r10.availableDefaultReminders()     // Catch:{ Exception -> 0x00a6 }
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x00a6 }
        L_0x001c:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x00a6 }
            if (r4 == 0) goto L_0x0038
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x00a6 }
            com.myfitnesspal.shared.model.v15.ReminderObject r4 = (com.myfitnesspal.shared.model.v15.ReminderObject) r4     // Catch:{ Exception -> 0x00a6 }
            if (r4 == 0) goto L_0x001c
            java.lang.String r4 = r4.getMealId()     // Catch:{ Exception -> 0x00a6 }
            boolean r5 = com.uacf.core.util.Strings.notEmpty(r4)     // Catch:{ Exception -> 0x00a6 }
            if (r5 == 0) goto L_0x001c
            r2.add(r4)     // Catch:{ Exception -> 0x00a6 }
            goto L_0x001c
        L_0x0038:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x00a6 }
            r3.<init>()     // Catch:{ Exception -> 0x00a6 }
            java.util.Iterator r4 = r1.iterator()     // Catch:{ Exception -> 0x00a6 }
        L_0x0041:
            boolean r5 = r4.hasNext()     // Catch:{ Exception -> 0x00a6 }
            if (r5 == 0) goto L_0x0083
            java.lang.Object r5 = r4.next()     // Catch:{ Exception -> 0x00a6 }
            com.myfitnesspal.shared.model.v15.ReminderObject r5 = (com.myfitnesspal.shared.model.v15.ReminderObject) r5     // Catch:{ Exception -> 0x00a6 }
            if (r5 == 0) goto L_0x0041
            java.lang.String r6 = r5.getMealId()     // Catch:{ Exception -> 0x00a6 }
            boolean r7 = com.uacf.core.util.Strings.isEmpty(r6)     // Catch:{ Exception -> 0x00a6 }
            int r8 = r5.getReminderType()     // Catch:{ Exception -> 0x00a6 }
            r9 = 3
            if (r8 != r9) goto L_0x006a
            if (r7 != 0) goto L_0x0079
            java.lang.Integer r7 = java.lang.Integer.valueOf(r0)     // Catch:{ Exception -> 0x00a6 }
            boolean r7 = com.uacf.core.util.Strings.equals(r6, r7)     // Catch:{ Exception -> 0x00a6 }
            if (r7 != 0) goto L_0x0079
        L_0x006a:
            r7 = 4
            if (r8 == r7) goto L_0x0079
            r7 = 2
            if (r8 == r7) goto L_0x0079
            boolean r6 = r2.contains(r6)     // Catch:{ Exception -> 0x00a6 }
            if (r6 == 0) goto L_0x0077
            goto L_0x0079
        L_0x0077:
            r6 = 0
            goto L_0x007a
        L_0x0079:
            r6 = 1
        L_0x007a:
            if (r6 != 0) goto L_0x0041
            r3.add(r5)     // Catch:{ Exception -> 0x00a6 }
            r4.remove()     // Catch:{ Exception -> 0x00a6 }
            goto L_0x0041
        L_0x0083:
            boolean r2 = com.uacf.core.util.CollectionUtils.notEmpty(r3)     // Catch:{ Exception -> 0x00a6 }
            if (r2 == 0) goto L_0x00a5
            java.util.Iterator r2 = r3.iterator()     // Catch:{ Exception -> 0x00a6 }
        L_0x008d:
            boolean r3 = r2.hasNext()     // Catch:{ Exception -> 0x00a6 }
            if (r3 == 0) goto L_0x00a5
            java.lang.Object r3 = r2.next()     // Catch:{ Exception -> 0x00a6 }
            com.myfitnesspal.shared.model.v15.ReminderObject r3 = (com.myfitnesspal.shared.model.v15.ReminderObject) r3     // Catch:{ Exception -> 0x00a6 }
            if (r3 == 0) goto L_0x008d
            boolean r4 = r3.hasLocalId()     // Catch:{ Exception -> 0x00a6 }
            if (r4 == 0) goto L_0x008d
            r10.deleteReminder(r3)     // Catch:{ Exception -> 0x00a6 }
            goto L_0x008d
        L_0x00a5:
            return r1
        L_0x00a6:
            r1 = move-exception
            java.lang.String r2 = "RemindersService.fetchRemindersForUser"
            java.lang.Object[] r0 = new java.lang.Object[r0]
            com.uacf.core.util.Ln.e(r1, r2, r0)
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.reminders.RemindersServiceImpl.getReminders():java.util.List");
    }

    private User getCurrentUser() {
        return ((Session) this.session.get()).getUser();
    }

    public boolean insertIfMissing(ReminderObject reminderObject) {
        try {
            this.remindersTable.insertIfMissing(getCurrentUser().getLocalId(), reminderObject, false, (DbConnectionManager) this.dbConnectionManager.get());
            return true;
        } catch (Exception e) {
            Ln.e(e, "RemindersService.insertIfMissing", new Object[0]);
            return false;
        }
    }

    public boolean insertFromSync(ReminderObject reminderObject) {
        try {
            this.remindersTable.insertIfMissing(getCurrentUser().getLocalId(), reminderObject, true, (DbConnectionManager) this.dbConnectionManager.get());
            return true;
        } catch (Exception e) {
            Ln.e(e, "RemindersService.insertIfMissing", new Object[0]);
            return false;
        }
    }

    @Nullable
    public ReminderObject getReminderForId(long j) {
        try {
            return this.remindersTable.queryReminderForId(Long.valueOf(j));
        } catch (Exception e) {
            Ln.e(e, "RemindersService.getReminderForId", new Object[0]);
            return null;
        }
    }

    public boolean deleteReminder(ReminderObject reminderObject) {
        try {
            this.remindersTable.deleteReminder(getCurrentUser().getLocalId(), reminderObject, (DbConnectionManager) this.dbConnectionManager.get());
            return true;
        } catch (Exception e) {
            Ln.e(e, "RemindersService.deleteReminderWithMasterId", new Object[0]);
            return false;
        }
    }

    public boolean deleteReminderWithMasterId(long j) {
        try {
            this.remindersTable.deleteReminderWithMasterId(getCurrentUser().getLocalId(), j, (DbConnectionManager) this.dbConnectionManager.get());
            return true;
        } catch (Exception e) {
            Ln.e(e, "RemindersService.deleteReminderWithMasterId", new Object[0]);
            return false;
        }
    }

    public boolean deleteReminderForUpdate(ReminderObject reminderObject) {
        try {
            this.remindersTable.deleteReminderForUpdate(getCurrentUser().getLocalId(), reminderObject, (DbConnectionManager) this.dbConnectionManager.get());
            return true;
        } catch (Exception e) {
            Ln.e(e, "RemindersService.deleteReminderForUpdate", new Object[0]);
            return false;
        }
    }

    public ReminderObject defaultNewReminderForUser(String str) {
        if (Strings.equals(str, "log_weight") && isWeightReminderEnabled()) {
            return createDefaultWeightReminder(true, false);
        }
        List availableDefaultReminders = availableDefaultReminders();
        if (availableDefaultReminders.size() != 0) {
            return (ReminderObject) availableDefaultReminders.get(0);
        }
        ReminderObject reminderObject = new ReminderObject();
        reminderObject.setReminderType(1);
        reminderObject.setIntervalInDays(1);
        reminderObject.setWallClockTime(Reminder.TWO_OCLOCK);
        reminderObject.setIsActive(true);
        reminderObject.setFrequency("");
        reminderObject.setDayOfMonth(0);
        reminderObject.setDayOfWeek("");
        reminderObject.setStatusFlag(StatusFlag.DEFAULT);
        return reminderObject;
    }

    public boolean addDefaultRemindersIfNecessary(boolean z) {
        List<ReminderObject> reminders = getReminders();
        if (CollectionUtils.size((Collection<?>) reminders) <= 0) {
            String[] generateRandomTimes = generateRandomTimes();
            List<String> names = getCurrentUser().getMealNames().getNames();
            if (CollectionUtils.notEmpty((Collection<?>) names)) {
                int i = 0;
                for (String str : names) {
                    if (i == 3) {
                        break;
                    }
                    ReminderObject reminderObject = new ReminderObject();
                    reminderObject.setReminderType(1);
                    reminderObject.setMealName(str);
                    reminderObject.setMealId(Strings.toString(Integer.valueOf(getCurrentUser().getMealNames().mealIdForName(str))));
                    reminderObject.setIntervalInDays(1);
                    reminderObject.setWallClockTime(generateRandomTimes[i]);
                    reminderObject.setIsActive(z);
                    reminderObject.setIsAutoCreated(true);
                    reminderObject.setFrequency("");
                    reminderObject.setDayOfMonth(0);
                    reminderObject.setDayOfWeek("");
                    reminderObject.setStatusFlag(StatusFlag.DEFAULT);
                    insertIfMissing(reminderObject);
                    i++;
                }
            }
            if (isWeightReminderEnabled()) {
                insertIfMissing(createDefaultWeightReminder(z, true));
            }
            return true;
        } else if (!z) {
            return false;
        } else {
            for (ReminderObject isActive : reminders) {
                if (isActive.isActive()) {
                    return false;
                }
            }
            Enumerable.forEach((Collection<T>) reminders, (Function1<T>) new Function1<ReminderObject>() {
                public void execute(ReminderObject reminderObject) {
                    RemindersServiceImpl.this.deleteReminderForUpdate(reminderObject);
                    reminderObject.setMasterId(0);
                    reminderObject.setIsActive(true);
                    reminderObject.setIsAutoCreated(true);
                    RemindersServiceImpl.this.insertIfMissing(reminderObject);
                }
            });
            return true;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:17:0x0036  */
    /* JADX WARNING: Removed duplicated region for block: B:19:0x0038  */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0046  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0054  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.String getLocalizedFrequencyString(java.lang.String r3) {
        /*
            r2 = this;
            int r0 = r3.hashCode()
            r1 = -791707519(0xffffffffd0cf8081, float:-2.78504428E10)
            if (r0 == r1) goto L_0x0028
            r1 = 95346201(0x5aede19, float:1.6444467E-35)
            if (r0 == r1) goto L_0x001e
            r1 = 1236635661(0x49b5900d, float:1487361.6)
            if (r0 == r1) goto L_0x0014
            goto L_0x0032
        L_0x0014:
            java.lang.String r0 = "monthly"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 2
            goto L_0x0033
        L_0x001e:
            java.lang.String r0 = "daily"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 0
            goto L_0x0033
        L_0x0028:
            java.lang.String r0 = "weekly"
            boolean r3 = r3.equals(r0)
            if (r3 == 0) goto L_0x0032
            r3 = 1
            goto L_0x0033
        L_0x0032:
            r3 = -1
        L_0x0033:
            switch(r3) {
                case 0: goto L_0x0054;
                case 1: goto L_0x0046;
                case 2: goto L_0x0038;
                default: goto L_0x0036;
            }
        L_0x0036:
            r3 = 0
            return r3
        L_0x0038:
            android.content.Context r3 = r2.context
            android.content.res.Resources r3 = r3.getResources()
            r0 = 2131888385(0x7f120901, float:1.9411404E38)
            java.lang.String r3 = r3.getString(r0)
            return r3
        L_0x0046:
            android.content.Context r3 = r2.context
            android.content.res.Resources r3 = r3.getResources()
            r0 = 2131889601(0x7f120dc1, float:1.941387E38)
            java.lang.String r3 = r3.getString(r0)
            return r3
        L_0x0054:
            android.content.Context r3 = r2.context
            android.content.res.Resources r3 = r3.getResources()
            r0 = 2131886962(0x7f120372, float:1.9408518E38)
            java.lang.String r3 = r3.getString(r0)
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.service.reminders.RemindersServiceImpl.getLocalizedFrequencyString(java.lang.String):java.lang.String");
    }

    public List<ReminderObject> availableDefaultReminders() {
        ArrayList arrayList = new ArrayList();
        MealNames mealNames = getCurrentUser().getMealNames();
        for (String str : mealNames.getNames()) {
            ReminderObject reminderObject = new ReminderObject();
            reminderObject.setReminderType(1);
            reminderObject.setMealName(str);
            reminderObject.setMealId(Strings.toString(Integer.valueOf(mealNames.mealIdForName(str))));
            reminderObject.setIntervalInDays(1);
            reminderObject.setWallClockTime(defaultReminderWallClockTimeForMealName(str));
            reminderObject.setIsActive(true);
            reminderObject.setFrequency("");
            reminderObject.setDayOfMonth(0);
            reminderObject.setDayOfWeek("");
            reminderObject.setStatusFlag(StatusFlag.DEFAULT);
            arrayList.add(reminderObject);
        }
        for (int intervalInDays : ANY_FOOD_REMINDER_INTERVALS) {
            ReminderObject reminderObject2 = new ReminderObject();
            reminderObject2.setReminderType(3);
            reminderObject2.setIntervalInDays(intervalInDays);
            reminderObject2.setWallClockTime(Reminder.TWELVE_OCLOCK);
            reminderObject2.setMealName(reminderObject2.description(this.context, this.localizedStringsUtil, mealNames));
            reminderObject2.setMealId("");
            reminderObject2.setFlags(reminderObject2.getFlags());
            reminderObject2.setFrequency("");
            reminderObject2.setDayOfMonth(0);
            reminderObject2.setDayOfWeek("");
            reminderObject2.setStatusFlag(StatusFlag.DEFAULT);
            arrayList.add(reminderObject2);
        }
        if (((ConfigService) this.configService.get()).isVariantEnabled(TrackWeightNotifications201510.NAME)) {
            arrayList.add(createDefaultWeightReminder(true, false));
        }
        return arrayList;
    }

    public boolean updateStatusFlag(long j, StatusFlag statusFlag) {
        try {
            return this.remindersTable.updateStatusFlag(j, statusFlag);
        } catch (Exception e) {
            Ln.e(e, "RemindersService.deleteReminderForUpdate", new Object[0]);
            return false;
        }
    }

    public String getEventNameForReminder(ReminderObject reminderObject) {
        if (reminderObject == null) {
            return "";
        }
        String str = "";
        int reminderType = reminderObject.getReminderType();
        if (reminderType == 1) {
            String mealName = reminderObject.getMealName();
            str = Strings.equals(mealName, this.context.getString(R.string.breakfast)) ? Events.REMINDER_BREAKFAST : Strings.equals(mealName, this.context.getString(R.string.lunch)) ? Events.REMINDER_LUNCH : Strings.equals(mealName, this.context.getString(R.string.dinner)) ? Events.REMINDER_DINNER : Strings.equals(mealName, this.context.getString(R.string.snacks)) ? Events.REMINDER_SNACK : Events.REMINDER_CUSTOM_MEAL;
        } else if (reminderType == 4) {
            str = Events.REMINDER_WEIGHT;
        } else if (reminderType == 3 || reminderType == 2) {
            int intervalInDays = reminderObject.getIntervalInDays();
            if (intervalInDays == 1) {
                str = Events.REMINDER_ANY_ITEM_ONE_DAY;
            } else if (intervalInDays == 3) {
                str = Events.REMINDER_ANY_ITEM_THREE_DAYS;
            } else if (intervalInDays == 7) {
                str = Events.REMINDER_ANY_ITEM_SEVEN_DAYS;
            }
        }
        return str;
    }

    private ReminderObject createDefaultWeightReminder(boolean z, boolean z2) {
        ReminderObject reminderObject = new ReminderObject();
        reminderObject.setReminderType(4);
        reminderObject.setMealName("");
        reminderObject.setMealId("");
        reminderObject.setIntervalInDays(0);
        reminderObject.setWallClockTime(Reminder.SEVEN_THIRTY_OCLOCK);
        reminderObject.setIsActive(z);
        reminderObject.setIsAutoCreated(z2);
        reminderObject.setFrequency(Reminder.WEEKLY_FREQUENCY);
        reminderObject.setDayOfMonth(0);
        reminderObject.setDayOfWeek(DateTime.MONDAY);
        reminderObject.setStatusFlag(StatusFlag.DEFAULT);
        return reminderObject;
    }

    private String[] generateRandomTimes() {
        ArrayList arrayList = new ArrayList();
        int[] iArr = {10, 14, 20};
        Random random = new Random();
        for (int i : iArr) {
            int nextInt = random.nextInt(30) - 15;
            if (nextInt < 0) {
                i--;
            }
            if (nextInt < 0) {
                nextInt += 60;
            }
            arrayList.add(String.format("%02d:%02d:00", new Object[]{Integer.valueOf(i), Integer.valueOf(nextInt)}));
        }
        return (String[]) arrayList.toArray(new String[arrayList.size()]);
    }

    public String defaultReminderWallClockTimeForMealName(String str) {
        if (Strings.equalsIgnoreCase(this.context.getString(R.string.breakfast), str)) {
            return Reminder.TEN_OCLOCK;
        }
        return Strings.equalsIgnoreCase(this.context.getString(R.string.dinner), str) ? Reminder.EIGHT_OCLOCK : Reminder.TWO_OCLOCK;
    }

    public boolean isWeightReminderEnabled() {
        return ((ConfigService) this.configService.get()).isVariantEnabled(TrackWeightNotifications201510.NAME);
    }
}
