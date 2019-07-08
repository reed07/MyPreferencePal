package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class SyncPointersTable_Factory implements Factory<SyncPointersTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public SyncPointersTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public SyncPointersTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static SyncPointersTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new SyncPointersTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static SyncPointersTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new SyncPointersTable_Factory(provider);
    }

    public static SyncPointersTable newSyncPointersTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new SyncPointersTable(sQLiteDatabaseWrapper);
    }
}
