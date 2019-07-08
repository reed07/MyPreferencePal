package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class ExerciseEntryPropertiesTable_Factory implements Factory<ExerciseEntryPropertiesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public ExerciseEntryPropertiesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public ExerciseEntryPropertiesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static ExerciseEntryPropertiesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExerciseEntryPropertiesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static ExerciseEntryPropertiesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new ExerciseEntryPropertiesTable_Factory(provider);
    }

    public static ExerciseEntryPropertiesTable newExerciseEntryPropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new ExerciseEntryPropertiesTable(sQLiteDatabaseWrapper);
    }
}
