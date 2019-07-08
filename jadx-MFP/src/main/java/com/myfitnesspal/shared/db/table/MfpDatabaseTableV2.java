package com.myfitnesspal.shared.db.table;

public interface MfpDatabaseTableV2 extends MfpDatabaseTable {

    public static class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTable.Columns {
        public static final String ORIGINAL_UID = "original_uid";
        public static final String SYNC_FLAGS = "sync_flags";
        public static final String UID = "uid";
    }
}
