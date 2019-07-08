package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DeletedMostUsedFoodsTable_Factory implements Factory<DeletedMostUsedFoodsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public DeletedMostUsedFoodsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public DeletedMostUsedFoodsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static DeletedMostUsedFoodsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new DeletedMostUsedFoodsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static DeletedMostUsedFoodsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new DeletedMostUsedFoodsTable_Factory(provider);
    }

    public static DeletedMostUsedFoodsTable newDeletedMostUsedFoodsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new DeletedMostUsedFoodsTable(sQLiteDatabaseWrapper);
    }
}
