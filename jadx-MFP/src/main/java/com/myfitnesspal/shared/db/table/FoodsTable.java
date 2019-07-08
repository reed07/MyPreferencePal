package com.myfitnesspal.shared.db.table;

import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.Food;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class FoodsTable extends MfpDatabaseTableV2Impl {
    private static final int ADD_FOOD_INFO_VERSION_COLUMN_VERSION = 31;
    private static final String IDX_MASTER_ID = "foods_master_id_index";
    private static final String IDX_ORIGINAL_FOOD_ID = "foods_original_food_id_index";
    private static final String IDX_ORIGINAL_FOOD_MASTER_ID = "foods_original_food_master_id_index";
    private static final String IDX_ORIGINAL_UID = "foods_original_uid_index";
    private static final String IDX_SYNC = "foods_sync_index";
    private static final String IDX_SYNC_FLAGS = "foods_sync_flags_index";
    private static final String IDX_SYNC_V2 = "foods_syncv2_index";
    private static final String IDX_UID = "foods_uid_index";
    private static final String IDX_VERIFIED = "foods_verified_index";
    public static final String TABLE_NAME = "foods";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String BRAND = "brand";
        public static final String DELETED = "deleted";
        public static final String DESCRIPTION = "description";
        public static final String DESTROYED = "destroyed";
        public static final String FOOD_BARCODE = "food_barcode";
        public static final String FOOD_GRAMS = "food_grams";
        public static final String FOOD_INFO = "food_info";
        public static final String FOOD_INFO_VERSION = "food_info_version";
        public static final String FOOD_TYPE = "food_type";
        public static final String ID = "id";
        public static final String IS_PUBLIC = "is_public";
        public static final String MASTER_ID = "master_id";
        public static final String ORIGINAL_FOOD_ID = "original_food_id";
        public static final String ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
        public static final String OWNER_USER_ID = "owner_user_id";
        public static final String OWNER_USER_MASTER_ID = "owner_user_master_id";
        public static final String PROMOTED_FROM_MASTER_ID = "promoted_from_master_id";
        public static final String PROMOTED_FROM_UID = "promoted_from_uid";
        public static final String VERIFIED = "food_verified";
    }

    @Inject
    public FoodsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "foods");
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "original_food_id integer", "original_food_master_id integer", "owner_user_id integer", "owner_user_master_id integer", "food_type integer not null", "deleted integer not null default 0", "destroyed integer not null default 0", "is_public integer not null default 0", "description text not null collate nocase", "brand text collate nocase", "food_info blob not null");
        createIndex(IDX_MASTER_ID, "master_id");
        createIndex(IDX_SYNC, "owner_user_id", "deleted", "master_id", "id");
        createIndex(IDX_ORIGINAL_FOOD_ID, "original_food_id");
        createIndex(IDX_ORIGINAL_FOOD_MASTER_ID, "original_food_master_id");
        execSQL("CREATE VIRTUAL TABLE foods_fts USING FTS3(description, tokenize=simple)", new Object[0]);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(21, i, i2)) {
            addColumn("food_barcode", "text");
        }
        if (addUidColumnIfNecessary(i, i2, IDX_UID)) {
            createIndex(IDX_SYNC_V2, "owner_user_id", "deleted", "uid", "id");
        }
        addOriginalUidColumnIfNecessary(i, i2, IDX_ORIGINAL_UID);
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(29, i, i2)) {
            addColumn("food_verified", "integer not null default 0");
            createIndex(IDX_VERIFIED, "food_verified");
        }
        if (shouldRunUpgrade(31, i, i2)) {
            StringBuilder sb = new StringBuilder();
            sb.append("integer not null default ");
            sb.append(Food.FOOD_INFO_V1);
            addColumn("food_info_version", sb.toString());
        }
        if (shouldRunUpgrade(33, i, i2)) {
            addColumn("food_grams", "float");
        }
        if (shouldRunUpgrade(44, i, i2)) {
            addColumn(Columns.PROMOTED_FROM_MASTER_ID, "integer");
            addColumn(Columns.PROMOTED_FROM_UID, "text");
        }
    }

    public void deleteFood(long j, boolean z) {
        SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(6);
        preparedStatement.bindLong(1, z ? 1 : 0);
        preparedStatement.bindLong(2, j);
        preparedStatement.execute();
        preparedStatement.clearBindings();
    }
}
