package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MeasurementTypesTable_Factory implements Factory<MeasurementTypesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public MeasurementTypesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public MeasurementTypesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static MeasurementTypesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new MeasurementTypesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static MeasurementTypesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new MeasurementTypesTable_Factory(provider);
    }

    public static MeasurementTypesTable newMeasurementTypesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new MeasurementTypesTable(sQLiteDatabaseWrapper);
    }
}
