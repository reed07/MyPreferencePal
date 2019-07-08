package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class UsersTableV1 extends MfpDatabaseTableImpl {
    public static final String TABLE_NAME = "users";

    public static final class Columns {
        public static final String ANDROID_ID_TRANSMITTED = "android_id_transmitted";
        public static final String ID = "id";
        public static final String LAST_SYNC_AT = "last_sync_at";
        public static final String MASTER_ID = "master_id";
        public static final String PASSWORD = "password";
        public static final String PINCODE = "pincode";
        public static final String THIRD_PARTY_AUTH_TOKEN = "third_party_auth_token";
        public static final String THIRD_PARTY_SERVICE_ID = "third_party_service_id";
        public static final String THIRD_PARTY_USER_ID = "third_party_user_id";
        public static final String USERNAME = "username";
    }

    @Inject
    public UsersTableV1(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "username text not null", "password text", "last_sync_at text");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(14, i, i2)) {
            addColumn(Columns.PINCODE, "text");
            addColumn(Columns.ANDROID_ID_TRANSMITTED, "integer default 0");
        }
        if (shouldRunUpgrade(18, i, i2)) {
            addColumn("third_party_service_id", "integer default 0");
            addColumn("third_party_user_id", "text");
            addColumn("third_party_auth_token", "text");
        }
        if (shouldRunUpgrade(28, i, i2)) {
            DatabaseUtil.ensureDatabaseTransaction(this.database, new Function0() {
                public void execute() {
                    UsersTableV1.this.renameTable("tmp_users");
                    UsersTableV1.this.createTable("id integer primary key autoincrement", "master_id integer unique", "username text not null", "last_sync_at text", "pincode text", "android_id_transmitted integer default 0", "third_party_service_id integer default 0", "third_party_user_id text", "third_party_auth_token text");
                    String join = Strings.join(", ", (T[]) new String[]{"id", "master_id", "username", "last_sync_at", Columns.PINCODE, Columns.ANDROID_ID_TRANSMITTED, "third_party_service_id", "third_party_user_id", "third_party_auth_token"});
                    UsersTableV1.this.insertDataFromOtherTable(join, join, "tmp_users");
                    UsersTableV1.this.dropTable("tmp_users");
                }
            });
        }
    }

    public void updateUserFromV2(Session session, UserV2 userV2) {
        long readLongAndClose = CursorUtils.readLongAndClose(rawQuery("SELECT users.master_id FROM users, user_properties WHERE users.id=user_properties.user_id AND user_properties.property_name=\"email\" AND user_properties.property_value=?;", userV2.getEmail()), -1);
        if (readLongAndClose > 0) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("username", userV2.getUsername());
            updateData(contentValues, "master_id=?", (Object[]) new String[]{String.valueOf(readLongAndClose)});
            session.getUser().getUserV1().setUsername(userV2.getUsername());
        }
    }
}
