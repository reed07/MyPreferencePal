package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class ProfileImagesTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_FULL_IMAGE_URL = "images_full_image_url_index";
    private static final String IDX_MASTER_ID = "images_master_id_index";
    private static final String IDX_SYNC = "images_sync_index";
    private static final String IDX_SYNC_V2 = "images_sync_v2_index";
    private static final String IDX_THUMBNAIL_IMAGE_URL = "images_thumbnail_image_url_index";
    private static final String LEGACY_TABLE_NAME = "images";
    public static final String TABLE_NAME = "profile_images";

    public static final class Columns {
        public static final String CREATED_AT = "created_at";
        public static final String CREATOR_UID = "creator_uid";
        public static final String FILENAME = "filename";
        public static final String FILE_TYPE = "file_type";
        public static final String FULLSIZE_IMAGE_DATA = "fullsize_image_data";
        public static final String FULL_IMAGE_URL = "full_image_url";
        public static final String HEIGHT = "height";
        public static final String ID = "id";
        public static final String IMAGE_TYPE = "image_type";
        public static final String IS_VISIBLE = "is_visible";
        public static final String MASTER_ID = "master_id";
        public static final String POSITION = "position";
        public static final String SECONDARY_ID = "secondary_id";
        public static final String SECONDARY_MASTER_ID = "secondary_master_id";
        public static final String THUMBNAIL_IMAGE_DATA = "thumbnail_image_data";
        public static final String THUMBNAIL_IMAGE_URL = "thumbnail_image_url";
        public static final String UID = "uid";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_ID = "user_id";
        public static final String WIDTH = "width";
    }

    @Inject
    public ProfileImagesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer", "image_type integer not null", "secondary_id integer", "secondary_master_id integer", "is_visible integer not null", "position integer not null", "width integer", "height integer", "file_type text", "filename text", "thumbnail_image_url text", "full_image_url text", "created_at text", "updated_at text", "thumbnail_image_data blob", "fullsize_image_data blob");
        createIndex(IDX_MASTER_ID, "master_id");
        createIndex(IDX_SYNC, "user_id", "master_id", "id");
        createIndex(IDX_THUMBNAIL_IMAGE_URL, Columns.THUMBNAIL_IMAGE_URL);
        createIndex(IDX_FULL_IMAGE_URL, Columns.FULL_IMAGE_URL);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(35, i, i2) && i != 1) {
            renameTable("images", TABLE_NAME);
        }
        if (shouldRunUpgrade(25, i, i2)) {
            addColumn("uid", "text");
            addColumn(Columns.CREATOR_UID, "text");
            createIndex(IDX_SYNC_V2, Columns.CREATOR_UID, "uid", "id");
        }
    }
}
