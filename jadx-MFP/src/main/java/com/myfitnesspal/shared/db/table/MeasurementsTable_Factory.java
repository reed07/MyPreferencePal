package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MeasurementsTable_Factory implements Factory<MeasurementsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public MeasurementsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public MeasurementsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static MeasurementsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new MeasurementsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static MeasurementsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new MeasurementsTable_Factory(provider);
    }

    public static MeasurementsTable newMeasurementsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new MeasurementsTable(sQLiteDatabaseWrapper);
    }
}
