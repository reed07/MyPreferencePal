package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeletedItemsTable_Factory implements Factory<DeletedItemsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public DeletedItemsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public DeletedItemsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static DeletedItemsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new DeletedItemsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static DeletedItemsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new DeletedItemsTable_Factory(provider);
    }

    public static DeletedItemsTable newDeletedItemsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new DeletedItemsTable(sQLiteDatabaseWrapper);
    }
}
