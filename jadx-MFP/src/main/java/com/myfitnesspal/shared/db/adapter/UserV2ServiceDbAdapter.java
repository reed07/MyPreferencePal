package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.UsersTableV1;
import com.myfitnesspal.shared.db.table.UsersTableV2;
import com.myfitnesspal.shared.model.v2.UserV2;
import com.myfitnesspal.shared.service.session.Session;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Tuple;
import com.uacf.core.util.Tuple1;

public class UserV2ServiceDbAdapter {
    private SQLiteDatabaseWrapper db;
    private Session session;
    private UsersTableV1 usersTableV1 = new UsersTableV1(this.db);
    private UsersTableV2 usersTableV2 = new UsersTableV2(this.db);

    public UserV2ServiceDbAdapter(Context context, DbConnectionManager dbConnectionManager, Session session2) {
        this.db = DbConnectionManager.getDb(context);
        this.session = session2;
    }

    public boolean setUser(UserV2 userV2) {
        Tuple1 create = Tuple.create(Boolean.valueOf(false));
        DatabaseUtil.ensureDatabaseTransaction(this.db, new Function0(create, userV2) {
            private final /* synthetic */ Tuple1 f$1;
            private final /* synthetic */ UserV2 f$2;

            {
                this.f$1 = r2;
                this.f$2 = r3;
            }

            public final void execute() {
                UserV2ServiceDbAdapter.lambda$setUser$0(UserV2ServiceDbAdapter.this, this.f$1, this.f$2);
            }
        });
        boolean booleanValue = ((Boolean) create.getItem1()).booleanValue();
        if (booleanValue) {
            this.usersTableV1.updateUserFromV2(this.session, userV2);
        }
        return booleanValue;
    }

    public static /* synthetic */ void lambda$setUser$0(UserV2ServiceDbAdapter userV2ServiceDbAdapter, Tuple1 tuple1, UserV2 userV2) throws RuntimeException {
        userV2ServiceDbAdapter.usersTableV2.reset();
        tuple1.setItem1(Boolean.valueOf(userV2ServiceDbAdapter.usersTableV2.setUser(userV2)));
    }

    public UserV2 getUser() {
        return this.usersTableV2.getUser();
    }

    public void deleteAllUsers() {
        this.usersTableV2.deleteData();
    }
}
