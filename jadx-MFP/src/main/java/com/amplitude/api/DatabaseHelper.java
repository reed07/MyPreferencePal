package com.amplitude.api;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import com.mopub.common.Constants;
import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

class DatabaseHelper extends SQLiteOpenHelper {
    static final Map<String, DatabaseHelper> instances = new HashMap();
    private static final AmplitudeLog logger = AmplitudeLog.getLogger();
    private File file;
    private String instanceName;

    @Deprecated
    static DatabaseHelper getDatabaseHelper(Context context) {
        return getDatabaseHelper(context, null);
    }

    static synchronized DatabaseHelper getDatabaseHelper(Context context, String str) {
        DatabaseHelper databaseHelper;
        synchronized (DatabaseHelper.class) {
            String normalizeInstanceName = Utils.normalizeInstanceName(str);
            databaseHelper = (DatabaseHelper) instances.get(normalizeInstanceName);
            if (databaseHelper == null) {
                databaseHelper = new DatabaseHelper(context.getApplicationContext(), normalizeInstanceName);
                instances.put(normalizeInstanceName, databaseHelper);
            }
        }
        return databaseHelper;
    }

    private static String getDatabaseName(String str) {
        if (Utils.isEmptyString(str) || str.equals("$default_instance")) {
            return "com.amplitude.api";
        }
        StringBuilder sb = new StringBuilder();
        sb.append("com.amplitude.api_");
        sb.append(str);
        return sb.toString();
    }

    protected DatabaseHelper(Context context, String str) {
        super(context, getDatabaseName(str), null, 3);
        this.file = context.getDatabasePath(getDatabaseName(str));
        this.instanceName = Utils.normalizeInstanceName(str);
    }

    public void onCreate(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS events (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);");
        sQLiteDatabase.execSQL("CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0038, code lost:
        if (r6 <= 2) goto L_0x0046;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void onUpgrade(android.database.sqlite.SQLiteDatabase r4, int r5, int r6) {
        /*
            r3 = this;
            if (r5 <= r6) goto L_0x000f
            com.amplitude.api.AmplitudeLog r5 = logger
            java.lang.String r6 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r0 = "onUpgrade() with invalid oldVersion and newVersion"
            r5.e(r6, r0)
            r3.resetDatabase(r4)
            return
        L_0x000f:
            r0 = 1
            if (r6 > r0) goto L_0x0013
            return
        L_0x0013:
            switch(r5) {
                case 1: goto L_0x0032;
                case 2: goto L_0x003b;
                case 3: goto L_0x0046;
                default: goto L_0x0016;
            }
        L_0x0016:
            com.amplitude.api.AmplitudeLog r6 = logger
            java.lang.String r0 = "com.amplitude.api.DatabaseHelper"
            java.lang.StringBuilder r1 = new java.lang.StringBuilder
            r1.<init>()
            java.lang.String r2 = "onUpgrade() with unknown oldVersion "
            r1.append(r2)
            r1.append(r5)
            java.lang.String r5 = r1.toString()
            r6.e(r0, r5)
            r3.resetDatabase(r4)
            goto L_0x0046
        L_0x0032:
            java.lang.String r5 = "CREATE TABLE IF NOT EXISTS store (key TEXT PRIMARY KEY NOT NULL, value TEXT);"
            r4.execSQL(r5)
            r5 = 2
            if (r6 > r5) goto L_0x003b
            goto L_0x0046
        L_0x003b:
            java.lang.String r5 = "CREATE TABLE IF NOT EXISTS identifys (id INTEGER PRIMARY KEY AUTOINCREMENT, event TEXT);"
            r4.execSQL(r5)
            java.lang.String r5 = "CREATE TABLE IF NOT EXISTS long_store (key TEXT PRIMARY KEY NOT NULL, value INTEGER);"
            r4.execSQL(r5)
            r4 = 3
        L_0x0046:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.onUpgrade(android.database.sqlite.SQLiteDatabase, int, int):void");
    }

    private void resetDatabase(SQLiteDatabase sQLiteDatabase) {
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS long_store");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS events");
        sQLiteDatabase.execSQL("DROP TABLE IF EXISTS identifys");
        onCreate(sQLiteDatabase);
    }

    /* access modifiers changed from: 0000 */
    public synchronized long insertOrReplaceKeyValue(String str, String str2) {
        long j;
        if (str2 == null) {
            j = deleteKeyFromTable("store", str);
        } else {
            j = insertOrReplaceKeyValueToTable("store", str, str2);
        }
        return j;
    }

    /* access modifiers changed from: 0000 */
    public synchronized long insertOrReplaceKeyLongValue(String str, Long l) {
        long j;
        if (l == null) {
            j = deleteKeyFromTable("long_store", str);
        } else {
            j = insertOrReplaceKeyValueToTable("long_store", str, l);
        }
        return j;
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0060=Splitter:B:23:0x0060, B:16:0x0044=Splitter:B:16:0x0044} */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:26:0x0065=Splitter:B:26:0x0065, B:21:0x004c=Splitter:B:21:0x004c} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized long insertOrReplaceKeyValueToTable(java.lang.String r10, java.lang.String r11, java.lang.Object r12) {
        /*
            r9 = this;
            monitor-enter(r9)
            r0 = 0
            r1 = 1
            r2 = -1
            android.database.sqlite.SQLiteDatabase r4 = r9.getWritableDatabase()     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            android.content.ContentValues r5 = new android.content.ContentValues     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            r5.<init>()     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            java.lang.String r6 = "key"
            r5.put(r6, r11)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            boolean r11 = r12 instanceof java.lang.Long     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            if (r11 == 0) goto L_0x001f
            java.lang.String r11 = "value"
            java.lang.Long r12 = (java.lang.Long) r12     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            r5.put(r11, r12)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            goto L_0x0026
        L_0x001f:
            java.lang.String r11 = "value"
            java.lang.String r12 = (java.lang.String) r12     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            r5.put(r11, r12)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
        L_0x0026:
            r11 = 0
            r12 = 5
            long r11 = r4.insertWithOnConflict(r10, r11, r5, r12)     // Catch:{ SQLiteException -> 0x0064, StackOverflowError -> 0x004b }
            int r4 = (r11 > r2 ? 1 : (r11 == r2 ? 0 : -1))
            if (r4 != 0) goto L_0x0044
            com.amplitude.api.AmplitudeLog r2 = logger     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003a }
            java.lang.String r3 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r4 = "Insert failed"
            r2.w(r3, r4)     // Catch:{ SQLiteException -> 0x003f, StackOverflowError -> 0x003a }
            goto L_0x0044
        L_0x003a:
            r2 = move-exception
            r7 = r11
            r11 = r2
            r2 = r7
            goto L_0x004c
        L_0x003f:
            r2 = move-exception
            r7 = r11
            r11 = r2
            r2 = r7
            goto L_0x0065
        L_0x0044:
            r9.close()     // Catch:{ all -> 0x0080 }
            r2 = r11
            goto L_0x007a
        L_0x0049:
            r10 = move-exception
            goto L_0x007c
        L_0x004b:
            r11 = move-exception
        L_0x004c:
            com.amplitude.api.AmplitudeLog r12 = logger     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r5 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0049 }
            r1[r0] = r10     // Catch:{ all -> 0x0049 }
            java.lang.String r10 = java.lang.String.format(r5, r1)     // Catch:{ all -> 0x0049 }
            r12.e(r4, r10, r11)     // Catch:{ all -> 0x0049 }
            r9.delete()     // Catch:{ all -> 0x0049 }
        L_0x0060:
            r9.close()     // Catch:{ all -> 0x0080 }
            goto L_0x007a
        L_0x0064:
            r11 = move-exception
        L_0x0065:
            com.amplitude.api.AmplitudeLog r12 = logger     // Catch:{ all -> 0x0049 }
            java.lang.String r4 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r5 = "insertOrReplaceKeyValue in %s failed"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x0049 }
            r1[r0] = r10     // Catch:{ all -> 0x0049 }
            java.lang.String r10 = java.lang.String.format(r5, r1)     // Catch:{ all -> 0x0049 }
            r12.e(r4, r10, r11)     // Catch:{ all -> 0x0049 }
            r9.delete()     // Catch:{ all -> 0x0049 }
            goto L_0x0060
        L_0x007a:
            monitor-exit(r9)
            return r2
        L_0x007c:
            r9.close()     // Catch:{ all -> 0x0080 }
            throw r10     // Catch:{ all -> 0x0080 }
        L_0x0080:
            r10 = move-exception
            monitor-exit(r9)
            throw r10
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.insertOrReplaceKeyValueToTable(java.lang.String, java.lang.String, java.lang.Object):long");
    }

    /* access modifiers changed from: 0000 */
    public synchronized long deleteKeyFromTable(String str, String str2) {
        long j;
        j = -1;
        try {
            j = (long) getWritableDatabase().delete(str, "key=?", new String[]{str2});
            close();
        } catch (SQLiteException e) {
            logger.e("com.amplitude.api.DatabaseHelper", String.format("deleteKey from %s failed", new Object[]{str}), e);
            delete();
            close();
            return j;
        } catch (StackOverflowError e2) {
            try {
                logger.e("com.amplitude.api.DatabaseHelper", String.format("deleteKey from %s failed", new Object[]{str}), e2);
                delete();
                close();
                return j;
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        return j;
    }

    /* access modifiers changed from: 0000 */
    public synchronized long addEvent(String str) {
        return addEventToTable(Constants.VIDEO_TRACKING_EVENTS_KEY, str);
    }

    /* access modifiers changed from: 0000 */
    public synchronized long addIdentify(String str) {
        return addEventToTable("identifys", str);
    }

    private synchronized long addEventToTable(String str, String str2) {
        long j;
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("event", str2);
            j = writableDatabase.insert(str, null, contentValues);
            if (j == -1) {
                try {
                    logger.w("com.amplitude.api.DatabaseHelper", String.format("Insert into %s failed", new Object[]{str}));
                } catch (SQLiteException e) {
                    e = e;
                } catch (StackOverflowError e2) {
                    e = e2;
                    try {
                        logger.e("com.amplitude.api.DatabaseHelper", String.format("addEvent to %s failed", new Object[]{str}), e);
                        delete();
                        close();
                        return j;
                    } catch (Throwable th) {
                        close();
                        throw th;
                    }
                }
            }
        } catch (SQLiteException e3) {
            e = e3;
            j = -1;
            logger.e("com.amplitude.api.DatabaseHelper", String.format("addEvent to %s failed", new Object[]{str}), e);
            delete();
            close();
            return j;
        } catch (StackOverflowError e4) {
            e = e4;
            j = -1;
            logger.e("com.amplitude.api.DatabaseHelper", String.format("addEvent to %s failed", new Object[]{str}), e);
            delete();
            close();
            return j;
        }
        close();
        return j;
    }

    /* access modifiers changed from: 0000 */
    public synchronized String getValue(String str) {
        return (String) getValueFromTable("store", str);
    }

    /* access modifiers changed from: 0000 */
    public synchronized Long getLongValue(String str) {
        return (Long) getValueFromTable("long_store", str);
    }

    /* access modifiers changed from: protected */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x0055 A[SYNTHETIC, Splitter:B:26:0x0055] */
    /* JADX WARNING: Removed duplicated region for block: B:33:0x0071 A[SYNTHETIC, Splitter:B:33:0x0071] */
    /* JADX WARNING: Removed duplicated region for block: B:40:0x008d A[SYNTHETIC, Splitter:B:40:0x008d] */
    /* JADX WARNING: Removed duplicated region for block: B:46:0x0096 A[SYNTHETIC, Splitter:B:46:0x0096] */
    /* JADX WARNING: Unknown top exception splitter block from list: {B:23:0x0050=Splitter:B:23:0x0050, B:37:0x0077=Splitter:B:37:0x0077, B:30:0x005b=Splitter:B:30:0x005b} */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.lang.Object getValueFromTable(java.lang.String r14, java.lang.String r15) {
        /*
            r13 = this;
            monitor-enter(r13)
            r0 = 0
            r1 = 0
            r2 = 1
            android.database.sqlite.SQLiteDatabase r4 = r13.getReadableDatabase()     // Catch:{ SQLiteException -> 0x0075, StackOverflowError -> 0x0059, RuntimeException -> 0x004e, all -> 0x004b }
            java.lang.String r3 = "key"
            java.lang.String r5 = "value"
            java.lang.String[] r6 = new java.lang.String[]{r3, r5}     // Catch:{ SQLiteException -> 0x0075, StackOverflowError -> 0x0059, RuntimeException -> 0x004e, all -> 0x004b }
            java.lang.String r7 = "key = ?"
            java.lang.String[] r8 = new java.lang.String[r2]     // Catch:{ SQLiteException -> 0x0075, StackOverflowError -> 0x0059, RuntimeException -> 0x004e, all -> 0x004b }
            r8[r1] = r15     // Catch:{ SQLiteException -> 0x0075, StackOverflowError -> 0x0059, RuntimeException -> 0x004e, all -> 0x004b }
            r9 = 0
            r10 = 0
            r11 = 0
            r12 = 0
            r3 = r13
            r5 = r14
            android.database.Cursor r15 = r3.queryDb(r4, r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ SQLiteException -> 0x0075, StackOverflowError -> 0x0059, RuntimeException -> 0x004e, all -> 0x004b }
            boolean r3 = r15.moveToFirst()     // Catch:{ SQLiteException -> 0x0049, StackOverflowError -> 0x0047, RuntimeException -> 0x0045 }
            if (r3 == 0) goto L_0x003c
            java.lang.String r3 = "store"
            boolean r3 = r14.equals(r3)     // Catch:{ SQLiteException -> 0x0049, StackOverflowError -> 0x0047, RuntimeException -> 0x0045 }
            if (r3 == 0) goto L_0x0033
            java.lang.String r14 = r15.getString(r2)     // Catch:{ SQLiteException -> 0x0049, StackOverflowError -> 0x0047, RuntimeException -> 0x0045 }
            goto L_0x003b
        L_0x0033:
            long r3 = r15.getLong(r2)     // Catch:{ SQLiteException -> 0x0049, StackOverflowError -> 0x0047, RuntimeException -> 0x0045 }
            java.lang.Long r14 = java.lang.Long.valueOf(r3)     // Catch:{ SQLiteException -> 0x0049, StackOverflowError -> 0x0047, RuntimeException -> 0x0045 }
        L_0x003b:
            r0 = r14
        L_0x003c:
            if (r15 == 0) goto L_0x0041
            r15.close()     // Catch:{ all -> 0x009d }
        L_0x0041:
            r13.close()     // Catch:{ all -> 0x009d }
            goto L_0x0091
        L_0x0045:
            r14 = move-exception
            goto L_0x0050
        L_0x0047:
            r3 = move-exception
            goto L_0x005b
        L_0x0049:
            r3 = move-exception
            goto L_0x0077
        L_0x004b:
            r14 = move-exception
            r15 = r0
            goto L_0x0094
        L_0x004e:
            r14 = move-exception
            r15 = r0
        L_0x0050:
            convertIfCursorWindowException(r14)     // Catch:{ all -> 0x0093 }
            if (r15 == 0) goto L_0x0041
            r15.close()     // Catch:{ all -> 0x009d }
            goto L_0x0041
        L_0x0059:
            r3 = move-exception
            r15 = r0
        L_0x005b:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0093 }
            r2[r1] = r14     // Catch:{ all -> 0x0093 }
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch:{ all -> 0x0093 }
            r4.e(r5, r14, r3)     // Catch:{ all -> 0x0093 }
            r13.delete()     // Catch:{ all -> 0x0093 }
            if (r15 == 0) goto L_0x0041
            r15.close()     // Catch:{ all -> 0x009d }
            goto L_0x0041
        L_0x0075:
            r3 = move-exception
            r15 = r0
        L_0x0077:
            com.amplitude.api.AmplitudeLog r4 = logger     // Catch:{ all -> 0x0093 }
            java.lang.String r5 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r6 = "getValue from %s failed"
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0093 }
            r2[r1] = r14     // Catch:{ all -> 0x0093 }
            java.lang.String r14 = java.lang.String.format(r6, r2)     // Catch:{ all -> 0x0093 }
            r4.e(r5, r14, r3)     // Catch:{ all -> 0x0093 }
            r13.delete()     // Catch:{ all -> 0x0093 }
            if (r15 == 0) goto L_0x0041
            r15.close()     // Catch:{ all -> 0x009d }
            goto L_0x0041
        L_0x0091:
            monitor-exit(r13)
            return r0
        L_0x0093:
            r14 = move-exception
        L_0x0094:
            if (r15 == 0) goto L_0x0099
            r15.close()     // Catch:{ all -> 0x009d }
        L_0x0099:
            r13.close()     // Catch:{ all -> 0x009d }
            throw r14     // Catch:{ all -> 0x009d }
        L_0x009d:
            r14 = move-exception
            monitor-exit(r13)
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getValueFromTable(java.lang.String, java.lang.String):java.lang.Object");
    }

    /* access modifiers changed from: 0000 */
    public synchronized List<JSONObject> getEvents(long j, long j2) throws JSONException {
        return getEventsFromTable(Constants.VIDEO_TRACKING_EVENTS_KEY, j, j2);
    }

    /* access modifiers changed from: 0000 */
    public synchronized List<JSONObject> getIdentifys(long j, long j2) throws JSONException {
        return getEventsFromTable("identifys", j, j2);
    }

    /* JADX WARNING: type inference failed for: r14v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: type inference failed for: r16v0 */
    /* JADX WARNING: type inference failed for: r16v1 */
    /* JADX WARNING: type inference failed for: r1v5, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r16v2 */
    /* JADX WARNING: type inference failed for: r0v9 */
    /* JADX WARNING: type inference failed for: r0v10, types: [java.lang.String] */
    /* JADX WARNING: type inference failed for: r0v11 */
    /* access modifiers changed from: protected */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 5 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public synchronized java.util.List<org.json.JSONObject> getEventsFromTable(java.lang.String r18, long r19, long r21) throws org.json.JSONException {
        /*
            r17 = this;
            r0 = r19
            r2 = r21
            monitor-enter(r17)
            java.util.LinkedList r11 = new java.util.LinkedList     // Catch:{ all -> 0x00db }
            r11.<init>()     // Catch:{ all -> 0x00db }
            r12 = 0
            r13 = 1
            r14 = 0
            android.database.sqlite.SQLiteDatabase r4 = r17.getReadableDatabase()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r5 = "id"
            java.lang.String r6 = "event"
            java.lang.String[] r5 = new java.lang.String[]{r5, r6}     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r6 = 0
            int r8 = (r0 > r6 ? 1 : (r0 == r6 ? 0 : -1))
            if (r8 < 0) goto L_0x0031
            java.lang.StringBuilder r8 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r8.<init>()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r9 = "id <= "
            r8.append(r9)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r8.append(r0)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r0 = r8.toString()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            goto L_0x0032
        L_0x0031:
            r0 = r14
        L_0x0032:
            r8 = 0
            r9 = 0
            r10 = 0
            java.lang.String r15 = "id ASC"
            int r1 = (r2 > r6 ? 1 : (r2 == r6 ? 0 : -1))
            if (r1 < 0) goto L_0x004f
            java.lang.StringBuilder r1 = new java.lang.StringBuilder     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r1.<init>()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r6 = ""
            r1.append(r6)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r1.append(r2)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r1 = r1.toString()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r16 = r1
            goto L_0x0051
        L_0x004f:
            r16 = r14
        L_0x0051:
            r1 = r17
            r2 = r4
            r3 = r18
            r4 = r5
            r5 = r0
            r6 = r8
            r7 = r9
            r8 = r10
            r9 = r15
            r10 = r16
            android.database.Cursor r14 = r1.queryDb(r2, r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
        L_0x0062:
            boolean r0 = r14.moveToNext()     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            if (r0 == 0) goto L_0x0085
            long r0 = r14.getLong(r12)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r2 = r14.getString(r13)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            boolean r3 = com.amplitude.api.Utils.isEmptyString(r2)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            if (r3 == 0) goto L_0x0077
            goto L_0x0062
        L_0x0077:
            org.json.JSONObject r3 = new org.json.JSONObject     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r3.<init>(r2)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            java.lang.String r2 = "event_id"
            r3.put(r2, r0)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            r11.add(r3)     // Catch:{ SQLiteException -> 0x00b5, StackOverflowError -> 0x009a, RuntimeException -> 0x0090 }
            goto L_0x0062
        L_0x0085:
            if (r14 == 0) goto L_0x008a
            r14.close()     // Catch:{ all -> 0x00db }
        L_0x008a:
            r17.close()     // Catch:{ all -> 0x00db }
            goto L_0x00d0
        L_0x008e:
            r0 = move-exception
            goto L_0x00d2
        L_0x0090:
            r0 = move-exception
            convertIfCursorWindowException(r0)     // Catch:{ all -> 0x008e }
            if (r14 == 0) goto L_0x008a
            r14.close()     // Catch:{ all -> 0x00db }
            goto L_0x008a
        L_0x009a:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r3 = "removeEvent from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[r13]     // Catch:{ all -> 0x008e }
            r4[r12] = r18     // Catch:{ all -> 0x008e }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x008e }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x008e }
            r17.delete()     // Catch:{ all -> 0x008e }
            if (r14 == 0) goto L_0x008a
            r14.close()     // Catch:{ all -> 0x00db }
            goto L_0x008a
        L_0x00b5:
            r0 = move-exception
            com.amplitude.api.AmplitudeLog r1 = logger     // Catch:{ all -> 0x008e }
            java.lang.String r2 = "com.amplitude.api.DatabaseHelper"
            java.lang.String r3 = "getEvents from %s failed"
            java.lang.Object[] r4 = new java.lang.Object[r13]     // Catch:{ all -> 0x008e }
            r4[r12] = r18     // Catch:{ all -> 0x008e }
            java.lang.String r3 = java.lang.String.format(r3, r4)     // Catch:{ all -> 0x008e }
            r1.e(r2, r3, r0)     // Catch:{ all -> 0x008e }
            r17.delete()     // Catch:{ all -> 0x008e }
            if (r14 == 0) goto L_0x008a
            r14.close()     // Catch:{ all -> 0x00db }
            goto L_0x008a
        L_0x00d0:
            monitor-exit(r17)
            return r11
        L_0x00d2:
            if (r14 == 0) goto L_0x00d7
            r14.close()     // Catch:{ all -> 0x00db }
        L_0x00d7:
            r17.close()     // Catch:{ all -> 0x00db }
            throw r0     // Catch:{ all -> 0x00db }
        L_0x00db:
            r0 = move-exception
            monitor-exit(r17)
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amplitude.api.DatabaseHelper.getEventsFromTable(java.lang.String, long, long):java.util.List");
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getEventCount() {
        return getEventCountFromTable(Constants.VIDEO_TRACKING_EVENTS_KEY);
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getIdentifyCount() {
        return getEventCountFromTable("identifys");
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getTotalEventCount() {
        return getEventCount() + getIdentifyCount();
    }

    private synchronized long getEventCountFromTable(String str) {
        long j;
        j = 0;
        SQLiteStatement sQLiteStatement = null;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT COUNT(*) FROM ");
            sb.append(str);
            SQLiteStatement compileStatement = readableDatabase.compileStatement(sb.toString());
            j = compileStatement.simpleQueryForLong();
            if (compileStatement != null) {
                compileStatement.close();
            }
        } catch (SQLiteException e) {
            logger.e("com.amplitude.api.DatabaseHelper", String.format("getNumberRows for %s failed", new Object[]{str}), e);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (StackOverflowError e2) {
            try {
                logger.e("com.amplitude.api.DatabaseHelper", String.format("getNumberRows for %s failed", new Object[]{str}), e2);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        close();
        return j;
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getNthEventId(long j) {
        return getNthEventIdFromTable(Constants.VIDEO_TRACKING_EVENTS_KEY, j);
    }

    /* access modifiers changed from: 0000 */
    public synchronized long getNthIdentifyId(long j) {
        return getNthEventIdFromTable("identifys", j);
    }

    private synchronized long getNthEventIdFromTable(String str, long j) {
        long j2;
        SQLiteStatement sQLiteStatement = null;
        j2 = -1;
        try {
            SQLiteDatabase readableDatabase = getReadableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("SELECT id FROM ");
            sb.append(str);
            sb.append(" LIMIT 1 OFFSET ");
            sb.append(j - 1);
            sQLiteStatement = readableDatabase.compileStatement(sb.toString());
            try {
                j2 = sQLiteStatement.simpleQueryForLong();
            } catch (SQLiteDoneException e) {
                logger.w("com.amplitude.api.DatabaseHelper", (Throwable) e);
            }
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (SQLiteException e2) {
            logger.e("com.amplitude.api.DatabaseHelper", String.format("getNthEventId from %s failed", new Object[]{str}), e2);
            delete();
            if (sQLiteStatement != null) {
                sQLiteStatement.close();
            }
        } catch (StackOverflowError e3) {
            try {
                logger.e("com.amplitude.api.DatabaseHelper", String.format("getNthEventId from %s failed", new Object[]{str}), e3);
                delete();
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
            } catch (Throwable th) {
                if (sQLiteStatement != null) {
                    sQLiteStatement.close();
                }
                close();
                throw th;
            }
        }
        close();
        return j2;
    }

    /* access modifiers changed from: 0000 */
    public synchronized void removeEvents(long j) {
        removeEventsFromTable(Constants.VIDEO_TRACKING_EVENTS_KEY, j);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void removeIdentifys(long j) {
        removeEventsFromTable("identifys", j);
    }

    private synchronized void removeEventsFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("id <= ");
            sb.append(j);
            writableDatabase.delete(str, sb.toString(), null);
        } catch (SQLiteException e) {
            logger.e("com.amplitude.api.DatabaseHelper", String.format("removeEvents from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.e("com.amplitude.api.DatabaseHelper", String.format("removeEvents from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    /* access modifiers changed from: 0000 */
    public synchronized void removeEvent(long j) {
        removeEventFromTable(Constants.VIDEO_TRACKING_EVENTS_KEY, j);
    }

    /* access modifiers changed from: 0000 */
    public synchronized void removeIdentify(long j) {
        removeEventFromTable("identifys", j);
    }

    private synchronized void removeEventFromTable(String str, long j) {
        try {
            SQLiteDatabase writableDatabase = getWritableDatabase();
            StringBuilder sb = new StringBuilder();
            sb.append("id = ");
            sb.append(j);
            writableDatabase.delete(str, sb.toString(), null);
        } catch (SQLiteException e) {
            logger.e("com.amplitude.api.DatabaseHelper", String.format("removeEvent from %s failed", new Object[]{str}), e);
            delete();
        } catch (StackOverflowError e2) {
            try {
                logger.e("com.amplitude.api.DatabaseHelper", String.format("removeEvent from %s failed", new Object[]{str}), e2);
                delete();
            } catch (Throwable th) {
                close();
                throw th;
            }
        }
        close();
    }

    private void delete() {
        try {
            close();
            this.file.delete();
        } catch (SecurityException e) {
            logger.e("com.amplitude.api.DatabaseHelper", "delete failed", e);
        }
    }

    /* access modifiers changed from: 0000 */
    public Cursor queryDb(SQLiteDatabase sQLiteDatabase, String str, String[] strArr, String str2, String[] strArr2, String str3, String str4, String str5, String str6) {
        return sQLiteDatabase.query(str, strArr, str2, strArr2, str3, str4, str5, str6);
    }

    private static void convertIfCursorWindowException(RuntimeException runtimeException) {
        String message = runtimeException.getMessage();
        if (Utils.isEmptyString(message) || !message.startsWith("Cursor window allocation of")) {
            throw runtimeException;
        }
        throw new CursorWindowAllocationException(message);
    }
}
