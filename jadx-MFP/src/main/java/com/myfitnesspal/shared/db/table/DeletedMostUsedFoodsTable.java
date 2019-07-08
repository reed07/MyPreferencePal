package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class DeletedMostUsedFoodsTable extends MfpDatabaseTableV2Impl {
    public static final String TABLE_NAME = "deleted_most_used_foods";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String MEAL_ID = "meal_id";
        public static final String ORIGINAL_FOOD_ID = "original_food_id";
        public static final String ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public DeletedMostUsedFoodsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "deleted_most_used_foods");
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "original_food_id not null", "original_food_master_id not null", "meal_id integer");
    }

    public void onUpgrade(int i, int i2) {
        addUidColumnIfNecessary(i, i2, null);
        addOriginalUidColumnIfNecessary(i, i2, null);
    }
}
