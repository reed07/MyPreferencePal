package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NutrientGoalsTable_Factory implements Factory<NutrientGoalsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public NutrientGoalsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public NutrientGoalsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static NutrientGoalsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new NutrientGoalsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static NutrientGoalsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new NutrientGoalsTable_Factory(provider);
    }

    public static NutrientGoalsTable newNutrientGoalsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new NutrientGoalsTable(sQLiteDatabaseWrapper);
    }
}
