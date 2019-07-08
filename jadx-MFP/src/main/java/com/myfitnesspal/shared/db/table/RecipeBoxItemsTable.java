package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class RecipeBoxItemsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_FOOD_DESCRIPTION_SORT = "recipe_box_items_food_description_sort_index";
    private static final String IDX_FOOD_ID = "recipe_box_items_food_id_index";
    private static final String IDX_FOOD_UID = "recipe_box_items_food_uid_index";
    private static final String IDX_ORIGINAL_FOOD_MASTER_ID = "recipe_box_items_original_food_master_id_index";
    private static final String IDX_ORIGINAL_FOOD_UID = "recipe_box_items_original_food_uid_index";
    private static final String IDX_SYNC = "recipe_box_items_sync_index";
    private static final String IDX_SYNC_FLAGS = "recipe_box_items_sync_flags";
    private static final String IDX_SYNC_V2 = "recipe_box_items_sync_v2_index";
    private static final String IDX_TIMESTAMP_SORT = "recipe_box_items_timestamp_sort_index";
    public static final String TABLE_NAME = "recipe_box_items";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String FOOD_DESCRIPTION = "food_description";
        public static final String FOOD_ID = "food_id";
        public static final String FOOD_UID = "food_uid";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String ORIGINAL_FOOD_ID = "original_food_id";
        public static final String ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
        public static final String ORIGINAL_FOOD_UID = "original_food_uid";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public RecipeBoxItemsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "user_id integer not null", "master_id integer unique", "food_id integer not null", "original_food_id integer", "original_food_master_id integer", "food_description text not null");
        createIndex(IDX_TIMESTAMP_SORT, "user_id", "id");
        createIndex(IDX_FOOD_ID, "user_id", "food_id");
        createIndex(IDX_FOOD_DESCRIPTION_SORT, "user_id", "food_description");
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
        createIndex(IDX_ORIGINAL_FOOD_MASTER_ID, "original_food_master_id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(25, i, i2)) {
            addColumn("food_uid", "text");
            addColumn("original_food_uid", "text");
            createIndex(IDX_FOOD_UID, "user_id", "food_uid");
            createIndex(IDX_ORIGINAL_FOOD_UID, "original_food_uid");
        }
    }
}
