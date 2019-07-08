package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UsersTableV2_Factory implements Factory<UsersTableV2> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public UsersTableV2_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public UsersTableV2 get() {
        return provideInstance(this.databaseProvider);
    }

    public static UsersTableV2 provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new UsersTableV2((SQLiteDatabaseWrapper) provider.get());
    }

    public static UsersTableV2_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new UsersTableV2_Factory(provider);
    }

    public static UsersTableV2 newUsersTableV2(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new UsersTableV2(sQLiteDatabaseWrapper);
    }
}
