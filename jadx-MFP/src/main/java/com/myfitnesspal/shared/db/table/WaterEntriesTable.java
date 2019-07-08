package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class WaterEntriesTable extends MfpDatabaseTableV2Impl {
    private static final int ADD_MILLILITERS_COLUMN_VERSION = 41;
    private static final String IDX_SYNC = "water_entries_sync_index";
    private static final String IDX_SYNC_FLAGS = "water_entries_sync_flags_index";
    private static final String IDX_SYNC_V2 = "water_entries_sync_v2_index";
    private static final String IDX_USER_ID_ENTRY_DATE = "water_entries_user_id_entry_date_index";
    public static final String TABLE_NAME = "water_entries";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String CUPS = "cups";
        public static final String ENTRY_DATE = "entry_date";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String MILLILITERS = "milliliters";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public WaterEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "cups integer not null");
        createIndex(IDX_USER_ID_ENTRY_DATE, "user_id", "entry_date");
        createUniqueIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createUniqueIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
        if (shouldRunUpgrade(41, i, i2)) {
            addColumn("milliliters", "real not null default 0");
        }
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
    }
}
