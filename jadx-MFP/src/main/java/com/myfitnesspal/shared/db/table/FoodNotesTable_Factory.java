package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class FoodNotesTable_Factory implements Factory<FoodNotesTable> {
    private final Provider<SQLiteDatabaseWrapper> dbProvider;

    public FoodNotesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.dbProvider = provider;
    }

    public FoodNotesTable get() {
        return provideInstance(this.dbProvider);
    }

    public static FoodNotesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodNotesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static FoodNotesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new FoodNotesTable_Factory(provider);
    }

    public static FoodNotesTable newFoodNotesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new FoodNotesTable(sQLiteDatabaseWrapper);
    }
}
