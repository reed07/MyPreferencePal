package io.uacf.inbox.internal.database;

import android.content.ContentValues;
import com.myfitnesspal.shared.constants.Constants.Analytics.Attributes;
import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.SQLiteDatabaseWrapper;

public class NotificationTable extends DatabaseTableImpl {

    public static final class Columns {
    }

    public static final class SyncFlags {
    }

    public NotificationTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "notification");
    }

    public void onCreate() {
        createTable("_id INTEGER PRIMARY KEY AUTOINCREMENT", "user_id TEXT NOT NULL", "engagement_id TEXT NOT NULL", "collapse_key TEXT", "created_at INTEGER NOT NULL DEFAULT 0", "deleted INTEGER NOT NULL DEFAULT 0", "state TEXT NOT NULL", "json STRING NOT NULL", "sync_flags INTEGER NOT NULL DEFAULT 0", "expires_at INTEGER NOT NULL DEFAULT 0", "marked_as_expired INTEGER NOT NULL DEFAULT 0", "priority INTEGER NOT NULL DEFAULT 0", "category TEXT");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(2, i, i2)) {
            addColumn("expires_at", "INTEGER NOT NULL DEFAULT 0");
            addColumn("marked_as_expired", "INTEGER NOT NULL DEFAULT 0");
            addColumn(com.myfitnesspal.shared.db.table.InstalledDatasetsTable.Columns.PRIORITY, "INTEGER NOT NULL DEFAULT 0");
        }
        if (shouldRunUpgrade(3, i, i2)) {
            addColumn(Attributes.CATEGORY, "TEXT");
            ContentValues contentValues = new ContentValues();
            contentValues.put(Attributes.CATEGORY, "io.uacf.fs.legacy");
            updateData(contentValues, (String) null, new Object[0]);
        }
    }
}
