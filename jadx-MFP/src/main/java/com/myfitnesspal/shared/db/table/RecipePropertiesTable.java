package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class RecipePropertiesTable extends MfpDatabaseTableImpl {
    private static final String IDX_RECIPE_FOOD_ID = "recipe_properties_recipe_food_id_index";
    public static final String TABLE_NAME = "recipe_properties";

    public static final class Columns {
        public static final String ID = "id";
        public static final String POSITION = "position";
        public static final String PROPERTY_TYPE = "property_type";
        public static final String PROPERTY_VALUE = "property_value";
        public static final String RECIPE_FOOD_ID = "recipe_food_id";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public RecipePropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "recipe_food_id integer not null", "position integer not null", "property_type text not null", "property_value text not null");
        createIndex(IDX_RECIPE_FOOD_ID, "recipe_food_id", Columns.PROPERTY_TYPE, "position");
    }
}
