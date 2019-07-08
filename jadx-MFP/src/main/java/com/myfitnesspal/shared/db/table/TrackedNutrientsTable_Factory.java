package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class TrackedNutrientsTable_Factory implements Factory<TrackedNutrientsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public TrackedNutrientsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public TrackedNutrientsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static TrackedNutrientsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new TrackedNutrientsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static TrackedNutrientsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new TrackedNutrientsTable_Factory(provider);
    }

    public static TrackedNutrientsTable newTrackedNutrientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new TrackedNutrientsTable(sQLiteDatabaseWrapper);
    }
}
