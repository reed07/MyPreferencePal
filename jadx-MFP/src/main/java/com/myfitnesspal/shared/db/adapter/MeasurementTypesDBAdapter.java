package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;
import java.util.Date;
import java.util.List;

public class MeasurementTypesDBAdapter extends SessionDBAdapter {
    private static final String DATABASE_TABLE = "measurement_types";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_ID = "id";
    public static final String KEY_LAST_SYNC_AT = "last_sync_at";
    public static final String KEY_MASTER_ID = "master_id";
    public static final String KEY_POSITION = "position";
    public static final String KEY_UID = "uid";
    public static final String KEY_UPDATED_AT = "updated_at";
    public static final String KEY_USER_ID = "user_id";
    private final Context context;

    public MeasurementTypesDBAdapter(Context context2) {
        this.context = context2;
    }

    public void insertMeasurementType(String str, long j, int i) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("user_id", Long.valueOf(j));
            contentValues.put("position", Integer.valueOf(i + 1));
            contentValues.put("description", str);
            DbConnectionManager.getDb(this.context).insert("measurement_types", null, contentValues);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:18:0x005a  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0062  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.feature.progress.model.MeasurementTypeItem> getMeasurementTypesForUserId(long r11) {
        /*
            r10 = this;
            r0 = 0
            java.lang.String r4 = "user_id = ?"
            java.lang.String r8 = "position asc"
            android.content.Context r1 = r10.context     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            com.uacf.core.database.SQLiteDatabaseWrapper r1 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r1)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            java.lang.String r2 = "measurement_types"
            java.lang.String r3 = "id"
            java.lang.String r5 = "description"
            java.lang.String[] r3 = new java.lang.String[]{r3, r5}     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r9 = 1
            java.lang.String[] r5 = new java.lang.String[r9]     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r12 = 0
            r5[r12] = r11     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            r6 = 0
            r7 = 0
            android.database.Cursor r11 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0053, all -> 0x0051 }
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x004f }
            r2 = 10
            r1.<init>(r2)     // Catch:{ Exception -> 0x004f }
            boolean r2 = r11.moveToFirst()     // Catch:{ Exception -> 0x004f }
            if (r2 == 0) goto L_0x0049
        L_0x0032:
            int r2 = r11.getInt(r12)     // Catch:{ Exception -> 0x004f }
            java.lang.String r3 = r11.getString(r9)     // Catch:{ Exception -> 0x004f }
            com.myfitnesspal.feature.progress.model.MeasurementTypeItem r4 = new com.myfitnesspal.feature.progress.model.MeasurementTypeItem     // Catch:{ Exception -> 0x004f }
            long r5 = (long) r2     // Catch:{ Exception -> 0x004f }
            r4.<init>(r5, r3)     // Catch:{ Exception -> 0x004f }
            r1.add(r4)     // Catch:{ Exception -> 0x004f }
            boolean r2 = r11.moveToNext()     // Catch:{ Exception -> 0x004f }
            if (r2 != 0) goto L_0x0032
        L_0x0049:
            if (r11 == 0) goto L_0x004e
            r11.close()
        L_0x004e:
            return r1
        L_0x004f:
            r12 = move-exception
            goto L_0x0055
        L_0x0051:
            r12 = move-exception
            goto L_0x0060
        L_0x0053:
            r12 = move-exception
            r11 = r0
        L_0x0055:
            com.uacf.core.util.Ln.e(r12)     // Catch:{ all -> 0x005e }
            if (r11 == 0) goto L_0x005d
            r11.close()
        L_0x005d:
            return r0
        L_0x005e:
            r12 = move-exception
            r0 = r11
        L_0x0060:
            if (r0 == 0) goto L_0x0065
            r0.close()
        L_0x0065:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter.getMeasurementTypesForUserId(long):java.util.ArrayList");
    }

    /* JADX WARNING: Code restructure failed: missing block: B:14:0x005b, code lost:
        if (r1 == null) goto L_0x0060;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x005d, code lost:
        r1.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0060, code lost:
        return;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x0052, code lost:
        if (r1 != null) goto L_0x005d;
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public void loadMeasurementTypesForUser(com.myfitnesspal.shared.model.v1.UserV1 r8) {
        /*
            r7 = this;
            if (r8 != 0) goto L_0x0003
            return
        L_0x0003:
            r0 = 94
            r1 = 0
            java.lang.String r0 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r0)     // Catch:{ Exception -> 0x0057 }
            r2 = 1
            java.lang.String[] r3 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0057 }
            r4 = 0
            long r5 = r8.getLocalId()     // Catch:{ Exception -> 0x0057 }
            java.lang.String r5 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0057 }
            r3[r4] = r5     // Catch:{ Exception -> 0x0057 }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x0057 }
            r5 = 10
            r4.<init>(r5)     // Catch:{ Exception -> 0x0057 }
            android.content.Context r5 = r7.context     // Catch:{ Exception -> 0x0057 }
            com.uacf.core.database.SQLiteDatabaseWrapper r5 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r5)     // Catch:{ Exception -> 0x0057 }
            android.database.Cursor r1 = r5.rawQuery(r0, r3)     // Catch:{ Exception -> 0x0057 }
            boolean r0 = r1.moveToFirst()     // Catch:{ Exception -> 0x0057 }
            if (r0 == 0) goto L_0x003c
        L_0x002f:
            java.lang.String r0 = r1.getString(r2)     // Catch:{ Exception -> 0x0057 }
            r4.add(r0)     // Catch:{ Exception -> 0x0057 }
            boolean r0 = r1.moveToNext()     // Catch:{ Exception -> 0x0057 }
            if (r0 != 0) goto L_0x002f
        L_0x003c:
            com.myfitnesspal.shared.model.v1.UserProfile r8 = r8.getProfile()     // Catch:{ Exception -> 0x0057 }
            int r0 = r4.size()     // Catch:{ Exception -> 0x0057 }
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0057 }
            java.lang.Object[] r0 = r4.toArray(r0)     // Catch:{ Exception -> 0x0057 }
            java.lang.String[] r0 = (java.lang.String[]) r0     // Catch:{ Exception -> 0x0057 }
            r8.setMeasurementTypes(r0)     // Catch:{ Exception -> 0x0057 }
            r1.close()     // Catch:{ Exception -> 0x0057 }
            if (r1 == 0) goto L_0x0060
            goto L_0x005d
        L_0x0055:
            r8 = move-exception
            goto L_0x0061
        L_0x0057:
            r8 = move-exception
            com.uacf.core.util.Ln.e(r8)     // Catch:{ all -> 0x0055 }
            if (r1 == 0) goto L_0x0060
        L_0x005d:
            r1.close()
        L_0x0060:
            return
        L_0x0061:
            if (r1 == 0) goto L_0x0066
            r1.close()
        L_0x0066:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.MeasurementTypesDBAdapter.loadMeasurementTypesForUser(com.myfitnesspal.shared.model.v1.UserV1):void");
    }

    public long measurementTypeIdFromDescription(String str) {
        long j = 0;
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("measurement_types", new String[]{"id"}, "user_id = ? and description = ?", new String[]{String.valueOf(getSession().getUser().getLocalId()), str}, null, null, null);
            if (query.moveToFirst()) {
                j = query.getLong(0);
            }
            if (query != null) {
                query.close();
            }
            return j;
        } catch (Exception e) {
            Ln.e(e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void updateMeasurementTypeDescriptions(String[] strArr, Long[] lArr, List<String> list, long j) {
        String[] strArr2 = strArr;
        try {
            int length = strArr2.length;
            String encodeDateAndTime = Database.encodeDateAndTime(new Date());
            ContentValues contentValues = new ContentValues();
            contentValues.putNull("master_id");
            char c = 1;
            DbConnectionManager.getDb(this.context).update("measurement_types", contentValues, "user_id = ? ", new String[]{String.valueOf(j)});
            int i = 0;
            while (i < length) {
                String str = strArr2[i];
                long longValue = lArr[i].longValue();
                String str2 = (String) list.get(i);
                String[] strArr3 = new String[2];
                strArr3[0] = String.valueOf(j);
                strArr3[c] = str;
                ContentValues contentValues2 = new ContentValues();
                contentValues2.put("master_id", String.valueOf(longValue));
                contentValues2.put("uid", str2);
                if (DbConnectionManager.getDb(this.context).update("measurement_types", contentValues2, "user_id = ? and description = ?", strArr3) == 0) {
                    ContentValues contentValues3 = new ContentValues();
                    contentValues3.put("master_id", String.valueOf(longValue));
                    contentValues3.put("uid", str2);
                    contentValues3.put("user_id", String.valueOf(j));
                    contentValues3.put("position", String.valueOf(0));
                    contentValues3.put("description", str);
                    DbConnectionManager.getDb(this.context).insert("measurement_types", null, contentValues3);
                }
                String[] strArr4 = {String.valueOf(j), String.valueOf(longValue)};
                ContentValues contentValues4 = new ContentValues();
                i++;
                contentValues4.put("position", String.valueOf(i));
                contentValues4.put("updated_at", encodeDateAndTime);
                contentValues4.put("last_sync_at", encodeDateAndTime);
                DbConnectionManager.getDb(this.context).update("measurement_types", contentValues4, "user_id = ? and master_id = ?", strArr4);
                c = 1;
            }
            String[] strArr5 = {String.valueOf(j)};
            DbConnectionManager.getDb(this.context).delete("measurement_types", "user_id = ? and master_id is null", strArr5);
        } catch (Exception e) {
            Ln.e(e);
        }
    }
}
