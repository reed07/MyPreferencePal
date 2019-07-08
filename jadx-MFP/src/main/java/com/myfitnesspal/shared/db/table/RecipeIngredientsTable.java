package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class RecipeIngredientsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_RECIPE_FOOD_ID = "recipe_ingredients_recipe_food_id_index";
    private static final String IDX_RECIPE_FOOD_UID = "recipe_ingredients_recipe_food_uid_index";
    public static final String TABLE_NAME = "recipe_ingredients";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String FRACTION = "fraction";
        public static final String ID = "id";
        public static final String INGREDIENT_FOOD_ID = "ingredient_food_id";
        public static final String MASTER_ID = "master_id";
        public static final String POSITION = "position";
        public static final String QUANTITY = "quantity";
        public static final String RECIPE_FOOD_ID = "recipe_food_id";
        public static final String RECIPE_FOOD_UID = "recipe_food_uid";
        public static final String WEIGHT_INDEX = "weight_index";
    }

    @Inject
    public RecipeIngredientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "recipe_food_id integer not null", "position integer not null", "ingredient_food_id integer not null", "quantity float not null", "weight_index integer not null", "fraction integer not null");
        createIndex(IDX_RECIPE_FOOD_ID, "recipe_food_id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            addColumn(Columns.RECIPE_FOOD_UID, "text");
            createIndex(IDX_RECIPE_FOOD_UID, Columns.RECIPE_FOOD_UID);
        }
    }
}
