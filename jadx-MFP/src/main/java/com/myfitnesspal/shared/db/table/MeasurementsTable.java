package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class MeasurementsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_MAIN_UNIQUE = "measurements_main_unique_index";
    private static final String IDX_SYNC = "measurements_sync_index";
    private static final String IDX_SYNC_FLAGS = "measurements_sync_flags_index";
    private static final String IDX_SYNC_V2 = "measurements_sync_v2_index";
    private static final String IDX_UID = "measurements_uid_index";
    public static final String TABLE_NAME = "measurements";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String ENTRY_DATE = "entry_date";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String MEASUREMENT_TYPE_ID = "measurement_type_id";
        public static final String SOURCE_CLIENT_ID = "source_client_id";
        public static final String USER_ID = "user_id";
        public static final String VALUE = "value";
    }

    @Inject
    public MeasurementsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "measurement_type_id integer not null", "value real not null", "entry_date text not null");
        createUniqueIndex(IDX_MAIN_UNIQUE, "user_id", Columns.MEASUREMENT_TYPE_ID, "entry_date");
        createUniqueIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createUniqueIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
        if (shouldRunUpgrade(46, i, i2)) {
            createIndex(IDX_UID, "uid");
        }
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(26, i, i2)) {
            addColumn(Columns.SOURCE_CLIENT_ID, "text");
        }
    }
}
