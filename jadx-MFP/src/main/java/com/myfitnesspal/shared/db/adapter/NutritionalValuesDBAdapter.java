package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.NutritionalValuesTable.Columns;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Function0;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class NutritionalValuesDBAdapter extends SessionDBAdapter {
    private static final String[] REQUIRED_COLUMNS = {"food_id", Columns.NUTRIENT_ID, "value"};
    private final Context context;

    public NutritionalValuesDBAdapter(Context context2) {
        this.context = context2;
    }

    public void insertNutritionalValues(NutritionalValues nutritionalValues, long j) {
        insertNutritionValuesWithOnConflict(nutritionalValues, j, 2);
    }

    /* JADX INFO: finally extract failed */
    public NutritionalValues getNutritionalValues(long j) {
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("nutritional_values", REQUIRED_COLUMNS, "food_id=?", new String[]{Long.toString(j)}, null, null, null);
            int columnIndex = query.getColumnIndex(Columns.NUTRIENT_ID);
            int columnIndex2 = query.getColumnIndex("value");
            NutritionalValues nutritionalValues = new NutritionalValues();
            while (query.moveToNext()) {
                nutritionalValues.setNutrientIndex(query.getInt(columnIndex), query.getFloat(columnIndex2));
            }
            if (query != null) {
                query.close();
            }
            return nutritionalValues;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public Map<Long, NutritionalValues> getFoodIdToNutritionalValuesMapForMultipleIds(List<Long> list) {
        Cursor cursor = null;
        try {
            String[] strArr = REQUIRED_COLUMNS;
            StringBuilder sb = new StringBuilder();
            sb.append("food_id IN ");
            sb.append(DatabaseUtil.getArgsForList(list));
            Cursor query = DbConnectionManager.getDb(this.context).query("nutritional_values", strArr, sb.toString(), null, null, null, null);
            HashMap hashMap = new HashMap();
            int columnIndex = query.getColumnIndex("food_id");
            int columnIndex2 = query.getColumnIndex(Columns.NUTRIENT_ID);
            int columnIndex3 = query.getColumnIndex("value");
            while (query.moveToNext()) {
                long j = query.getLong(columnIndex);
                NutritionalValues nutritionalValues = (NutritionalValues) hashMap.get(Long.valueOf(j));
                if (nutritionalValues == null) {
                    nutritionalValues = new NutritionalValues();
                    hashMap.put(Long.valueOf(j), nutritionalValues);
                }
                nutritionalValues.setNutrientIndex(query.getInt(columnIndex2), query.getFloat(columnIndex3));
            }
            if (query != null) {
                query.close();
            }
            return hashMap;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void insertOrReplaceNutritionalValues(NutritionalValues nutritionalValues, long j) {
        insertNutritionValuesWithOnConflict(nutritionalValues, j, 5);
    }

    public int fixInvalidNutritionValues() {
        ContentValues contentValues = new ContentValues();
        contentValues.put("value", Integer.valueOf(-1));
        return DbConnectionManager.getDb(this.context).update("nutritional_values", contentValues, "nutrient_id!=0 AND value< -1", null);
    }

    private void insertNutritionValuesWithOnConflict(NutritionalValues nutritionalValues, long j, int i) {
        SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
        final NutritionalValues nutritionalValues2 = nutritionalValues;
        final long j2 = j;
        final SQLiteDatabaseWrapper sQLiteDatabaseWrapper = db;
        final int i2 = i;
        AnonymousClass1 r1 = new Function0() {
            public void execute() {
                float[] values = nutritionalValues2.getValues();
                for (int i = 0; i < values.length; i++) {
                    ContentValues contentValues = new ContentValues();
                    contentValues.put("food_id", Long.valueOf(j2));
                    contentValues.put(Columns.NUTRIENT_ID, Integer.valueOf(i));
                    contentValues.put("value", Float.valueOf(values[i]));
                    sQLiteDatabaseWrapper.insertWithOnConflict("nutritional_values", null, contentValues, i2);
                }
            }
        };
        DatabaseUtil.ensureDatabaseTransaction(db, r1);
    }
}
