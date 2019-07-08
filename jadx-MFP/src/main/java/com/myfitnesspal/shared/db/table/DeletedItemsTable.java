package com.myfitnesspal.shared.db.table;

import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Ln;
import com.uacf.core.util.Strings;
import java.util.Date;
import javax.inject.Inject;

public class DeletedItemsTable extends MfpDatabaseTableImpl {
    private static final String IDX_SYNC = "deleted_items_sync_index";
    private static final String TABLE_NAME = "deleted_items";

    public static final class Columns {
        public static final String DELETED_AT = "deleted_at";
        public static final String ID = "id";
        public static final String IS_DESTROYED = "is_destroyed";
        public static final String ITEM_MASTER_ID = "item_master_id";
        public static final String ITEM_TYPE = "item_type";
        public static final String ITEM_UID = "item_uid";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public DeletedItemsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "user_id integer not null", "item_type integer not null", "item_master_id integer not null", "is_destroyed integer not null default 0", "deleted_at text not null");
        createUniqueIndex(IDX_SYNC, "user_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(25, i, i2)) {
            addColumn(Columns.ITEM_UID, "text");
        }
    }

    public void recordDeletedItemForUserId(long j, int i, long j2, String str, boolean z) {
        try {
            SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(20);
            preparedStatement.bindLong(1, j);
            preparedStatement.bindLong(2, (long) i);
            preparedStatement.bindLong(3, j2);
            if (Strings.notEmpty(str)) {
                preparedStatement.bindString(4, str);
            } else {
                preparedStatement.bindNull(4);
            }
            preparedStatement.bindLong(5, z ? 1 : 0);
            preparedStatement.bindString(6, Database.encodeDateAndTime(new Date()));
            preparedStatement.execute();
        } catch (Exception e) {
            Ln.e(e, "DeletedItemsTable.recordDeletedItemForUserId", new Object[0]);
        }
    }
}
