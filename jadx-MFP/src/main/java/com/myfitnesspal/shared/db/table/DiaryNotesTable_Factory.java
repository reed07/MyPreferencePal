package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import dagger.internal.Factory;
import javax.inject.Provider;

public final class DiaryNotesTable_Factory implements Factory<DiaryNotesTable> {
    private final Provider<SQLiteDatabaseWrapper> databaseProvider;

    public DiaryNotesTable_Factory(Provider<SQLiteDatabaseWrapper> provider) {
        this.databaseProvider = provider;
    }

    public DiaryNotesTable get() {
        return provideInstance(this.databaseProvider);
    }

    public static DiaryNotesTable provideInstance(Provider<SQLiteDatabaseWrapper> provider) {
        return new DiaryNotesTable((SQLiteDatabaseWrapper) provider.get());
    }

    public static DiaryNotesTable_Factory create(Provider<SQLiteDatabaseWrapper> provider) {
        return new DiaryNotesTable_Factory(provider);
    }

    public static DiaryNotesTable newDiaryNotesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        return new DiaryNotesTable(sQLiteDatabaseWrapper);
    }
}
