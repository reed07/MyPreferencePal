package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class UserApplicationSettingsTable_Factory implements Factory<UserApplicationSettingsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public UserApplicationSettingsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public UserApplicationSettingsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static UserApplicationSettingsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new UserApplicationSettingsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static UserApplicationSettingsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new UserApplicationSettingsTable_Factory(provider);
    }

    public static UserApplicationSettingsTable newUserApplicationSettingsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new UserApplicationSettingsTable(sQLiteDatabaseWrapper);
    }
}
