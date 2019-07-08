package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodsTable_Factory implements Factory<FoodsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public FoodsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public FoodsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static FoodsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static FoodsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodsTable_Factory(provider);
    }

    public static FoodsTable newFoodsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new FoodsTable(sQLiteDatabaseWrapper);
    }
}
