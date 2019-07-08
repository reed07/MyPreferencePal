package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;
import javax.inject.Named;

public class StockExercisesTable extends MfpDatabaseTableV2Impl {
    public static final String TABLE_NAME = "stock_exercises";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String DESCRIPTION = "description";
        public static final String EXERCISE_TYPE = "exercise_type";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String SEARCHABLE = "searchable";
    }

    public void onCreate() {
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public StockExercisesTable(@Named("stock_database") SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }
}
