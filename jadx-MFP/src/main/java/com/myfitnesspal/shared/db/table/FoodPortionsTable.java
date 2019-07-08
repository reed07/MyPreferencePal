package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class FoodPortionsTable extends MfpDatabaseTableImpl {
    public static final String TABLE_NAME = "food_portions";

    public static final class Columns {
        public static final String AMOUNT = "amount";
        public static final String DESCRIPTION = "description";
        public static final String FOOD_ID = "food_id";
        public static final String GRAM_WEIGHT = "gram_weight";
        public static final String IS_FRACTION = "is_fraction";
        public static final String NUTRITION_MULTIPLIER = "nutrition_multiplier";
        public static final String WEIGHT_INDEX = "weight_index";
    }

    public void onCreate() {
    }

    @Inject
    public FoodPortionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    private void createTable() {
        createTable("food_id INTEGER", "weight_index INTEGER", "amount FLOAT", "description TEXT COLLATE NOCASE", "gram_weight FLOAT", "nutrition_multiplier DOUBLE", "is_fraction INTEGER", "PRIMARY KEY (food_id, weight_index)");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(33, i, i2)) {
            createTable();
        }
    }
}
