package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.DatabaseTableImpl;
import com.uacf.core.database.SQLiteDatabaseWrapper;

public abstract class MfpDatabaseTableImpl extends DatabaseTableImpl implements MfpDatabaseTable {
    protected MfpDatabaseTableImpl(SQLiteDatabaseWrapper sQLiteDatabaseWrapper, String str) {
        super(sQLiteDatabaseWrapper, str);
    }
}
