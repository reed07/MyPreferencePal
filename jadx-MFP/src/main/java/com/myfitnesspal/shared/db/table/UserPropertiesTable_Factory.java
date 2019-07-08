package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserPropertiesTable_Factory implements Factory<UserPropertiesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public UserPropertiesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public UserPropertiesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static UserPropertiesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new UserPropertiesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static UserPropertiesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new UserPropertiesTable_Factory(provider);
    }

    public static UserPropertiesTable newUserPropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new UserPropertiesTable(sQLiteDatabaseWrapper);
    }
}
