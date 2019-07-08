package com.myfitnesspal.feature.externalsync.impl.googlefit.db;

import android.content.ContentValues;
import android.database.Cursor;
import android.util.Log;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.myfitnesspal.feature.externalsync.impl.googlefit.model.GoogleFitNutritionEntry;
import com.myfitnesspal.feature.externalsync.impl.googlefit.util.GoogleFitDateTimeUtils;
import com.samsung.android.sdk.healthdata.HealthConstants.SessionMeasurement;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

class GoogleFitNutritionTable extends DatabaseTableImpl {
    static final String TABLE_NAME = "fit_nutrition";
    private static final String TAG = "GoogleFitNutritionTable";

    enum Columns {
        ID("_id"),
        USER_ID("user_id"),
        ENTRY_DATE("entry_date"),
        START_TIME("start_time"),
        END_TIME(SessionMeasurement.END_TIME),
        MEAL_ID("meal_id"),
        NUTRIENTS("nutrients"),
        STATUS_FLAG(com.myfitnesspal.shared.db.table.RemindersTable.Columns.STATUS_FLAG),
        SYNC_FLAG(com.myfitnesspal.shared.db.table.UserApplicationSettingsTable.Columns.SYNC_FLAG);
        
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
        public int getColumnIntValue(Cursor cursor) {
            return cursor.getInt(cursor.getColumnIndexOrThrow(getColumnName()));
        }

        /* access modifiers changed from: 0000 */
        public long getColumnLongValue(Cursor cursor) {
            return cursor.getLong(cursor.getColumnIndexOrThrow(getColumnName()));
        }
    }

    GoogleFitNutritionTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
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
        sb3.append(Columns.ENTRY_DATE.getColumnName());
        sb3.append(" integer not null");
        StringBuilder sb4 = new StringBuilder();
        sb4.append(Columns.START_TIME.getColumnName());
        sb4.append(" integer not null");
        StringBuilder sb5 = new StringBuilder();
        sb5.append(Columns.END_TIME.getColumnName());
        sb5.append(" integer not null");
        StringBuilder sb6 = new StringBuilder();
        sb6.append(Columns.MEAL_ID.getColumnName());
        sb6.append(" integer");
        StringBuilder sb7 = new StringBuilder();
        sb7.append(Columns.NUTRIENTS.getColumnName());
        sb7.append(" text not null");
        StringBuilder sb8 = new StringBuilder();
        sb8.append(Columns.STATUS_FLAG.getColumnName());
        sb8.append(" integer not null");
        StringBuilder sb9 = new StringBuilder();
        sb9.append(Columns.SYNC_FLAG.getColumnName());
        sb9.append(" integer not null");
        createTable(sb.toString(), sb2.toString(), sb3.toString(), sb4.toString(), sb5.toString(), sb6.toString(), sb7.toString(), sb8.toString(), sb9.toString());
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(4, i, i2)) {
            Log.d(TAG, "Upgrade table");
            dropTable();
            onCreate();
        }
    }

    /* access modifiers changed from: 0000 */
    public boolean insertOrUpdateFitNutrientEntry(GoogleFitNutritionEntry googleFitNutritionEntry, String str) {
        boolean z = false;
        if (str == null) {
            return false;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.USER_ID.getColumnName(), str);
        contentValues.put(Columns.ENTRY_DATE.getColumnName(), Long.valueOf(GoogleFitDateTimeUtils.getStartOfDay(new Date(googleFitNutritionEntry.getStartTime())).getTime()));
        contentValues.put(Columns.START_TIME.getColumnName(), Long.valueOf(googleFitNutritionEntry.getStartTime()));
        contentValues.put(Columns.END_TIME.getColumnName(), Long.valueOf(googleFitNutritionEntry.getEndTime()));
        contentValues.put(Columns.MEAL_ID.getColumnName(), Integer.valueOf(googleFitNutritionEntry.getMeal()));
        contentValues.put(Columns.NUTRIENTS.getColumnName(), mapFromMapToJsonString(googleFitNutritionEntry.getNutrients()));
        contentValues.put(Columns.STATUS_FLAG.getColumnName(), Integer.valueOf(googleFitNutritionEntry.getStatusFlag()));
        contentValues.put(Columns.SYNC_FLAG.getColumnName(), Integer.valueOf(101010));
        if (insertData(contentValues) > 0) {
            z = true;
        }
        return z;
    }

    /* access modifiers changed from: 0000 */
    public boolean markAsUploading(String str, Object... objArr) {
        ContentValues contentValues = new ContentValues();
        contentValues.put(Columns.SYNC_FLAG.getColumnName(), Integer.valueOf(4161));
        return updateData(contentValues, str, objArr) > 0;
    }

    /* access modifiers changed from: 0000 */
    public TreeMap<Long, List<GoogleFitNutritionEntry>> fetchNutrientEntry(String str, String[] strArr) {
        TreeMap<Long, List<GoogleFitNutritionEntry>> treeMap = new TreeMap<>();
        Cursor rawQuery = super.rawQuery(str, strArr);
        if (rawQuery != null) {
            while (rawQuery.moveToNext()) {
                try {
                    long columnLongValue = Columns.ENTRY_DATE.getColumnLongValue(rawQuery);
                    int columnIntValue = Columns.MEAL_ID.getColumnIntValue(rawQuery);
                    GoogleFitNutritionEntry googleFitNutritionEntry = new GoogleFitNutritionEntry();
                    googleFitNutritionEntry.setEntryTime(columnLongValue);
                    googleFitNutritionEntry.setStartTime(Columns.START_TIME.getColumnLongValue(rawQuery));
                    googleFitNutritionEntry.setEndTime(Columns.END_TIME.getColumnLongValue(rawQuery));
                    googleFitNutritionEntry.setMeal(columnIntValue);
                    googleFitNutritionEntry.setNutrients(mapFromJsonStringToMap(Columns.NUTRIENTS.getColumnStringValue(rawQuery)));
                    googleFitNutritionEntry.setStatusFlag(Columns.STATUS_FLAG.getColumnIntValue(rawQuery));
                    String str2 = TAG;
                    StringBuilder sb = new StringBuilder();
                    sb.append("GoogleFit: Reading food from local table:");
                    sb.append(Strings.toString(googleFitNutritionEntry.getNutrients()));
                    Log.d(str2, sb.toString());
                    if (treeMap.containsKey(Long.valueOf(columnLongValue))) {
                        List list = (List) treeMap.get(Long.valueOf(columnLongValue));
                        if (list != null) {
                            list.add(googleFitNutritionEntry);
                        }
                    } else {
                        ArrayList arrayList = new ArrayList();
                        arrayList.add(googleFitNutritionEntry);
                        treeMap.put(Long.valueOf(columnLongValue), arrayList);
                    }
                } finally {
                    rawQuery.close();
                }
            }
        }
        return treeMap;
    }

    /* access modifiers changed from: 0000 */
    public int deleteUploadedData(String str, Object... objArr) {
        return super.deleteData(str, objArr);
    }

    private String mapFromMapToJsonString(Map<String, Float> map) {
        String str = "";
        try {
            return new Gson().toJson((Object) map);
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return str;
        }
    }

    private Map<String, Float> mapFromJsonStringToMap(String str) {
        HashMap hashMap = new HashMap();
        try {
            return (Map) new Gson().fromJson(str, new TypeToken<Map<String, Float>>() {
            }.getType());
        } catch (Exception e) {
            Log.e(TAG, e.toString());
            return hashMap;
        }
    }
}
