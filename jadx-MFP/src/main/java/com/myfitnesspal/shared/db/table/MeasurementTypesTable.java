package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class MeasurementTypesTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_MASTER_ID = "measurement_types_master_id_index";
    private static final String IDX_UID = "measurement_types_uid_index";
    private static final String IDX_USER_ID = "measurement_types_user_id_index";
    public static final String TABLE_NAME = "measurement_types";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String DESCRIPTION = "description";
        public static final String ID = "id";
        public static final String LAST_SYNC_AT = "last_sync_at";
        public static final String MASTER_ID = "master_id";
        public static final String POSITION = "position";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public MeasurementTypesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer", "user_id integer not null", "position integer not null", "description text not null", "updated_at text", "last_sync_at text");
        createIndex(IDX_USER_ID, "user_id", "position");
        createIndex(IDX_MASTER_ID, "master_id", "position");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_UID, "uid", "position");
        }
    }
}
