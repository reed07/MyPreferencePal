package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecipePropertiesTable_Factory implements Factory<RecipePropertiesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public RecipePropertiesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public RecipePropertiesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static RecipePropertiesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipePropertiesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static RecipePropertiesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipePropertiesTable_Factory(provider);
    }

    public static RecipePropertiesTable newRecipePropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new RecipePropertiesTable(sQLiteDatabaseWrapper);
    }
}
