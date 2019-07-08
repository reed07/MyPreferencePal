package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StockExercisesTable_Factory implements Factory<StockExercisesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public StockExercisesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public StockExercisesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static StockExercisesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new StockExercisesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static StockExercisesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new StockExercisesTable_Factory(provider);
    }

    public static StockExercisesTable newStockExercisesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new StockExercisesTable(sQLiteDatabaseWrapper);
    }
}
