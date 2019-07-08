package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodPermissionsTable_Factory implements Factory<FoodPermissionsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public FoodPermissionsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public FoodPermissionsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static FoodPermissionsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodPermissionsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static FoodPermissionsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodPermissionsTable_Factory(provider);
    }

    public static FoodPermissionsTable newFoodPermissionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new FoodPermissionsTable(sQLiteDatabaseWrapper);
    }
}
