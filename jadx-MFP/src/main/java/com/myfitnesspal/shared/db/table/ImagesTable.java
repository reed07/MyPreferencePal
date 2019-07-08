package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.uacf.core.database.SQLiteDatabaseWrapper;

public class ImagesTable extends MfpDatabaseTableImpl {
    private static final int ADD_MISSING_COLUMNS_VERSION = 35;
    private static final int ADD_MISSING_DATE_COLUMNS_VERSION = 36;
    public static final String TABLE_NAME = "images";
    private static final String UID_INDEX = "images_uid_index";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String CHECKSUM = "checksum";
        public static final String CREATED_AT = "created_at";
        public static final String DESCRIPTION = "description";
        public static final String FLAGGED = "flagged";
        public static final String FORMAT = "format";
        public static final String HEIGHT = "height";
        public static final String ID = "id";
        public static final String LAST_UPLOAD_ATTEMPT = "last_upload_attempt";
        public static final String LOCAL_FILEPATH = "local_filepath";
        public static final String REMOTE_STATUS = "remote_status";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_UID = "user_id";
        public static final String WIDTH = "width";
    }

    public void onCreate() {
    }

    public ImagesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(35, i, i2)) {
            createTable();
        }
        if (shouldRunUpgrade(35, i, i2)) {
            addColumn(Columns.CHECKSUM, "TEXT");
            addColumn(Columns.LAST_UPLOAD_ATTEMPT, "INTEGER DEFAULT 0");
        }
        if (shouldRunUpgrade(36, i, i2)) {
            addColumn("created_at", "TEXT");
            addColumn("updated_at", "TEXT");
        }
        if (shouldRunUpgrade(46, i, i2)) {
            createIndex(UID_INDEX, "uid");
        }
    }

    public static ContentValues getContentValuesForUpdate(MfpImage mfpImage, int i) {
        ContentValues contentValuesForUpdate = getContentValuesForUpdate(mfpImage);
        contentValuesForUpdate.put(com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns.SYNC_FLAGS, Integer.valueOf(i));
        return contentValuesForUpdate;
    }

    public static ContentValues getContentValuesForUpdate(MfpImage mfpImage) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", mfpImage.getId());
        contentValues.put("user_id", mfpImage.getUserId());
        contentValues.put(Columns.FORMAT, mfpImage.getFormat());
        contentValues.put("width", Integer.valueOf(mfpImage.getWidth()));
        contentValues.put("height", Integer.valueOf(mfpImage.getHeight()));
        contentValues.put("description", mfpImage.getDescription());
        contentValues.put(Columns.FLAGGED, Boolean.valueOf(mfpImage.isFlagged()));
        contentValues.put(Columns.REMOTE_STATUS, mfpImage.getStatus());
        contentValues.put(Columns.CHECKSUM, mfpImage.getChecksum());
        contentValues.put("created_at", mfpImage.getCreatedAt());
        contentValues.put("updated_at", mfpImage.getUpdatedAt());
        return contentValues;
    }

    private void createTable() {
        createTable("id INTEGER PRIMARY KEY AUTOINCREMENT", "uid TEXT", "user_id TEXT NOT NULL", "format TEXT", "width INTEGER", "height INTEGER", "description TEXT", "flagged TEXT", "remote_status TEXT", "local_filepath TEXT", "sync_flags INTEGER NOT NULL DEFAULT 0");
    }
}
