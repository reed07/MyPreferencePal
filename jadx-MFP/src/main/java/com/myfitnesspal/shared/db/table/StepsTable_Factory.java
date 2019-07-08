package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class StepsTable_Factory implements Factory<StepsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public StepsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public StepsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static StepsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new StepsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static StepsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new StepsTable_Factory(provider);
    }

    public static StepsTable newStepsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new StepsTable(sQLiteDatabaseWrapper);
    }
}
