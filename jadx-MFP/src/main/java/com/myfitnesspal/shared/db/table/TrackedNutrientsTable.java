package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class TrackedNutrientsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_SYNC = "tracked_nutrients_sync_index";
    private static final String IDX_SYNC_V2 = "tracked_nutrients_sync_v2_index";
    private static final String IDX_USER_ID = "tracked_nutrients_user_id_index";
    private static final String TABLE_NAME = "tracked_nutrients";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String NUTRIENT_NAME_ID = "nutrient_name_id";
        public static final String POSITION = "position";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public TrackedNutrientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "position integer not null", "nutrient_name_id integer not null");
        createIndex(IDX_USER_ID, "user_id");
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
    }
}
