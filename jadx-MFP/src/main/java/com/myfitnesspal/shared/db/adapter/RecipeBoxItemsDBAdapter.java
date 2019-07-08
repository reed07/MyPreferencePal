package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.feature.recipes.model.MfpPartialRecipe;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.RecipeBoxItemsTable;
import com.myfitnesspal.shared.model.mapper.impl.FoodMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.FoodPortionMapperImpl;
import com.myfitnesspal.shared.model.mapper.impl.MfpFoodMapperImpl;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.RecipeBoxItem;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;

public class RecipeBoxItemsDBAdapter extends SessionDBAdapter {
    private static final int RECIPE_LIMIT = 2000;
    private final Context context;
    private final DbConnectionManager dbConnectionManager;
    SQLiteStatement stmt;

    public RecipeBoxItemsDBAdapter(@Nonnull Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public void insertOrUpdateRecipeBoxItemWithMasterId(long j, RecipeFood recipeFood) {
        long j2;
        long j3 = j;
        RecipeFood recipeFood2 = recipeFood;
        long localId = getSession().getUser().getLocalId();
        int i = (j3 > 0 ? 1 : (j3 == 0 ? 0 : -1));
        if (i > 0) {
            Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(78), new String[]{String.valueOf(localId), String.valueOf(j)});
            try {
                j2 = rawQuery.moveToFirst() ? rawQuery.getLong(0) : 0;
            } finally {
                rawQuery.close();
            }
        } else {
            j2 = 0;
        }
        if (j2 > 0) {
            this.stmt = DbConnectionManager.preparedStatement(79);
            this.stmt.bindLong(1, recipeFood2.localId);
            this.stmt.bindLong(2, recipeFood2.originalId.longValue());
            this.stmt.bindLong(3, recipeFood2.originalMasterId.longValue());
            this.stmt.bindString(4, recipeFood2.description);
            this.stmt.bindLong(5, j2);
            this.stmt.execute();
            this.stmt.clearBindings();
            return;
        }
        this.stmt = DbConnectionManager.preparedStatement(80);
        this.stmt.bindLong(1, localId);
        if (i > 0) {
            this.stmt.bindLong(2, j3);
        } else {
            this.stmt.bindNull(2);
        }
        this.stmt.bindLong(3, recipeFood2.localId);
        this.stmt.bindLong(4, recipeFood2.originalId.longValue());
        this.stmt.bindLong(5, recipeFood2.originalMasterId.longValue());
        this.stmt.bindString(6, recipeFood2.description);
        if (Strings.notEmpty(recipeFood.getUid())) {
            this.stmt.bindString(7, recipeFood.getUid());
        } else {
            this.stmt.bindNull(7);
        }
        if (Strings.notEmpty(recipeFood.getOriginalUid())) {
            this.stmt.bindString(8, recipeFood.getOriginalUid());
        } else {
            this.stmt.bindNull(8);
        }
        this.stmt.execute();
        this.stmt.clearBindings();
    }

    public void updateRecipeBoxItemReferencesForRecipeFood(RecipeFood recipeFood) {
        this.stmt = DbConnectionManager.preparedStatement(81);
        this.stmt.bindLong(1, recipeFood.localId);
        this.stmt.bindString(2, recipeFood.description);
        this.stmt.bindLong(3, recipeFood.originalMasterId.longValue());
        this.stmt.execute();
        this.stmt.clearBindings();
    }

    public void deleteRecipeBoxItemWithMasterId(long j) {
        this.stmt = DbConnectionManager.preparedStatement(84);
        this.stmt.bindLong(1, getSession().getUser().getLocalId());
        this.stmt.bindLong(2, j);
        this.stmt.execute();
        this.stmt.clearBindings();
    }

    public void deleteRecipeBoxItem(RecipeBoxItem recipeBoxItem, boolean z) {
        try {
            if (recipeBoxItem.hasLocalId()) {
                deleteRecipeBoxItemWithLocalId(recipeBoxItem.localId);
            }
            if (recipeBoxItem.hasMasterDatabaseId() && z) {
                this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(getSession().getUser().getLocalId(), 12, recipeBoxItem.masterDatabaseId, true);
            }
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public void deleteRecipeBoxItemWithLocalId(long j) {
        this.stmt = DbConnectionManager.preparedStatement(83);
        this.stmt.bindLong(1, j);
        this.stmt.execute();
        this.stmt.clearBindings();
    }

    public long getRecipeFoodIdForMasterId(long j) {
        return CursorUtils.readLongAndClose(DbConnectionManager.getDb(this.context).query(RecipeBoxItemsTable.TABLE_NAME, new String[]{"food_id"}, "master_id=?", new String[]{Strings.toString(Long.valueOf(j))}, null, null, null), 0);
    }

    public void deleteRecipeBoxItem(RecipeBoxItem recipeBoxItem) {
        long masterDatabaseId = recipeBoxItem.getMasterDatabaseId();
        if (masterDatabaseId > 0) {
            this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(getSession().getUser().getLocalId(), 12, masterDatabaseId, true);
        }
        DbConnectionManager.getDb(this.context).delete(RecipeBoxItemsTable.TABLE_NAME, "id=?", new String[]{Strings.toString(Long.valueOf(recipeBoxItem.getLocalId()))});
    }

    public int fetchCountForRecipeBoxItems() {
        Cursor queryData = new RecipeBoxItemsTable(DbConnectionManager.getDb(this.context)).queryData(new String[]{"COUNT(id)"}, "user_id=?", String.valueOf(((Session) this.session.get()).getUser().getLocalId()));
        if (queryData != null) {
            try {
                if (queryData.moveToFirst()) {
                    return queryData.getInt(0);
                }
                queryData.close();
            } finally {
                queryData.close();
            }
        }
        return 0;
    }

    public ArrayList<RecipeBoxItem> fetchRecipeBoxItemsWithSortOrder(SortOrder sortOrder, int i, int i2) {
        String str;
        switch (sortOrder) {
            case ALPHABETICAL_ASCENDING:
                str = DbConnectionManager.queryString(91);
                break;
            case ALPHABETICAL_DESCENDING:
                str = DbConnectionManager.queryString(92);
                break;
            case DATE_ASCENDING:
                str = DbConnectionManager.queryString(129);
                break;
            case RECENTLY_USED:
            case DATE_DESCENDING:
                str = DbConnectionManager.queryString(93);
                break;
            default:
                return null;
        }
        ArrayList<RecipeBoxItem> arrayList = new ArrayList<>();
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(str, new String[]{String.valueOf(getSession().getUser().getLocalId()), String.valueOf(i), String.valueOf(i2)});
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    RecipeBoxItem recipeBoxItem = new RecipeBoxItem();
                    recipeBoxItem.setLocalId(rawQuery.getLong(0));
                    recipeBoxItem.setMasterDatabaseId(rawQuery.getLong(1));
                    recipeBoxItem.setRecipeFoodId((int) rawQuery.getLong(2));
                    if (rawQuery.getString(3) != null) {
                        recipeBoxItem.setFoodDescription(rawQuery.getString(3));
                    }
                    arrayList.add(recipeBoxItem);
                } while (rawQuery.moveToNext());
            }
            return arrayList;
        } finally {
            rawQuery.close();
        }
    }

    public List<MfpPartialRecipe> getRecipes() {
        ArrayList<RecipeBoxItem> fetchRecipeBoxItemsWithSortOrder = fetchRecipeBoxItemsWithSortOrder(SortOrder.DATE_DESCENDING, 2000, 0);
        if (CollectionUtils.isEmpty((Collection<?>) fetchRecipeBoxItemsWithSortOrder)) {
            return null;
        }
        ArrayList arrayList = new ArrayList();
        for (RecipeBoxItem recipeFoodId : fetchRecipeBoxItemsWithSortOrder) {
            arrayList.add(Long.valueOf(recipeFoodId.getRecipeFoodId()));
        }
        Map foodIdToFoodMapForMultipleIds = this.dbConnectionManager.foodDbAdapter().getFoodIdToFoodMapForMultipleIds(arrayList);
        Map recipePropertiesForFoodIds = this.dbConnectionManager.recipePropertiesDBAdapter().getRecipePropertiesForFoodIds(arrayList);
        FoodMapperImpl foodMapperImpl = new FoodMapperImpl(new FoodPortionMapperImpl());
        MfpFoodMapperImpl mfpFoodMapperImpl = new MfpFoodMapperImpl();
        ArrayList arrayList2 = new ArrayList();
        for (RecipeBoxItem recipeBoxItem : fetchRecipeBoxItemsWithSortOrder) {
            long recipeFoodId2 = recipeBoxItem.getRecipeFoodId();
            Map map = (Map) recipePropertiesForFoodIds.get(Long.valueOf(recipeFoodId2));
            Food food = (Food) foodIdToFoodMapForMultipleIds.get(Long.valueOf(recipeFoodId2));
            if (food != null) {
                MfpFood mfpFood = (MfpFood) mfpFoodMapperImpl.tryMapFrom(foodMapperImpl.tryMapFrom(food));
                MfpPartialRecipe mfpPartialRecipe = new MfpPartialRecipe();
                mfpPartialRecipe.setName(recipeBoxItem.getFoodDescription());
                mfpPartialRecipe.setNutritionalContents(mfpFood.getNutritionalContents());
                mfpPartialRecipe.setMfpFood(mfpFood);
                List list = (List) map.get("servings");
                mfpPartialRecipe.setServings(list != null ? Double.valueOf((String) list.get(0)).doubleValue() : 0.0d);
                mfpPartialRecipe.setRecipeBoxItem(recipeBoxItem);
                arrayList2.add(mfpPartialRecipe);
            }
        }
        return arrayList2;
    }
}
