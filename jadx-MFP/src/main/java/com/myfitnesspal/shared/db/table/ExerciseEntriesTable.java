package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class ExerciseEntriesTable extends MfpDatabaseTableV2Impl {
    private static final int ADD_EXERCISE_ENTRY_APP_ID_DB_VERSION = 38;
    private static final String IDX_RECENTLY_USED = "exercise_entries_recently_used_index";
    private static final String IDX_SYNC = "exercise_entries_sync_index";
    private static final String IDX_SYNC_FLAGS = "exercise_entries_sync_flags_index";
    private static final String IDX_SYNC_V2 = "exercise_entries_syncv2_index";
    private static final String IDX_USAGE_COUNT = "exercise_entries_usage_count_index";
    private static final String IDX_USER_ID_ENTRY_DATE = "exercise_entries_user_id_entry_date_index";
    public static final String TABLE_NAME = "exercise_entries";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String APP_ID = "app_id";
        public static final String AVG_HEART_RATE = "avg_heart_rate";
        public static final String CALORIES = "calories";
        public static final String CLIENT_ID = "client_id";
        public static final String DEVICE_ID = "device_id";
        public static final String DISTANCE_IN_MILES = "distance_in_miles";
        public static final String DURATION_IN_SECONDS = "duration_in_seconds";
        public static final String ELEVATION_CHANGE_IN_FEET = "elevation_change_in_feet";
        public static final String ENERGY_OFFSET_DATA = "energy_offset_data";
        public static final String ENTRY_DATE = "entry_date";
        public static final String EXERCISE = "exercise";
        public static final String EXERCISE_ID = "exercise_id";
        public static final String EXERCISE_MASTER_ID = "exercise_master_id";
        public static final String EXERCISE_TYPE = "exercise_type";
        public static final String EXERCISE_VERSION = "exercise_version";
        public static final String ID = "id";
        public static final String IS_CALORIE_ADJUSTMENT = "is_calorie_adjustment";
        public static final String MASTER_ID = "master_id";
        public static final String MAX_HEART_RATE = "max_heart_rate";
        public static final String MAX_SPEED_IN_MPH = "max_speed_in_mph";
        public static final String ORIGINAL_EXERCISE_ID = "original_exercise_id";
        public static final String QUANTITY = "quantity";
        public static final String REPETITIONS = "repetitions";
        public static final String SETS = "sets";
        public static final String SOURCE = "source";
        public static final String START_TIME = "start_time";
        public static final String STEPS = "steps";
        public static final String USER_ID = "user_id";
        public static final String WEIGHT = "weight";
    }

    @Inject
    public ExerciseEntriesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "exercise_type integer not null", "exercise_id integer not null", "original_exercise_id integer not null", "quantity integer", "sets integer", "weight integer", "calories integer");
        createBaseIndexes();
        createBaseUsageCountIndex();
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(11, i, i2)) {
            updateData("master_id", "4294967296 + master_id", "master_id < 0");
        }
        if (shouldRunUpgrade(19, i, i2)) {
            recreateTable();
        }
        if (addUidColumnIfNecessary(i, i2, null)) {
            createUniqueIndex(IDX_SYNC_V2, "uid", "id");
        }
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(35, i, i2)) {
            addColumn(Columns.DURATION_IN_SECONDS, " INTEGER");
            addColumn(Columns.REPETITIONS, " INTEGER");
            addColumn("steps", " INTEGER DEFAULT 0");
            addColumn("device_id", " TEXT");
            addColumn("client_id", " TEXT");
            addColumn(Columns.DISTANCE_IN_MILES, " NUMBER DEFAULT 0");
            addColumn(Columns.MAX_SPEED_IN_MPH, " NUMBER DEFAULT 0");
            addColumn(Columns.AVG_HEART_RATE, " INTEGER DEFAULT 0");
            addColumn("max_heart_rate", " INTEGER DEFAULT 0");
            addColumn(Columns.ELEVATION_CHANGE_IN_FEET, " NUMBER DEFAULT 0");
            addColumn(Columns.ENERGY_OFFSET_DATA, " TEXT");
            addColumn("start_time", " TEXT");
            addColumn("source", " TEXT");
            addColumn("exercise", " TEXT");
            addColumn(Columns.EXERCISE_MASTER_ID, " INTEGER DEFAULT 0");
            addColumn(Columns.EXERCISE_VERSION, " TEXT");
            addColumn(Columns.IS_CALORIE_ADJUSTMENT, " BOOL DEFAULT 0");
        }
        if (shouldRunUpgrade(37, i, i2)) {
            recreateTableForRemovalOfOriginalExerciseId();
        }
        if (shouldRunUpgrade(38, i, i2)) {
            addColumn("app_id", " TEXT");
        }
    }

    private void recreateTable() {
        renameTable("tmp_exercise_entries");
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "exercise_type integer not null", "exercise_id integer not null", "original_exercise_id integer not null", "quantity integer", "sets integer", "weight real", "calories real");
        String join = Strings.join(", ", (T[]) new String[]{"master_id", "user_id", "entry_date", "exercise_type", "exercise_id", "original_exercise_id", "quantity", Columns.SETS, "weight", "calories"});
        insertDataFromOtherTable(join, join, "tmp_exercise_entries");
        dropTable("tmp_exercise_entries");
        createBaseIndexes();
        createBaseUsageCountIndex();
    }

    private void recreateTableForRemovalOfOriginalExerciseId() {
        renameTable("tmp_exercise_entries");
        createTable("id integer primary key autoincrement", "master_id integer unique", "user_id integer not null", "entry_date text not null", "exercise_type integer not null", "exercise_id integer not null", "quantity integer", "sets integer", "weight real", "calories real", "uid text", "sync_flags INTEGER NOT NULL DEFAULT 0", "duration_in_seconds INTEGER", "repetitions INTEGER", "steps INTEGER DEFAULT 0", "device_id TEXT", "client_id TEXT", "distance_in_miles NUMBER DEFAULT 0", "max_speed_in_mph NUMBER DEFAULT 0", "avg_heart_rate INTEGER DEFAULT 0", "max_heart_rate INTEGER DEFAULT 0", "elevation_change_in_feet NUMBER DEFAULT 0", "energy_offset_data TEXT", "start_time TEXT", "source TEXT", "exercise TEXT", "exercise_master_id INTEGER DEFAULT 0", "exercise_version TEXT", "is_calorie_adjustment BOOL DEFAULT 0");
        String join = Strings.join(", ", (T[]) new String[]{"master_id", "user_id", "entry_date", "exercise_type", "exercise_id", "quantity", Columns.SETS, "weight", "calories", "uid", com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns.SYNC_FLAGS, Columns.DURATION_IN_SECONDS, Columns.REPETITIONS, "steps", "device_id", "client_id", Columns.DISTANCE_IN_MILES, Columns.MAX_SPEED_IN_MPH, Columns.AVG_HEART_RATE, "max_heart_rate", Columns.ELEVATION_CHANGE_IN_FEET, Columns.ENERGY_OFFSET_DATA, "start_time", "source", "exercise", Columns.EXERCISE_MASTER_ID, Columns.EXERCISE_VERSION, Columns.IS_CALORIE_ADJUSTMENT});
        insertDataFromOtherTable(join, join, "tmp_exercise_entries");
        dropTable("tmp_exercise_entries");
        createBaseIndexes();
        createIndex(IDX_USAGE_COUNT, "user_id", "exercise_type");
        createUniqueIndex(IDX_SYNC_V2, "uid", "id");
        createIndex(IDX_SYNC_FLAGS, com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns.SYNC_FLAGS);
    }

    private void createBaseIndexes() {
        createIndex(IDX_USER_ID_ENTRY_DATE, "user_id", "entry_date");
        createUniqueIndex(IDX_SYNC, "user_id", "master_id", "id");
        createUniqueIndex(IDX_RECENTLY_USED, "user_id", "exercise_id", "id");
    }

    private void createBaseUsageCountIndex() {
        createIndex(IDX_USAGE_COUNT, "user_id", "exercise_type", "original_exercise_id");
    }
}
