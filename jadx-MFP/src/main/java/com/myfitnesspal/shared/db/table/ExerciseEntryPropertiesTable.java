package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class ExerciseEntryPropertiesTable extends MfpDatabaseTableImpl {
    private static final String IDX_MAIN = "exercise_entry_properties_main_index";
    private static final String TABLE_NAME = "exercise_entry_properties";

    public static final class Columns {
        public static final String EXERCISE_ENTRY_ID = "exercise_entry_id";
        public static final String PROPERTY_NAME = "property_name";
        public static final String PROPERTY_VALUE = "property_value";
    }

    public static final class Keys {
        public static final String ALLOW_NEGATIVE_CALORIE_ADJUSTMENT = "allow_negative_calorie_adjustment";
        public static final String APP_ID = "app_id";
        public static final String CALORIE_ADJUSTMENT_REDUCED = "calorie_adjustment_reduced";
        public static final String CLIENT_APP_CALORIE_BURNED_AMOUNT = "client_app_calorie_burned_amount";
        public static final String CLIENT_APP_CALORIE_BURNED_PROJECTION_AMOUNT = "client_app_calorie_burned_projection_amount";
        public static final String CLIENT_APP_EXERCISE_CALORIES = "client_app_exercise_calories";
        public static final String CLIENT_APP_MFP_CALORIE_PROJECTION = "client_app_mfp_calorie_projection";
        public static final String CLIENT_APP_NAME = "client_app_name";
        public static final String CLIENT_APP_PROJECTION_TIMESTAMP = "client_app_projection_timestamp";
        public static final String SOURCE = "source";
        public static final String START_TIME = "start_time";
    }

    public void onUpgrade(int i, int i2) {
    }

    @Inject
    public ExerciseEntryPropertiesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("exercise_entry_id integer not null", "property_name text not null", "property_value text not null");
        createUniqueIndex(IDX_MAIN, Columns.EXERCISE_ENTRY_ID, "property_name");
    }
}
