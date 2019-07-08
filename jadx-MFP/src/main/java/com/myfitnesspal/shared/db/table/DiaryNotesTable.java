package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class DiaryNotesTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_ENTRY_DATE = "diary_notes_entry_date_index";
    private static final String IDX_SYNC = "diary_notes_sync_index";
    private static final String IDX_SYNC_V2 = "diary_notes_sync_v2_index";
    public static final String TABLE_NAME = "diary_notes";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String BODY = "body";
        public static final String ENTRY_DATE = "entry_date";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String NOTE_TYPE = "note_type";
        public static final String USER_ID = "user_id";
    }

    @Inject
    public DiaryNotesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "note_type integer not null", "body text not null");
        createIndex(IDX_ENTRY_DATE, "user_id", "entry_date");
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
    }

    public void onUpgrade(int i, int i2) {
        if (addUidColumnIfNecessary(i, i2, null)) {
            createIndex(IDX_SYNC_V2, "user_id", "uid", "id");
        }
    }
}
