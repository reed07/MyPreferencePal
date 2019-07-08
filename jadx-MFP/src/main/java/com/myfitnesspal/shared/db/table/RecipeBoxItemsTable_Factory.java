package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecipeBoxItemsTable_Factory implements Factory<RecipeBoxItemsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public RecipeBoxItemsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public RecipeBoxItemsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static RecipeBoxItemsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipeBoxItemsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static RecipeBoxItemsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipeBoxItemsTable_Factory(provider);
    }

    public static RecipeBoxItemsTable newRecipeBoxItemsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new RecipeBoxItemsTable(sQLiteDatabaseWrapper);
    }
}
