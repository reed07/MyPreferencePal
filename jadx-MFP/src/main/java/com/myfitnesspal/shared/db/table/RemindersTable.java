package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v15.ReminderObject;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.inject.Inject;

public class RemindersTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_SYNC = "reminders_sync_index";
    private static final String IDX_SYNC_V2 = "reminders_sync_v2_index";
    public static final String TABLE_NAME = "reminders";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String DAY_OF_MONTH = "day_of_month";
        public static final String DAY_OF_WEEK = "day_of_week";
        public static final String FLAGS = "is_active";
        public static final String FREQUENCY = "frequency";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String MEAL_ID = "meal_id";
        public static final String MEAL_NAME = "meal_name";
        public static final String OFFSET_FROM_MIDNIGHT_UTC = "offset_from_midnight_utc";
        public static final String REMINDER_INTERVAL_IN_DAYS = "reminder_interval_in_days";
        public static final String REMINDER_TYPE = "reminder_type";
        public static final String STATUS_FLAG = "status_flag";
        public static final String USER_ID = "user_id";
        public static final String WALL_CLOCK_TIME = "wall_clock_time";
    }

    public enum StatusFlag {
        DEFAULT(0),
        DELETED(-1),
        UPLOADED(1);
        
        private static Map<Integer, StatusFlag> lookupMap;
        private int value;

        static {
            int i;
            StatusFlag[] values;
            lookupMap = new HashMap();
            for (StatusFlag statusFlag : values()) {
                lookupMap.put(Integer.valueOf(statusFlag.getValue()), statusFlag);
            }
        }

        private StatusFlag(int i) {
            this.value = i;
        }

        public int getValue() {
            return this.value;
        }

        public static StatusFlag getStatusFlag(int i) {
            return (StatusFlag) lookupMap.get(Integer.valueOf(i));
        }
    }

    @Inject
    public RemindersTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "reminders");
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "reminder_type integer not null", "reminder_interval_in_days integer not null", "meal_name text", "offset_from_midnight_utc integer not null", "wall_clock_time text");
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(10, i, i2)) {
            dropIndexIfExists(IDX_SYNC);
            dropTableIfExists();
        }
        if (shouldRunUpgrade(14, i, i2)) {
            onCreate();
        }
        if (shouldRunUpgrade(22, i, i2)) {
            addColumn(Columns.FLAGS, " integer not null default 1");
        }
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
        if (shouldRunUpgrade(32, i, i2)) {
            addColumn("frequency", "text");
            addColumn("day_of_month", " integer not null default 0");
            addColumn("day_of_week", "text");
        }
        if (shouldRunUpgrade(34, i, i2)) {
            addColumn(Columns.STATUS_FLAG, " integer not null default 0");
        }
        if (shouldRunUpgrade(39, i, i2)) {
            addColumn("meal_id", " text");
        }
    }

    public long insertIfMissing(long j, ReminderObject reminderObject, boolean z, @Nonnull DbConnectionManager dbConnectionManager) {
        if (!any(new String[]{"id"}, "user_id = ? AND master_id = ?", Long.valueOf(j), Long.valueOf(reminderObject.getMasterId()))) {
            return insert(j, reminderObject);
        }
        if (z) {
            ReminderObject queryReminderForMasterId = queryReminderForMasterId(Long.valueOf(reminderObject.getMasterId()));
            if (queryReminderForMasterId == null || queryReminderForMasterId.getStatusFlag() != StatusFlag.DELETED) {
                updateStatusFlag(queryReminderForMasterId.getLocalId(), StatusFlag.DEFAULT);
            } else {
                deleteReminderWithMasterId(j, queryReminderForMasterId.getMasterId(), dbConnectionManager);
            }
        }
        return reminderObject.getLocalId();
    }

    public long insert(long j, ReminderObject reminderObject) {
        ContentValues contentValues = new ContentValues();
        if (reminderObject.hasMasterId()) {
            contentValues.put("master_id", Long.valueOf(reminderObject.getMasterId()));
        } else {
            contentValues.putNull("master_id");
        }
        if (reminderObject.hasUid()) {
            contentValues.put("uid", reminderObject.getUid());
        } else {
            contentValues.putNull("uid");
        }
        contentValues.put("user_id", Long.valueOf(j));
        contentValues.put("reminder_type", Integer.valueOf(reminderObject.getReminderType()));
        contentValues.put(Columns.REMINDER_INTERVAL_IN_DAYS, Integer.valueOf(reminderObject.getIntervalInDays()));
        contentValues.put("meal_name", reminderObject.getMealName());
        contentValues.put("meal_id", reminderObject.getMealId());
        contentValues.put(Columns.OFFSET_FROM_MIDNIGHT_UTC, Integer.valueOf(0));
        contentValues.put("wall_clock_time", reminderObject.getWallClockTime());
        contentValues.put(Columns.FLAGS, Integer.valueOf(reminderObject.getFlags()));
        contentValues.put("frequency", reminderObject.getFrequency());
        contentValues.put("day_of_month", Integer.valueOf(reminderObject.getDayOfMonth()));
        contentValues.put("day_of_week", reminderObject.getDayOfWeek());
        contentValues.put(Columns.STATUS_FLAG, Integer.valueOf(reminderObject.getStatusFlag().getValue()));
        long insertData = insertData(contentValues);
        reminderObject.setLocalId(insertData);
        return insertData;
    }

    public void deleteReminder(long j, ReminderObject reminderObject, @Nonnull DbConnectionManager dbConnectionManager) {
        if (reminderObject != null) {
            if (reminderObject.hasMasterId()) {
                deleteReminderWithMasterId(j, reminderObject.getMasterId(), dbConnectionManager);
            } else {
                deleteReminderWithLocalId(reminderObject.getLocalId());
            }
        }
    }

    public void deleteReminderWithMasterId(long j, long j2, @Nonnull DbConnectionManager dbConnectionManager) {
        deleteReminderWithMasterId(j2);
        if (j2 > 0) {
            dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(j, 20, j2, false);
        }
    }

    public void deleteReminderForUpdate(long j, ReminderObject reminderObject, @Nonnull DbConnectionManager dbConnectionManager) {
        if (reminderObject != null) {
            if (reminderObject.hasMasterId()) {
                dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(j, 20, reminderObject.getMasterId(), false);
            } else if (queryReminderForId(Long.valueOf(reminderObject.getLocalId())).getStatusFlag() != StatusFlag.UPLOADED) {
                deleteReminderWithLocalId(reminderObject.getLocalId());
            }
            updateStatusFlag(reminderObject.getLocalId(), StatusFlag.DELETED);
        }
    }

    public void deleteReminderWithLocalId(long j) {
        deleteData("id = ?", Long.valueOf(j));
    }

    public void deleteReminderWithMasterId(long j) {
        deleteData("master_id = ?", Long.valueOf(j));
    }

    public boolean updateStatusFlag(long j, StatusFlag statusFlag) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.STATUS_FLAG, Integer.valueOf(statusFlag.getValue()));
        if (updateData(contentValues, "id=?", Long.valueOf(j)) > 0) {
            return true;
        }
        return false;
    }

    /* JADX WARNING: type inference failed for: r1v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0114, code lost:
        if (r1 != 0) goto L_0x011f;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x011d, code lost:
        if (r1 == 0) goto L_0x0122;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x011f, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0122, code lost:
        return r0;
     */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], android.database.Cursor]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 95
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<com.myfitnesspal.shared.model.v15.ReminderObject> getRemindersForUser(long r9) {
        /*
            r8 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            r0.<init>()
            r1 = 0
            java.lang.String r2 = "%s=? AND %s!=?"
            r3 = 2
            java.lang.Object[] r4 = new java.lang.Object[r3]     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r5 = "user_id"
            r6 = 0
            r4[r6] = r5     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r5 = "status_flag"
            r7 = 1
            r4[r7] = r5     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r2 = java.lang.String.format(r2, r4)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.Long r9 = java.lang.Long.valueOf(r9)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r9 = com.uacf.core.util.Strings.toString(r9)     // Catch:{ SQLiteException -> 0x0119 }
            r3[r6] = r9     // Catch:{ SQLiteException -> 0x0119 }
            com.myfitnesspal.shared.db.table.RemindersTable$StatusFlag r9 = com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag.DELETED     // Catch:{ SQLiteException -> 0x0119 }
            int r9 = r9.getValue()     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.Integer r9 = java.lang.Integer.valueOf(r9)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r9 = com.uacf.core.util.Strings.toString(r9)     // Catch:{ SQLiteException -> 0x0119 }
            r3[r7] = r9     // Catch:{ SQLiteException -> 0x0119 }
            android.database.Cursor r1 = r8.queryData(r1, r2, r3)     // Catch:{ SQLiteException -> 0x0119 }
        L_0x0039:
            boolean r9 = r1.moveToNext()     // Catch:{ SQLiteException -> 0x0119 }
            if (r9 == 0) goto L_0x0114
            com.myfitnesspal.shared.model.v15.ReminderObject r9 = new com.myfitnesspal.shared.model.v15.ReminderObject     // Catch:{ SQLiteException -> 0x0119 }
            r9.<init>()     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "id"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            long r2 = r1.getLong(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setLocalId(r2)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "master_id"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            long r2 = r1.getLong(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setMasterId(r2)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "uid"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setUid(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "reminder_type"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r1.getInt(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setReminderType(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "reminder_interval_in_days"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r1.getInt(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setIntervalInDays(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "meal_name"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setMealName(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r9.getReminderType()     // Catch:{ SQLiteException -> 0x0119 }
            r2 = 3
            if (r10 != r2) goto L_0x00a0
            java.lang.String r10 = ""
            goto L_0x00ae
        L_0x00a0:
            java.lang.String r10 = "meal_id"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ SQLiteException -> 0x0119 }
        L_0x00ae:
            r9.setMealId(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "wall_clock_time"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setWallClockTime(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "is_active"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r1.getInt(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setFlags(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "frequency"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setFrequency(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "day_of_month"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r1.getInt(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setDayOfMonth(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "day_of_week"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = r1.getString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = com.uacf.core.util.Strings.toString(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setDayOfWeek(r10)     // Catch:{ SQLiteException -> 0x0119 }
            java.lang.String r10 = "status_flag"
            int r10 = r1.getColumnIndex(r10)     // Catch:{ SQLiteException -> 0x0119 }
            int r10 = r1.getInt(r10)     // Catch:{ SQLiteException -> 0x0119 }
            com.myfitnesspal.shared.db.table.RemindersTable$StatusFlag r10 = com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag.getStatusFlag(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r9.setStatusFlag(r10)     // Catch:{ SQLiteException -> 0x0119 }
            r0.add(r9)     // Catch:{ SQLiteException -> 0x0119 }
            goto L_0x0039
        L_0x0114:
            if (r1 == 0) goto L_0x0122
            goto L_0x011f
        L_0x0117:
            r9 = move-exception
            goto L_0x0123
        L_0x0119:
            r9 = move-exception
            com.uacf.core.util.Ln.e(r9)     // Catch:{ all -> 0x0117 }
            if (r1 == 0) goto L_0x0122
        L_0x011f:
            r1.close()
        L_0x0122:
            return r0
        L_0x0123:
            if (r1 == 0) goto L_0x0128
            r1.close()
        L_0x0128:
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.table.RemindersTable.getRemindersForUser(long):java.util.List");
    }

    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r1v4, types: [com.myfitnesspal.shared.model.v15.ReminderObject] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r2v2, types: [com.myfitnesspal.shared.model.v15.ReminderObject] */
    /* JADX WARNING: type inference failed for: r1v6, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r1v3
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[OBJECT, ARRAY], java.lang.String]
  uses: [com.myfitnesspal.shared.model.v15.ReminderObject, java.lang.String]
  mth insns count: 95
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v15.ReminderObject queryReminderForId(java.lang.Long r11) {
        /*
            r10 = this;
            java.lang.String r0 = "*"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r1 = java.lang.String.valueOf(r11)
            r9 = 0
            r0[r9] = r1
            java.lang.String r2 = "reminders"
            java.lang.String r4 = "id=?"
            r1 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r1 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r1, r2, r3, r4, r5, r6, r7, r8)
            android.database.Cursor r0 = super.rawQuery(r1, r0)
            r1 = 0
            if (r0 == 0) goto L_0x0110
            boolean r2 = r0.moveToFirst()     // Catch:{ all -> 0x010b }
            if (r2 == 0) goto L_0x0107
            com.myfitnesspal.shared.model.v15.ReminderObject r2 = new com.myfitnesspal.shared.model.v15.ReminderObject     // Catch:{ all -> 0x010b }
            r2.<init>()     // Catch:{ all -> 0x010b }
            long r3 = r11.longValue()     // Catch:{ all -> 0x010b }
            r2.setLocalId(r3)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "master_id"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            long r3 = r0.getLong(r11)     // Catch:{ all -> 0x010b }
            r2.setMasterId(r3)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "uid"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x010b }
            r2.setUid(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "reminder_type"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            int r11 = r0.getInt(r11)     // Catch:{ all -> 0x010b }
            r2.setReminderType(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "reminder_interval_in_days"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            int r11 = r0.getInt(r11)     // Catch:{ all -> 0x010b }
            r2.setIntervalInDays(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "meal_name"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x007b
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x010b }
            goto L_0x007c
        L_0x007b:
            r11 = r1
        L_0x007c:
            r2.setMealName(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "meal_id"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = com.uacf.core.util.Strings.toString(r11)     // Catch:{ all -> 0x010b }
            r2.setMealId(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "wall_clock_time"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x00a1
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x010b }
            goto L_0x00a2
        L_0x00a1:
            r11 = r1
        L_0x00a2:
            r2.setWallClockTime(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "is_active"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x00b6
            int r11 = r0.getInt(r11)     // Catch:{ all -> 0x010b }
            goto L_0x00b7
        L_0x00b6:
            r11 = 0
        L_0x00b7:
            r2.setFlags(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "frequency"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x00cb
            java.lang.String r11 = r0.getString(r11)     // Catch:{ all -> 0x010b }
            goto L_0x00cc
        L_0x00cb:
            r11 = r1
        L_0x00cc:
            r2.setFrequency(r11)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "day_of_month"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x00df
            int r9 = r0.getInt(r11)     // Catch:{ all -> 0x010b }
        L_0x00df:
            r2.setDayOfMonth(r9)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "day_of_week"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            boolean r3 = r0.isNull(r11)     // Catch:{ all -> 0x010b }
            if (r3 != 0) goto L_0x00f2
            java.lang.String r1 = r0.getString(r11)     // Catch:{ all -> 0x010b }
        L_0x00f2:
            r2.setDayOfWeek(r1)     // Catch:{ all -> 0x010b }
            java.lang.String r11 = "status_flag"
            int r11 = r0.getColumnIndex(r11)     // Catch:{ all -> 0x010b }
            int r11 = r0.getInt(r11)     // Catch:{ all -> 0x010b }
            com.myfitnesspal.shared.db.table.RemindersTable$StatusFlag r11 = com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag.getStatusFlag(r11)     // Catch:{ all -> 0x010b }
            r2.setStatusFlag(r11)     // Catch:{ all -> 0x010b }
            r1 = r2
        L_0x0107:
            r0.close()
            goto L_0x0110
        L_0x010b:
            r11 = move-exception
            r0.close()
            throw r11
        L_0x0110:
            return r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.table.RemindersTable.queryReminderForId(java.lang.Long):com.myfitnesspal.shared.model.v15.ReminderObject");
    }

    /* JADX WARNING: type inference failed for: r0v3 */
    /* JADX WARNING: type inference failed for: r0v4, types: [com.myfitnesspal.shared.model.v15.ReminderObject] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.myfitnesspal.shared.model.v15.ReminderObject] */
    /* JADX WARNING: type inference failed for: r0v7, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v12 */
    /* JADX WARNING: type inference failed for: r0v13, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v14 */
    /* JADX WARNING: type inference failed for: r0v15 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r0v3
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY], ?[OBJECT, ARRAY], java.lang.String]
  uses: [com.myfitnesspal.shared.model.v15.ReminderObject, java.lang.String]
  mth insns count: 98
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Unknown variable types count: 3 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v15.ReminderObject queryReminderForMasterId(java.lang.Long r11) {
        /*
            r10 = this;
            java.lang.String r0 = "*"
            java.lang.String[] r3 = new java.lang.String[]{r0}
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r11 = java.lang.String.valueOf(r11)
            r9 = 0
            r0[r9] = r11
            java.lang.String r2 = "reminders"
            java.lang.String r4 = "master_id=?"
            r1 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r11 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r1, r2, r3, r4, r5, r6, r7, r8)
            android.database.Cursor r11 = super.rawQuery(r11, r0)
            r0 = 0
            if (r11 == 0) goto L_0x0117
            boolean r1 = r11.moveToFirst()     // Catch:{ all -> 0x0112 }
            if (r1 == 0) goto L_0x010e
            com.myfitnesspal.shared.model.v15.ReminderObject r1 = new com.myfitnesspal.shared.model.v15.ReminderObject     // Catch:{ all -> 0x0112 }
            r1.<init>()     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "id"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0112 }
            long r2 = (long) r2     // Catch:{ all -> 0x0112 }
            r1.setLocalId(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "master_id"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            long r2 = r11.getLong(r2)     // Catch:{ all -> 0x0112 }
            r1.setMasterId(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "uid"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
            r1.setUid(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "reminder_type"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0112 }
            r1.setReminderType(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "reminder_interval_in_days"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0112 }
            r1.setIntervalInDays(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "meal_name"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x0082
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
            goto L_0x0083
        L_0x0082:
            r2 = r0
        L_0x0083:
            r1.setMealName(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "meal_id"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = com.uacf.core.util.Strings.toString(r2)     // Catch:{ all -> 0x0112 }
            r1.setMealId(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "wall_clock_time"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x00a8
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
            goto L_0x00a9
        L_0x00a8:
            r2 = r0
        L_0x00a9:
            r1.setWallClockTime(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "is_active"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x00bd
            int r2 = r11.getInt(r2)     // Catch:{ all -> 0x0112 }
            goto L_0x00be
        L_0x00bd:
            r2 = 0
        L_0x00be:
            r1.setFlags(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "frequency"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x00d2
            java.lang.String r2 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
            goto L_0x00d3
        L_0x00d2:
            r2 = r0
        L_0x00d3:
            r1.setFrequency(r2)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "day_of_month"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x00e6
            int r9 = r11.getInt(r2)     // Catch:{ all -> 0x0112 }
        L_0x00e6:
            r1.setDayOfMonth(r9)     // Catch:{ all -> 0x0112 }
            java.lang.String r2 = "day_of_week"
            int r2 = r11.getColumnIndex(r2)     // Catch:{ all -> 0x0112 }
            boolean r3 = r11.isNull(r2)     // Catch:{ all -> 0x0112 }
            if (r3 != 0) goto L_0x00f9
            java.lang.String r0 = r11.getString(r2)     // Catch:{ all -> 0x0112 }
        L_0x00f9:
            r1.setDayOfWeek(r0)     // Catch:{ all -> 0x0112 }
            java.lang.String r0 = "status_flag"
            int r0 = r11.getColumnIndex(r0)     // Catch:{ all -> 0x0112 }
            int r0 = r11.getInt(r0)     // Catch:{ all -> 0x0112 }
            com.myfitnesspal.shared.db.table.RemindersTable$StatusFlag r0 = com.myfitnesspal.shared.db.table.RemindersTable.StatusFlag.getStatusFlag(r0)     // Catch:{ all -> 0x0112 }
            r1.setStatusFlag(r0)     // Catch:{ all -> 0x0112 }
            r0 = r1
        L_0x010e:
            r11.close()
            goto L_0x0117
        L_0x0112:
            r0 = move-exception
            r11.close()
            throw r0
        L_0x0117:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.table.RemindersTable.queryReminderForMasterId(java.lang.Long):com.myfitnesspal.shared.model.v15.ReminderObject");
    }
}
