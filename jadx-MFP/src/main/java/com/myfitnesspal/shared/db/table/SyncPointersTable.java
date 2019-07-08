package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class SyncPointersTable extends MfpDatabaseTableImpl {
    private static final String IDX_USER_ID = "last_sync_pointers_user_id_index";
    public static final String TABLE_NAME = "last_sync_pointers";

    public static final class Columns {
        public static final String CUTOFF_ID = "cutoff_id";
        public static final String ITEM_TYPE_NAME = "item_type_name";
        public static final String LAST_SYNC_POINTER = "last_sync_pointer";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public SyncPointersTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("user_id integer not null", "item_type_name text not null", "last_sync_pointer text not null");
        createIndex(IDX_USER_ID, "user_id");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(4, i, i2)) {
            addColumn(Columns.CUTOFF_ID, "integer not null default '0'");
        }
        if (shouldRunUpgrade(7, i, i2)) {
            deleteData("item_type_name = 'user_property'");
        }
        if (shouldRunUpgrade(12, i, i2)) {
            deleteData("item_type_name = 'user_image'");
        }
        if (shouldRunUpgrade(22, i, i2)) {
            deleteData("item_type_name = 'steps_entry'");
        }
    }
}
