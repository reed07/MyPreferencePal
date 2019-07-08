package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import javax.annotation.Nonnull;

public class MealIngredientsDBAdapter {
    private static final String DATABASE_TABLE = "meal_ingredients";
    private static final String KEY_FRACTION = "fraction";
    private static final String KEY_ID = "id";
    private static final String KEY_INGREDIENT_FOOD_ID = "ingredient_food_id";
    private static final String KEY_MASTER_ID = "master_id";
    private static final String KEY_MEAL_FOOD_ID = "meal_food_id";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_WEIGHT_INDEX = "weight_index";
    private final Context context;
    private final DbConnectionManager dbConnectionManager;

    public MealIngredientsDBAdapter(@Nonnull Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public void insertMealIngredient(MealIngredient mealIngredient) {
        if (mealIngredient != null) {
            SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(23);
            try {
                if (mealIngredient.hasMasterDatabaseId()) {
                    preparedStatement.bindLong(1, mealIngredient.getMasterDatabaseId());
                } else {
                    preparedStatement.bindNull(1);
                }
                preparedStatement.bindLong(2, mealIngredient.getCreatorUserId());
                preparedStatement.bindLong(3, mealIngredient.getMealFoodId());
                preparedStatement.bindLong(4, mealIngredient.getIngredientFoodId());
                preparedStatement.bindDouble(5, (double) mealIngredient.getQuantity());
                preparedStatement.bindLong(6, (long) mealIngredient.getWeightIndex());
                preparedStatement.bindLong(7, mealIngredient.isFraction() ? 1 : 0);
                long executeInsert = preparedStatement.executeInsert();
                preparedStatement.clearBindings();
                mealIngredient.setLocalId(executeInsert);
            } catch (Exception e) {
                preparedStatement.clearBindings();
                Ln.e(e);
            }
        }
    }

    public void eraseIngredientsForMealFoodId(long j) {
        SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(22);
        try {
            preparedStatement.bindLong(1, j);
            preparedStatement.execute();
            preparedStatement.clearBindings();
        } catch (Exception e) {
            preparedStatement.clearBindings();
            Ln.e(e);
        }
    }

    public List<MealIngredient> mealIngredientsForMealFood(MealFood mealFood) {
        return mealIngredientsForMealFoods(Collections.singletonList(mealFood));
    }

    /* JADX INFO: finally extract failed */
    public List<MealIngredient> mealIngredientsForMealFoods(List<MealFood> list) {
        HashMap hashMap = new HashMap();
        ArrayList arrayList = new ArrayList();
        Cursor cursor = null;
        try {
            String[] strArr = {"id", "master_id", "user_id", "ingredient_food_id", "meal_food_id", "quantity", "weight_index", "fraction"};
            List select = Enumerable.select((Collection<T>) list, (ReturningFunction1<U, T>) new ReturningFunction1<String, MealFood>() {
                public String execute(MealFood mealFood) throws RuntimeException {
                    return Long.toString(mealFood.getLocalId());
                }
            });
            if (CollectionUtils.isEmpty((Collection<?>) select)) {
                return arrayList;
            }
            SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
            String str = DATABASE_TABLE;
            StringBuilder sb = new StringBuilder();
            sb.append("meal_food_id IN ");
            sb.append(DatabaseUtil.getArgsForList(select));
            Cursor query = db.query(str, strArr, sb.toString(), null, null, null, null);
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("master_id");
            int columnIndex3 = query.getColumnIndex("user_id");
            int columnIndex4 = query.getColumnIndex("ingredient_food_id");
            int columnIndex5 = query.getColumnIndex("meal_food_id");
            int columnIndex6 = query.getColumnIndex("quantity");
            int columnIndex7 = query.getColumnIndex("weight_index");
            int columnIndex8 = query.getColumnIndex("fraction");
            if (query.moveToFirst()) {
                int count = query.getCount();
                int i = 0;
                while (i < count) {
                    MealIngredient mealIngredient = new MealIngredient();
                    int i2 = i;
                    mealIngredient.setLocalId(query.getLong(columnIndex));
                    mealIngredient.setMasterDatabaseId(query.getLong(columnIndex2));
                    mealIngredient.setCreatorUserId(query.getLong(columnIndex3));
                    mealIngredient.setMealFoodId(query.getLong(columnIndex5));
                    long j = query.getLong(columnIndex4);
                    mealIngredient.setIngredientFoodId(j);
                    int i3 = columnIndex;
                    mealIngredient.setQuantity(query.getFloat(columnIndex6));
                    mealIngredient.setWeightIndex(query.getInt(columnIndex7));
                    mealIngredient.setIsFraction(query.getInt(columnIndex8) != 0);
                    arrayList.add(mealIngredient);
                    List list2 = (List) hashMap.get(Long.valueOf(j));
                    if (list2 == null) {
                        list2 = new ArrayList();
                        hashMap.put(Long.valueOf(j), list2);
                    }
                    list2.add(mealIngredient);
                    query.moveToNext();
                    i = i2 + 1;
                    columnIndex = i3;
                }
            }
            if (query != null) {
                query.close();
            }
            if (CollectionUtils.notEmpty((Collection<?>) hashMap.keySet())) {
                for (Food food : this.dbConnectionManager.foodDbAdapter().fetchFoodsByIds(new ArrayList(hashMap.keySet()))) {
                    for (MealIngredient mealIngredient2 : (List) hashMap.get(Long.valueOf(food.getLocalId()))) {
                        mealIngredient2.setIngredientFood(food);
                        mealIngredient2.setFoodPortion(food.foodPortionWithIndex(mealIngredient2.getWeightIndex()));
                    }
                }
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }
}
