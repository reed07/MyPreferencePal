package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class StepsTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_SYNC = "steps_sync_index";
    private static final String TABLE_NAME = "steps";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String CALORIES = "calories";
        public static final String CLIENT_ID = "client_id";
        public static final String DEVICE_ID = "device_id";
        public static final String ENTRY_DATE = "entry_date";
        public static final String EXERCISE_ENTRY_MASTER_ID = "exercise_entry_master_id";
        public static final String EXERCISE_ENTRY_UID = "exercise_entry_uid";
        public static final String ID = "id";
        public static final String IS_PRIMARY_STEP_SOURCE = "is_primary_step_source";
        public static final String MASTER_ID = "master_id";
        public static final String STEPS = "steps";
        public static final String STEP_GOAL = "step_goal";
        public static final String USER_ID = "user_id";
    }

    public void onCreate() {
    }

    @Inject
    public StepsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "steps");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(22, i, i2)) {
            createTable("id integer primary key autoincrement", "master_id integer not null default 0", "user_id integer not null default 0", "entry_date text not null", "exercise_entry_master_id integer default 0", "steps integer not null default 0", "calories integer not null default 0", "client_id text not null", "device_id text", "step_goal integer default 0", "is_primary_step_source integer not null default 0");
            createUniqueIndex(IDX_SYNC, "user_id", "id");
        }
        if (addUidColumnIfNecessary(i, i2, null)) {
            addColumn(Columns.EXERCISE_ENTRY_UID, "text");
        }
    }
}
