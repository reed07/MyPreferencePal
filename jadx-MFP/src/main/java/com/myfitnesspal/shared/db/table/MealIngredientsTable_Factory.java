package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class MealIngredientsTable_Factory implements Factory<MealIngredientsTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public MealIngredientsTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public MealIngredientsTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static MealIngredientsTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new MealIngredientsTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static MealIngredientsTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new MealIngredientsTable_Factory(provider);
    }

    public static MealIngredientsTable newMealIngredientsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new MealIngredientsTable(sQLiteDatabaseWrapper);
    }
}
