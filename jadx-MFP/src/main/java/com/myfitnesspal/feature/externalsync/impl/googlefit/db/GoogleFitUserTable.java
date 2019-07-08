package com.myfitnesspal.feature.externalsync.impl.googlefit.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.myfitnesspal.feature.externalsync.impl.googlefit.constants.GoogleFitConstants.DateTime.Format;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

class GoogleFitUserTable extends DatabaseTableImpl {
    private static final String DATABASE_DATE_TIME_FORMAT = Format.newIso8601DateTimeFormat().toPattern();
    public static final String TABLE_NAME = "fit_user";
    private static final String TAG = "GoogleFitUserTable";

    enum Columns {
        ID("_id"),
        USER_ID("user_id"),
        ENTRY_TIME_FORMATTED("entry_time_formatted"),
        ENTRY_TIME(com.myfitnesspal.shared.db.table.FoodEntriesTable.Columns.ENTRY_TIME),
        WEIGHT("weight");
        
        private String name;

        private Columns(String str) {
            this.name = str;
        }

        public String getColumnName() {
            return this.name;
        }

        /* access modifiers changed from: 0000 */
        public String getColumnStringValue(Cursor cursor) {
            return cursor.getString(cursor.getColumnIndexOrThrow(getColumnName()));
        }

        /* access modifiers changed from: 0000 */
        public long getColumnLongValue(Cursor cursor) {
            return cursor.getLong(cursor.getColumnIndexOrThrow(getColumnName()));
        }
    }

    GoogleFitUserTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        StringBuilder sb = new StringBuilder();
        sb.append(Columns.ID.getColumnName());
        sb.append(" integer primary key autoincrement");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(Columns.USER_ID.getColumnName());
        sb2.append(" text not null");
        StringBuilder sb3 = new StringBuilder();
        sb3.append(Columns.ENTRY_TIME_FORMATTED.getColumnName());
        sb3.append(" text not null");
        StringBuilder sb4 = new StringBuilder();
        sb4.append(Columns.ENTRY_TIME.getColumnName());
        sb4.append(" integer not null");
        StringBuilder sb5 = new StringBuilder();
        sb5.append(Columns.WEIGHT.getColumnName());
        sb5.append(" text not null");
        createTable(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString());
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(3, i, i2)) {
            Log.d(TAG, "Upgrade table");
            dropTable();
            onCreate();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean insertOrUpdateFitUserWeight(Date date, float f, String str) {
        boolean z = false;
        if (str == null || date == null) {
            return false;
        }
        StringBuilder sb = new StringBuilder();
        sb.append(Columns.ENTRY_TIME_FORMATTED.getColumnName());
        sb.append(" = ? AND ");
        sb.append(Columns.USER_ID.getColumnName());
        sb.append(" = ?");
        String sb2 = sb.toString();
        String format = GoogleFitDateTimeUtils.format(DATABASE_DATE_TIME_FORMAT, date);
        String[] strArr = {format, str};
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.USER_ID.getColumnName(), str);
        contentValues.put(Columns.ENTRY_TIME_FORMATTED.getColumnName(), format);
        contentValues.put(Columns.ENTRY_TIME.getColumnName(), Long.valueOf(date.getTime()));
        contentValues.put(Columns.WEIGHT.getColumnName(), String.valueOf(f));
        if (updateData(contentValues, sb2, (Object[]) strArr) != 0) {
            return true;
        }
        if (insertData(contentValues) > 0) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public Map<Long, Float> getUserWeights(String str, String[] strArr) {
        HashMap hashMap = new HashMap();
        Cursor rawQuery = super.rawQuery(str, strArr);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                try {
                    hashMap.put(Long.valueOf(Columns.ENTRY_TIME.getColumnLongValue(rawQuery)), Float.valueOf(Float.parseFloat(Columns.WEIGHT.getColumnStringValue(rawQuery))));
                } finally {
                    rawQuery.close();
                }
            }
        }
        return hashMap;
    }

    /* access modifiers changed from: 0000 */
    public void reset() {
        deleteData();
    }
}
