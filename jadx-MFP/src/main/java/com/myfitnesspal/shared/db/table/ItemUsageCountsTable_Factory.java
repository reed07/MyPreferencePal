package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ItemUsageCountsTable_Factory implements Factory<ItemUsageCountsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public ItemUsageCountsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public ItemUsageCountsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static ItemUsageCountsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new ItemUsageCountsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static ItemUsageCountsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new ItemUsageCountsTable_Factory(provider);
    }

    public static ItemUsageCountsTable newItemUsageCountsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new ItemUsageCountsTable(sQLiteDatabaseWrapper);
    }
}
