package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class FoodEntriesTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_FOOD_ID = "food_entries_food_id_index";
    private static final String IDX_MEAL_FOOD_ID = "food_entries_meal_food_id_index";
    private static final String IDX_OVERALL_USAGE_COUNT = "food_entries_overall_usage_count_index";
    private static final String IDX_RECENTLY_USED = "food_entries_recently_used_index";
    private static final String IDX_SYNC = "food_entries_sync_index";
    private static final String IDX_SYNC_FLAGS = "food_entries_sync_flags_index";
    private static final String IDX_SYNC_V2 = "food_entries_syncv2_index";
    private static final String IDX_UID = "food_entries_uid_index";
    private static final String IDX_USAGE_COUNT = "food_entries_usage_count_index";
    private static final String IDX_USER_ID_ENTRY_DATE = "food_entries_user_id_entry_date_index";
    public static final String TABLE_NAME = "food_entries";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String ENTRY_DATE = "entry_date";
        public static final String ENTRY_TIME = "entry_time";
        public static final String FOOD_ID = "food_id";
        public static final String FRACTION = "fraction";
        public static final String ID = "id";
        public static final String LOGGED_AT = "logged_at";
        public static final String MASTER_ID = "master_id";
        public static final String MEAL_FOOD_ID = "meal_food_id";
        public static final String MEAL_ID = "meal_id";
        public static final String ORIGINAL_FOOD_ID = "original_food_id";
        public static final String QUANTITY = "quantity";
        public static final String USER_ID = "user_id";
        public static final String WEIGHT_INDEX = "weight_index";
    }

    @Inject
    public FoodEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "food_id integer not null", "original_food_id integer not null", "meal_id integer not null", "quantity real not null", "weight_index integer not null", "fraction integer not null");
        createIndex(IDX_USER_ID_ENTRY_DATE, "user_id", "entry_date");
        createUniqueIndex(IDX_SYNC, "user_id", "master_id", "id");
        createUniqueIndex(IDX_RECENTLY_USED, "user_id", "meal_id", "id");
        createIndex(IDX_USAGE_COUNT, "user_id", "meal_id", "original_food_id");
        createIndex(IDX_OVERALL_USAGE_COUNT, "user_id", "original_food_id");
        createIndex(IDX_FOOD_ID, "food_id");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(11, i, i2)) {
            updateData("master_id", "4294967296 + master_id", "master_id < 0");
        }
        if (addUidColumnIfNecessary(i, i2, IDX_UID)) {
            createUniqueIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(43, i, i2)) {
            addColumn(Columns.MEAL_FOOD_ID, " INTEGER DEFAULT 0");
            createIndex(IDX_MEAL_FOOD_ID, Columns.MEAL_FOOD_ID);
        }
        if (shouldRunUpgrade(48, i, i2)) {
            addColumn(Columns.ENTRY_TIME, "text");
            addColumn(Columns.LOGGED_AT, "text");
        }
    }
}
