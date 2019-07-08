package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodEntriesTable_Factory implements Factory<FoodEntriesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public FoodEntriesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public FoodEntriesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static FoodEntriesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodEntriesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static FoodEntriesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodEntriesTable_Factory(provider);
    }

    public static FoodEntriesTable newFoodEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new FoodEntriesTable(sQLiteDatabaseWrapper);
    }
}
