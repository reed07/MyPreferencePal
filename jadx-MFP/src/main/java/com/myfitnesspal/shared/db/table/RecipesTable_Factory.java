package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecipesTable_Factory implements Factory<RecipesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public RecipesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public RecipesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static RecipesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static RecipesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipesTable_Factory(provider);
    }

    public static RecipesTable newRecipesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new RecipesTable(sQLiteDatabaseWrapper);
    }
}
