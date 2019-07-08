package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodPortionsTable_Factory implements Factory<FoodPortionsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public FoodPortionsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public FoodPortionsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static FoodPortionsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodPortionsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static FoodPortionsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodPortionsTable_Factory(provider);
    }

    public static FoodPortionsTable newFoodPortionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new FoodPortionsTable(sQLiteDatabaseWrapper);
    }
}
