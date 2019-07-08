package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.FoodPortionsTable;
import com.myfitnesspal.shared.db.table.FoodPortionsTable.Columns;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.ArrayUtil;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FoodPortionsDBAdapter {
    private static final String[] REQUIRED_COLUMNS = {"food_id", "weight_index", "amount", "description", Columns.GRAM_WEIGHT, Columns.IS_FRACTION, Columns.NUTRITION_MULTIPLIER};
    private final Context context;

    public FoodPortionsDBAdapter(Context context2) {
        this.context = context2;
    }

    public void insertFoodPortions(FoodPortion[] foodPortionArr, long j) {
        if (ArrayUtil.size(foodPortionArr) != 0) {
            for (FoodPortion insertFoodPortionWithOnConflict : foodPortionArr) {
                insertFoodPortionWithOnConflict(insertFoodPortionWithOnConflict, j, 2);
            }
        }
    }

    public FoodPortion[] getFoodPortions(long j) {
        Cursor cursor = null;
        try {
            int i = 0;
            cursor = DbConnectionManager.getDb(this.context).query(FoodPortionsTable.TABLE_NAME, REQUIRED_COLUMNS, "food_id=?", new String[]{Long.toString(j)}, null, null, null);
            CursorMapper cursorMapper = new CursorMapper(cursor);
            int count = cursorMapper.getCount();
            if (count == 0) {
                return new FoodPortion[0];
            }
            FoodPortion[] foodPortionArr = new FoodPortion[count];
            while (cursorMapper.moveToNext()) {
                int i2 = i + 1;
                foodPortionArr[i] = new FoodPortion(cursorMapper);
                i = i2;
            }
            if (cursor != null) {
                cursor.close();
            }
            return foodPortionArr;
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public Map<Long, List<FoodPortion>> getFoodIdToFoodPortionsMapForMultipleIds(List<Long> list) {
        Cursor cursor = null;
        try {
            SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
            String str = FoodPortionsTable.TABLE_NAME;
            String[] strArr = REQUIRED_COLUMNS;
            StringBuilder sb = new StringBuilder();
            sb.append("food_id IN ");
            sb.append(DatabaseUtil.getArgsForList(list));
            Cursor query = db.query(str, strArr, sb.toString(), null, null, null, null);
            HashMap hashMap = new HashMap();
            CursorMapper cursorMapper = new CursorMapper(query);
            while (cursorMapper.moveToNext()) {
                long j = cursorMapper.getLong("food_id");
                FoodPortion foodPortion = new FoodPortion(cursorMapper);
                List list2 = (List) hashMap.get(Long.valueOf(j));
                if (list2 == null) {
                    list2 = new ArrayList();
                    hashMap.put(Long.valueOf(j), list2);
                }
                list2.add(foodPortion);
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

    public void insertOrReplaceFoodPortions(FoodPortion[] foodPortionArr, long j) {
        for (FoodPortion insertFoodPortionWithOnConflict : foodPortionArr) {
            insertFoodPortionWithOnConflict(insertFoodPortionWithOnConflict, j, 5);
        }
    }

    public void insertDefaultFoodPortion(long j) {
        insertFoodPortionWithOnConflict(FoodPortion.getDefaultFoodPortion(), j, 2);
    }

    private void insertFoodPortionWithOnConflict(FoodPortion foodPortion, long j, int i) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_id", Long.valueOf(j));
        contentValues.put("weight_index", Integer.valueOf(foodPortion.getWeightIndex()));
        contentValues.put("amount", Float.valueOf(foodPortion.getAmount()));
        contentValues.put("description", foodPortion.getDescription());
        contentValues.put(Columns.GRAM_WEIGHT, Float.valueOf(foodPortion.getGramWeight()));
        contentValues.put(Columns.IS_FRACTION, Integer.valueOf(foodPortion.getIsFraction() ? 1 : 0));
        contentValues.put(Columns.NUTRITION_MULTIPLIER, foodPortion.getNutritionMultiplier());
        DbConnectionManager.getDb(this.context).insertWithOnConflict(FoodPortionsTable.TABLE_NAME, null, contentValues, i);
    }
}
