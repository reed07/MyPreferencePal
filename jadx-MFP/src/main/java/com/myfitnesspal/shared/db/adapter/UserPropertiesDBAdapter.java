package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.feature.registration.model.LoginModel;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.UserV1;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Date;

public class UserPropertiesDBAdapter {
    private static final String DATABASE_TABLE = "user_properties";
    public static final String KEY_PROPERTY_NAME = "property_name";
    public static final String KEY_PROPERTY_VALUE = "property_value";
    public static final String KEY_USER_ID = "user_id";
    private final Context context;
    SQLiteStatement stmt;

    public UserPropertiesDBAdapter(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: Removed duplicated region for block: B:24:0x0067  */
    /* JADX WARNING: Removed duplicated region for block: B:28:0x006e  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.String, java.lang.String> fetchUnsyncedUserProperties(long r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            java.util.HashMap r2 = new java.util.HashMap     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r3 = 5
            r2.<init>(r3)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.lang.String r7 = "user_id = ? and (last_sync_at is null or updated_at > last_sync_at)"
            android.content.Context r3 = r12.context     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            com.uacf.core.database.SQLiteDatabaseWrapper r4 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r3)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.lang.String r5 = "user_properties"
            java.lang.String r3 = "property_name"
            java.lang.String r6 = "property_value"
            java.lang.String[] r6 = new java.lang.String[]{r3, r6}     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r3 = 1
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            java.lang.String r13 = java.lang.String.valueOf(r13)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r8[r1] = r13     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            r9 = 0
            r10 = 0
            r11 = 0
            android.database.Cursor r13 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ Exception -> 0x005c, all -> 0x0059 }
            boolean r14 = r13.moveToFirst()     // Catch:{ Exception -> 0x0057 }
            if (r14 == 0) goto L_0x0051
        L_0x0030:
            java.lang.String r14 = r13.getString(r1)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r4 = r13.getString(r3)     // Catch:{ Exception -> 0x0057 }
            java.lang.String r5 = "requires_start_time_for_exercise_entries"
            boolean r5 = r14.equals(r5)     // Catch:{ Exception -> 0x0057 }
            if (r5 == 0) goto L_0x0048
            java.lang.String r14 = "ignoring requires_start_time_for_exercise_entries"
            java.lang.Object[] r4 = new java.lang.Object[r1]     // Catch:{ Exception -> 0x0057 }
            com.uacf.core.util.Ln.w(r14, r4)     // Catch:{ Exception -> 0x0057 }
            goto L_0x004b
        L_0x0048:
            r2.put(r14, r4)     // Catch:{ Exception -> 0x0057 }
        L_0x004b:
            boolean r14 = r13.moveToNext()     // Catch:{ Exception -> 0x0057 }
            if (r14 != 0) goto L_0x0030
        L_0x0051:
            if (r13 == 0) goto L_0x0056
            r13.close()
        L_0x0056:
            return r2
        L_0x0057:
            r14 = move-exception
            goto L_0x005e
        L_0x0059:
            r14 = move-exception
            r13 = r0
            goto L_0x006c
        L_0x005c:
            r14 = move-exception
            r13 = r0
        L_0x005e:
            java.lang.String r2 = "UserPropertiesDBAdapter::fetchUnsyncedUserProperties"
            java.lang.Object[] r1 = new java.lang.Object[r1]     // Catch:{ all -> 0x006b }
            com.uacf.core.util.Ln.e(r14, r2, r1)     // Catch:{ all -> 0x006b }
            if (r13 == 0) goto L_0x006a
            r13.close()
        L_0x006a:
            return r0
        L_0x006b:
            r14 = move-exception
        L_0x006c:
            if (r13 == 0) goto L_0x0071
            r13.close()
        L_0x0071:
            throw r14
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter.fetchUnsyncedUserProperties(long):java.util.Map");
    }

    public void saveUserProperty(String str, String str2, long j) {
        Cursor rawQuery;
        try {
            Boolean valueOf = Boolean.valueOf(false);
            rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(38), new String[]{String.valueOf(j), str});
            String str3 = null;
            if (rawQuery.moveToFirst()) {
                valueOf = Boolean.valueOf(true);
                str3 = rawQuery.getString(0);
            }
            rawQuery.close();
            String encodeDateAndTime = Database.encodeDateAndTime(new Date());
            if (!valueOf.booleanValue()) {
                this.stmt = DbConnectionManager.preparedStatement(40);
                this.stmt.bindLong(1, j);
                this.stmt.bindString(2, str);
                if (str2 != null) {
                    this.stmt.bindString(3, str2);
                } else {
                    this.stmt.bindNull(3);
                }
                this.stmt.bindString(4, encodeDateAndTime);
                this.stmt.executeInsert();
                this.stmt.clearBindings();
            } else if (!Strings.equals(str2, str3)) {
                this.stmt = DbConnectionManager.preparedStatement(39);
                if (str2 != null) {
                    this.stmt.bindString(1, str2);
                } else {
                    this.stmt.bindNull(1);
                }
                this.stmt.bindString(2, encodeDateAndTime);
                this.stmt.bindLong(3, j);
                this.stmt.bindString(4, str);
                this.stmt.execute();
                this.stmt.clearBindings();
            }
        } catch (Exception e) {
            Ln.e(e);
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }

    public Cursor loadUserProperties(long j) {
        String str = "user_id = ?";
        try {
            return DbConnectionManager.getDb(this.context).query(DATABASE_TABLE, new String[]{"property_name", "property_value"}, str, new String[]{String.valueOf(j)}, null, null, null);
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public void saveUserProperties(UserV1 userV1, LoginModel loginModel) {
        try {
            ArrayList arrayList = new ArrayList();
            ArrayList arrayList2 = new ArrayList();
            userV1.writeAllPropertyKeysTo(arrayList, arrayList2);
            int size = arrayList.size();
            for (int i = 0; i < size; i++) {
                String str = (String) arrayList.get(i);
                String str2 = (String) arrayList2.get(i);
                saveUserProperty(str, str2, userV1.getLocalId());
                if ("email".equals(str)) {
                    loginModel.setUsername(str2);
                }
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void updateUserPropertiesLastSyncAtTimestamps(long j) {
        try {
            String encodeDateAndTime = Database.encodeDateAndTime(new Date());
            this.stmt = DbConnectionManager.preparedStatement(41);
            this.stmt.bindString(1, encodeDateAndTime);
            this.stmt.bindLong(2, j);
            this.stmt.execute();
            this.stmt.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004a, code lost:
        if (r0 == null) goto L_0x004d;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004d, code lost:
        return r1;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:5:0x003e, code lost:
        if (r0 != null) goto L_0x0040;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:6:0x0040, code lost:
        r0.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public long fetchUserIdForGivenEmailAddress(java.lang.String r13) {
        /*
            r12 = this;
            r0 = 0
            r1 = 0
            android.content.Context r3 = r12.context     // Catch:{ SQLiteException -> 0x0046 }
            com.uacf.core.database.SQLiteDatabaseWrapper r4 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r3)     // Catch:{ SQLiteException -> 0x0046 }
            java.lang.String r5 = "user_properties"
            java.lang.String r3 = "user_id"
            java.lang.String[] r6 = new java.lang.String[]{r3}     // Catch:{ SQLiteException -> 0x0046 }
            java.lang.String r7 = "property_name = ? and LOWER(property_value) = ?"
            r3 = 2
            java.lang.String[] r8 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0046 }
            r3 = 0
            java.lang.String r9 = "email"
            r8[r3] = r9     // Catch:{ SQLiteException -> 0x0046 }
            r3 = 1
            java.lang.String r13 = com.uacf.core.util.Strings.toString(r13)     // Catch:{ SQLiteException -> 0x0046 }
            java.lang.String r13 = r13.toLowerCase()     // Catch:{ SQLiteException -> 0x0046 }
            r8[r3] = r13     // Catch:{ SQLiteException -> 0x0046 }
            r9 = 0
            r10 = 0
            java.lang.String r11 = "updated_at DESC"
            android.database.Cursor r0 = r4.query(r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0046 }
            boolean r13 = r0.moveToFirst()     // Catch:{ SQLiteException -> 0x0046 }
            if (r13 == 0) goto L_0x003e
            java.lang.String r13 = "user_id"
            int r13 = r0.getColumnIndex(r13)     // Catch:{ SQLiteException -> 0x0046 }
            long r1 = r0.getLong(r13)     // Catch:{ SQLiteException -> 0x0046 }
        L_0x003e:
            if (r0 == 0) goto L_0x004d
        L_0x0040:
            r0.close()
            goto L_0x004d
        L_0x0044:
            r13 = move-exception
            goto L_0x004e
        L_0x0046:
            r13 = move-exception
            com.uacf.core.util.Ln.e(r13)     // Catch:{ all -> 0x0044 }
            if (r0 == 0) goto L_0x004d
            goto L_0x0040
        L_0x004d:
            return r1
        L_0x004e:
            if (r0 == 0) goto L_0x0053
            r0.close()
        L_0x0053:
            throw r13
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.UserPropertiesDBAdapter.fetchUserIdForGivenEmailAddress(java.lang.String):long");
    }
}
