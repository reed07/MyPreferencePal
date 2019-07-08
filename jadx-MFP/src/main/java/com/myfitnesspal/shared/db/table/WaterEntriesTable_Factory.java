package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class WaterEntriesTable_Factory implements Factory<WaterEntriesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public WaterEntriesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public WaterEntriesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static WaterEntriesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new WaterEntriesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static WaterEntriesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new WaterEntriesTable_Factory(provider);
    }

    public static WaterEntriesTable newWaterEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new WaterEntriesTable(sQLiteDatabaseWrapper);
    }
}
