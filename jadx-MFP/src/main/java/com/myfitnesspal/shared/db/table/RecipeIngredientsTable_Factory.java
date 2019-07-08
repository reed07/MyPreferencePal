package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class RecipeIngredientsTable_Factory implements Factory<RecipeIngredientsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public RecipeIngredientsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public RecipeIngredientsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static RecipeIngredientsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipeIngredientsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static RecipeIngredientsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new RecipeIngredientsTable_Factory(provider);
    }

    public static RecipeIngredientsTable newRecipeIngredientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new RecipeIngredientsTable(sQLiteDatabaseWrapper);
    }
}
