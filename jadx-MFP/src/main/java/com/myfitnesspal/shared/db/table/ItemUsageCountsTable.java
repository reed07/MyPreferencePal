package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class ItemUsageCountsTable extends MfpDatabaseTableImpl {
    private static final String IDX_MAIN = "item_usage_counts_main_index";
    private static final String IDX_OVERALL_COUNT = "item_usage_counts_overall_count_index";
    private static final String IDX_RECENTLY_USED = "item_usage_counts_recently_used_index";
    private static final String IDX_USAGE_COUNT = "item_usage_counts_usage_count_index";
    private static final String TABLE_NAME = "item_usage_counts";

    public static final class Columns {
        public static final String ITEM_ID = "item_id";
        public static final String ITEM_TYPE = "item_type";
        public static final String LAST_USED_AT = "last_used_at";
        public static final String SUBCATEGORY_ID = "subcategory_id";
        public static final String USAGE_COUNT = "usage_count";
        public static final String USER_ID = "user_id";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public ItemUsageCountsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("user_id integer not null", "item_type integer not null", "item_id integer not null", "subcategory_id integer", "usage_count integer not null", "last_used_at text default null");
        createIndex(IDX_MAIN, "user_id", "item_type", Columns.SUBCATEGORY_ID, "item_id");
        createIndex(IDX_RECENTLY_USED, "user_id", "item_type", Columns.SUBCATEGORY_ID, Columns.LAST_USED_AT);
        createIndex(IDX_OVERALL_COUNT, "item_type", "item_id");
        createIndex(IDX_USAGE_COUNT, "user_id", "item_type", Columns.SUBCATEGORY_ID, Columns.USAGE_COUNT);
    }
}
