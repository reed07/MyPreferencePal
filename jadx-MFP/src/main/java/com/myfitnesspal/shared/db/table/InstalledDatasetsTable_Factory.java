package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class InstalledDatasetsTable_Factory implements Factory<InstalledDatasetsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public InstalledDatasetsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public InstalledDatasetsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static InstalledDatasetsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new InstalledDatasetsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static InstalledDatasetsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new InstalledDatasetsTable_Factory(provider);
    }

    public static InstalledDatasetsTable newInstalledDatasetsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new InstalledDatasetsTable(sQLiteDatabaseWrapper);
    }
}
