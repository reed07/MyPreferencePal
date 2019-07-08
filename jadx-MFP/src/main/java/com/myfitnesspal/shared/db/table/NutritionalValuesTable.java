package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class NutritionalValuesTable extends MfpDatabaseTableImpl {
    public static final String TABLE_NAME = "nutritional_values";

    public static final class Columns {
        public static final String FOOD_ID = "food_id";
        public static final String NUTRIENT_ID = "nutrient_id";
        public static final String VALUE = "value";
    }

    public void onCreate() {
    }

    @Inject
    public NutritionalValuesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "nutritional_values");
    }

    private void createTable() {
        createTable("food_id INTEGER", "nutrient_id INTEGER", "value FLOAT", "PRIMARY KEY (food_id, nutrient_id)");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(33, i, i2)) {
            createTable();
        }
    }
}
