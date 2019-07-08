package com.myfitnesspal.shared.db.table;

import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class ExercisesTable extends MfpDatabaseTableV2Impl {
    private static final String IDX_ALPHABETICAL = "exercises_alphabetical_index";
    private static final String IDX_ORIGINAL_EXERCISE_ID = "exercises_original_exercise_id_index";
    private static final String IDX_ORIGINAL_UID = "exercises_original_uid_index";
    private static final String IDX_SYNC_FLAGS = "exercises_sync_flags_index";
    private static final String IDX_UID = "exercises_uid_index";
    public static final String TABLE_NAME = "exercises";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String DELETED = "deleted";
        public static final String DESCRIPTION = "description";
        public static final String DESTROYED = "destroyed";
        public static final String EXERCISE_TYPE = "exercise_type";
        public static final String IS_CALORIE_ADJUSTMENT_EXERCISE = "is_calorie_adjustment_exercise";
        public static final String IS_PUBLIC = "is_public";
        public static final String METS = "mets";
        public static final String ORIGINAL_EXERCISE_ID = "original_exercise_id";
        public static final String ORIGINAL_EXERCISE_MASTER_ID = "original_exercise_master_id";
        public static final String OWNER_USER_ID = "owner_user_id";
        public static final String OWNER_USER_MASTER_ID = "owner_user_master_id";
    }

    @Inject
    public ExercisesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onCreate() {
        createTable("id integer primary key autoincrement", "master_id integer unique", "original_exercise_id integer default null", "original_exercise_master_id integer default null", "owner_user_id integer", "owner_user_master_id", "exercise_type integer not null", "description text not null collate nocase", "mets real", "deleted integer not null default 0", "destroyed integer not null default 0", "is_public integer not null default 0");
        createAlphaIndex();
        createIndex(IDX_ORIGINAL_EXERCISE_ID, "original_exercise_id");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(15, i, i2)) {
            addColumn(Columns.IS_CALORIE_ADJUSTMENT_EXERCISE, "integer not null default 0");
        }
        addUidColumnIfNecessary(i, i2, IDX_UID);
        addOriginalUidColumnIfNecessary(i, i2, IDX_ORIGINAL_UID);
        addSyncFlagsColumnIfNecessary(i, i2, IDX_SYNC_FLAGS);
        if (shouldRunUpgrade(35, i, i2)) {
            createTrigger();
        }
        if (shouldRunUpgrade(37, i, i2)) {
            recreateTableForRemovalOfOriginalExerciseId();
        }
    }

    private void createTrigger() {
        this.database.execSQL("CREATE TRIGGER exercises_after_insert_uid AFTER INSERT ON exercises FOR EACH ROW WHEN IFNULL(NEW.uid, '') = '' BEGIN UPDATE exercises SET uid = NEW.original_uid WHERE id = NEW.id; END;");
    }

    private void recreateTableForRemovalOfOriginalExerciseId() {
        renameTable("tmp_exercises");
        createTable("id integer primary key autoincrement", "master_id integer unique", "owner_user_id integer", "owner_user_master_id", "exercise_type integer not null", "description text not null collate nocase", "mets real", "deleted integer not null default 0", "destroyed integer not null default 0", "is_public integer not null default 0", "is_calorie_adjustment_exercise integer not null default 0", "uid text", "original_uid text", "sync_flags INTEGER NOT NULL DEFAULT 0");
        String join = Strings.join(", ", (T[]) new String[]{"master_id", "owner_user_id", "owner_user_master_id", "exercise_type", "description", Columns.METS, "deleted", "destroyed", "is_public", Columns.IS_CALORIE_ADJUSTMENT_EXERCISE, "uid", "original_uid", com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns.SYNC_FLAGS});
        insertDataFromOtherTable(join, join, "tmp_exercises");
        dropTable("tmp_exercises");
        createAlphaIndex();
        createTrigger();
    }

    private void createAlphaIndex() {
        createIndex(IDX_ALPHABETICAL, "owner_user_id", "deleted", "description");
    }
}
