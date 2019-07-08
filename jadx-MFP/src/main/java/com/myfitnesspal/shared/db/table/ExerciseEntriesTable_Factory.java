package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExerciseEntriesTable_Factory implements Factory<ExerciseEntriesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public ExerciseEntriesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public ExerciseEntriesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static ExerciseEntriesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExerciseEntriesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static ExerciseEntriesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExerciseEntriesTable_Factory(provider);
    }

    public static ExerciseEntriesTable newExerciseEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new ExerciseEntriesTable(sQLiteDatabaseWrapper);
    }
}
