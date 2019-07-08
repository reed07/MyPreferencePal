package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class NutritionalValuesTable_Factory implements Factory<NutritionalValuesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public NutritionalValuesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public NutritionalValuesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static NutritionalValuesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new NutritionalValuesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static NutritionalValuesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new NutritionalValuesTable_Factory(provider);
    }

    public static NutritionalValuesTable newNutritionalValuesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new NutritionalValuesTable(sQLiteDatabaseWrapper);
    }
}
