package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RemindersTable_Factory implements Factory<RemindersTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public RemindersTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public RemindersTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static RemindersTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new RemindersTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static RemindersTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new RemindersTable_Factory(provider);
    }

    public static RemindersTable newRemindersTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new RemindersTable(sQLiteDatabaseWrapper);
    }
}
