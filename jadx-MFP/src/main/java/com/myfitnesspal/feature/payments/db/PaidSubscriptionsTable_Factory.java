package com.myfitnesspal.feature.payments.db;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class PaidSubscriptionsTable_Factory implements Factory<PaidSubscriptionsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public PaidSubscriptionsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public PaidSubscriptionsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static PaidSubscriptionsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new PaidSubscriptionsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static PaidSubscriptionsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new PaidSubscriptionsTable_Factory(provider);
    }

    public static PaidSubscriptionsTable newPaidSubscriptionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new PaidSubscriptionsTable(sQLiteDatabaseWrapper);
    }
}
