package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class GlobalApplicationPreferencesTable_Factory implements Factory<GlobalApplicationPreferencesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public GlobalApplicationPreferencesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public GlobalApplicationPreferencesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static GlobalApplicationPreferencesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new GlobalApplicationPreferencesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static GlobalApplicationPreferencesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new GlobalApplicationPreferencesTable_Factory(provider);
    }

    public static GlobalApplicationPreferencesTable newGlobalApplicationPreferencesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new GlobalApplicationPreferencesTable(sQLiteDatabaseWrapper);
    }
}
