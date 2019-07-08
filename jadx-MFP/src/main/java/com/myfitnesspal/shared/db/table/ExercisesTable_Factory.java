package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExercisesTable_Factory implements Factory<ExercisesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public ExercisesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public ExercisesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static ExercisesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExercisesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static ExercisesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExercisesTable_Factory(provider);
    }

    public static ExercisesTable newExercisesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new ExercisesTable(sQLiteDatabaseWrapper);
    }
}
