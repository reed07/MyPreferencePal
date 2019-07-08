package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class MealIngredientsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_MEAL_FOOD_ID = "meal_ingredients_meal_food_id_index";
    private static final String IDX_SYNC = "meal_ingredients_sync_index";
    private static final String IDX_SYNC_V2 = "meal_ingredients_sync_v2_index";
    private static final String TABLE_NAME = "meal_ingredients";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        private static final String FRACTION = "fraction";
        private static final String ID = "id";
        private static final String INGREDIENT_FOOD_ID = "ingredient_food_id";
        private static final String MASTER_ID = "master_id";
        private static final String MEAL_FOOD_ID = "meal_food_id";
        private static final String QUANTITY = "quantity";
        private static final String USER_ID = "user_id";
        private static final String WEIGHT_INDEX = "weight_index";
    }

    @Inject
    public MealIngredientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer", "meal_food_id integer not null", "ingredient_food_id integer not null", "quantity float not null", "weight_index integer not null", "fraction integer not null");
        createIndex(IDX_MEAL_FOOD_ID, com.myfitnesspal.shared.db.table.FoodEntriesTable.Columns.MEAL_FOOD_ID);
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
    }
}
