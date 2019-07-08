package com.myfitnesspal.shared.db.table;

import com.facebook.appevents.AppEventsConstants;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class InstalledDatasetsTable extends MfpDatabaseTableImpl {
    private static final String TABLE_NAME = "installed_datasets";

    public static final class Columns {
        public static final String DATASET_ID = "dataset_id";
        public static final String DESCRIPTION = "description";
        public static final String IDENTIFIER = "identifier";
        public static final String PRIORITY = "priority";
        public static final String TYPE = "type";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public InstalledDatasetsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("dataset_id integer unique not null", Columns.IDENTIFIER, "type text not null", "description text not null", "priority integer not null default 1");
        insertData((Object[]) new String[]{String.valueOf(1), "stock_data", "stock", "Stock Data", AppEventsConstants.EVENT_PARAM_VALUE_YES}, Columns.DATASET_ID, Columns.IDENTIFIER, "type", "description", Columns.PRIORITY);
    }
}
