package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class UserPropertiesTable extends MfpDatabaseTableImpl {
    private static final String IDX_USER_ID_PROPERTY_NAME = "user_properties_user_id_property_name_index";
    private static final String TABLE_NAME = "user_properties";

    public static final class Columns {
        public static final String ID = "id";
        public static final String LAST_SYNC_AT = "last_sync_at";
        public static final String PROPERTY_NAME = "property_name";
        public static final String PROPERTY_VALUE = "property_value";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_ID = "user_id";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public UserPropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "user_id integer not null", "property_name text not null", "property_value text", "updated_at text", "last_sync_at text");
        createIndex(IDX_USER_ID_PROPERTY_NAME, "user_id", "property_name");
    }
}
