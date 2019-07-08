package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ProfileImagesTable_Factory implements Factory<ProfileImagesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public ProfileImagesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public ProfileImagesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static ProfileImagesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new ProfileImagesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static ProfileImagesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new ProfileImagesTable_Factory(provider);
    }

    public static ProfileImagesTable newProfileImagesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new ProfileImagesTable(sQLiteDatabaseWrapper);
    }
}
