package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.shared.model.v2.MfpImageAssociation;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import java.util.Locale;

public class ImageAssociationsTable extends MfpDatabaseTableImpl {
    private static final int ADD_LOCAL_ID_COLUMNS_VERSION = 35;
    private static final int ADD_MISSING_DATE_COLUMNS_VERSION = 36;
    private static final int ADD_SYNC_FLAGS_COLUMN_VERSION = 35;
    private static final String IMAGE_UID_INDEX = "image_associations_image_uid_index";
    private static final String RESOURCE_TYPE_RESOURCE_UID_INDEX = "resource_type_resource_uid_index";
    private static final String RESOURCE_UID_INDEX = "image_associations_resource_uid_index";
    public static final String TABLE_NAME = "image_associations";
    private static final String UID_INDEX = "image_associations_uid_index";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String CREATED_AT = "created_at";
        public static final String ID = "id";
        public static final String IMAGE_ID = "image_id";
        public static final String IMAGE_UID = "image_uid";
        public static final String RESOURCE_ID = "resource_id";
        public static final String RESOURCE_TYPE = "resource_type";
        public static final String RESOURCE_UID = "resource_uid";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_UID = "user_id";
    }

    public void onCreate() {
    }

    public ImageAssociationsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(35, i, i2)) {
            createTableAndIndex();
        }
        if (shouldRunUpgrade(35, i, i2)) {
            dropTable();
            createTableAndIndex();
        }
        if (shouldRunUpgrade(35, i, i2)) {
            addColumn(com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns.SYNC_FLAGS, String.format(Locale.ENGLISH, " INTEGER NOT NULL DEFAULT %d", new Object[]{Integer.valueOf(2)}));
        }
        if (shouldRunUpgrade(36, i, i2)) {
            addColumn("created_at", "TEXT");
            addColumn("updated_at", "TEXT");
        }
        if (shouldRunUpgrade(46, i, i2)) {
            createIndex(UID_INDEX, "uid");
            createIndex(RESOURCE_UID_INDEX, "resource_uid");
            createIndex(IMAGE_UID_INDEX, "image_uid");
        }
    }

    public static ContentValues getContentValuesForUpdate(MfpImageAssociation mfpImageAssociation) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("uid", mfpImageAssociation.getId());
        contentValues.put("resource_uid", mfpImageAssociation.getResourceId());
        contentValues.put("resource_type", mfpImageAssociation.getResourceType());
        contentValues.put("image_uid", mfpImageAssociation.getImageId());
        contentValues.put("user_id", mfpImageAssociation.getUserId());
        contentValues.put("created_at", mfpImageAssociation.getCreatedAt());
        contentValues.put("updated_at", mfpImageAssociation.getUpdatedAt());
        return contentValues;
    }

    private void createTableAndIndex() {
        createTable("id INTEGER PRIMARY KEY AUTOINCREMENT", "uid TEXT", "image_id INTEGER", "image_uid TEXT", "user_id TEXT NOT NULL", "resource_id INTEGER", "resource_uid TEXT", "resource_type TEXT NOT NULL");
        createIndex(RESOURCE_TYPE_RESOURCE_UID_INDEX, "resource_type", "resource_uid");
    }
}
