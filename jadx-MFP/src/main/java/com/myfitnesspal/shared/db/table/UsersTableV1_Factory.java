package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UsersTableV1_Factory implements Factory<UsersTableV1> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public UsersTableV1_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public UsersTableV1 get() {
        return provideInstance(this.databaseProvider);
    }

    public static UsersTableV1 provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new UsersTableV1((SQLiteDatabaseWrapper) provider.get());
    }

    public static UsersTableV1_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new UsersTableV1_Factory(provider);
    }

    public static UsersTableV1 newUsersTableV1(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new UsersTableV1(sQLiteDatabaseWrapper);
    }
}
