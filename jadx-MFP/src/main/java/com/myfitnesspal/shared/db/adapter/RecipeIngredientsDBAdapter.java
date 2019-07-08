package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v1.RecipeIngredient;
import java.util.ArrayList;
import java.util.Iterator;
import javax.annotation.Nonnull;

public class RecipeIngredientsDBAdapter {
    private final Context context;
    private final DbConnectionManager dbConnectionManager;
    SQLiteStatement stmt;
    int updatesExecuted;

    public RecipeIngredientsDBAdapter(@Nonnull Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public void eraseRecipeIngredientsForRecipeFoodId(long j) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(73);
            this.stmt.bindLong(1, j);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
        } catch (SQLiteException e) {
            e.printStackTrace();
        }
    }

    public void insertRecipeIngredients(ArrayList<RecipeIngredient> arrayList) {
        Iterator it = arrayList.iterator();
        int i = 1;
        while (it.hasNext()) {
            RecipeIngredient recipeIngredient = (RecipeIngredient) it.next();
            if (recipeIngredient.hasMasterDatabaseId()) {
                this.stmt = DbConnectionManager.preparedStatement(74);
                this.stmt.bindLong(1, recipeIngredient.masterDatabaseId);
                this.stmt.execute();
                this.stmt.clearBindings();
            }
            this.stmt = DbConnectionManager.preparedStatement(75);
            if (recipeIngredient.hasMasterDatabaseId()) {
                this.stmt.bindLong(1, recipeIngredient.masterDatabaseId);
            } else {
                this.stmt.bindNull(1);
            }
            this.stmt.bindLong(2, recipeIngredient.getRecipeFoodId());
            int i2 = i + 1;
            this.stmt.bindLong(3, (long) i);
            this.stmt.bindLong(4, recipeIngredient.getIngredientFoodId());
            this.stmt.bindDouble(5, (double) recipeIngredient.getQuantity());
            this.stmt.bindLong(6, (long) recipeIngredient.getWeightIndex());
            this.stmt.bindLong(7, recipeIngredient.isFraction() ? 1 : 0);
            this.stmt.execute();
            this.stmt.clearBindings();
            i = i2;
        }
    }

    /* JADX INFO: finally extract failed */
    public ArrayList<RecipeIngredient> recipeIngredientsForRecipeFood(RecipeFood recipeFood) {
        ArrayList<RecipeIngredient> arrayList = new ArrayList<>(10);
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(89), new String[]{String.valueOf(recipeFood.localId)});
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    RecipeIngredient recipeIngredient = new RecipeIngredient();
                    recipeIngredient.setRecipeFoodId(recipeFood.localId);
                    recipeIngredient.setLocalId(rawQuery.getLong(0));
                    recipeIngredient.setMasterDatabaseId(rawQuery.getLong(1));
                    recipeIngredient.setIngredientFoodId(rawQuery.getLong(2));
                    recipeIngredient.setQuantity(rawQuery.getFloat(3));
                    recipeIngredient.setWeightIndex(rawQuery.getInt(4));
                    recipeIngredient.setIsFraction(rawQuery.getInt(5) != 0);
                    arrayList.add(recipeIngredient);
                } while (rawQuery.moveToNext());
            }
            rawQuery.close();
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                RecipeIngredient recipeIngredient2 = (RecipeIngredient) it.next();
                Food fetchFoodById = this.dbConnectionManager.foodDbAdapter().fetchFoodById(recipeIngredient2.getIngredientFoodId());
                recipeIngredient2.setFood(fetchFoodById);
                recipeIngredient2.setFoodPortion(fetchFoodById.foodPortionWithIndex(recipeIngredient2.getWeightIndex()));
            }
            return arrayList;
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
    }
}
