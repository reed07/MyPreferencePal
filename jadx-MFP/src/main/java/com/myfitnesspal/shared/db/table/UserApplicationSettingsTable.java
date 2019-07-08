package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.feature.userapplicationsettings.response.UserApplicationSetting;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class UserApplicationSettingsTable extends MfpDatabaseTableImpl {
    public static final int FIRST_USER_APPLICATION_SETTINGS_VERSION = 42;
    public static final String TABLE_NAME = "user_application_settings";

    public static final class Columns {
        public static final String ID = "id";
        public static final String NAME = "name";
        public static final String SYNC_FLAG = "sync_flag";
        public static final String TYPE = "type";
        public static final String UPDATED_AT = "updated_at";
        public static final String USER_ID = "user_id";
        public static final String VALUE = "value";
    }

    public void onCreate() {
    }

    @Inject
    public UserApplicationSettingsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "user_application_settings");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(42, i, i2)) {
            createTable("id integer primary key autoincrement", "user_id text not null", "name text unique", "value text not null", "type text", "updated_at text", "sync_flag integer not null default 0");
        }
    }

    public static ContentValues getContentValuesForUpdateOrInsert(UserApplicationSetting userApplicationSetting) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", userApplicationSetting.getName());
        contentValues.put("user_id", userApplicationSetting.getUserId());
        contentValues.put("value", userApplicationSetting.getValue());
        contentValues.put("type", userApplicationSetting.getType());
        contentValues.put("updated_at", userApplicationSetting.getUpdatedAt());
        contentValues.put(Columns.SYNC_FLAG, userApplicationSetting.getSyncFlag());
        return contentValues;
    }

    public static ContentValues getContentValuesForUpdate(String str, String str2, Integer num) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", str);
        contentValues.put("value", str2);
        contentValues.put(Columns.SYNC_FLAG, num);
        return contentValues;
    }
}
