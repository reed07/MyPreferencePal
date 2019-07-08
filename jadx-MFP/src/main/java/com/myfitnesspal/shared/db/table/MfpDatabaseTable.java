package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.DatabaseTable;

public interface MfpDatabaseTable extends DatabaseTable {

    public static class Columns {
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
    }
}
