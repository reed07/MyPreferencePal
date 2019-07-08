package com.myfitnesspal.shared.db.table;

import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Strings;

public abstract class MfpDatabaseTableV2Impl extends MfpDatabaseTableImpl implements MfpDatabaseTableV2 {
    protected MfpDatabaseTableV2Impl(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, String str) {
        super(sQLiteDatabaseWrapper, str);
    }

    /* access modifiers changed from: protected */
    public boolean addUidColumnIfNecessary(int i, int i2, String str) {
        return addColumn(25, i, i2, "uid", "text", str);
    }

    /* access modifiers changed from: protected */
    public boolean addOriginalUidColumnIfNecessary(int i, int i2, String str) {
        return addColumn(25, i, i2, "original_uid", "text", str);
    }

    /* access modifiers changed from: protected */
    public boolean addSyncFlagsColumnIfNecessary(int i, int i2, String str) {
        return addColumn(35, i, i2, Columns.SYNC_FLAGS, "INTEGER NOT NULL DEFAULT 0", str);
    }

    private boolean addColumn(int i, int i2, int i3, String str, String str2, String str3) {
        if (!shouldRunUpgrade(i, i2, i3)) {
            return false;
        }
        addColumn(str, str2);
        if (Strings.notEmpty(str3)) {
            createIndex(str3, str);
        }
        return true;
    }
}
