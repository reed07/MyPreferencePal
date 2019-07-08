package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import com.brightcove.player.event.EventType;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodNotes;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.Strings;
import javax.inject.Inject;

public class FoodNotesTable extends MfpDatabaseTableV2Impl {
    private static final String[] ALL_COLUMNS = {EventType.ANY};
    private static final String IDX_FOOD_UID = "food_notes_food_uid_index";
    private static final String IDX_ORIGINAL_FOOD_UID = "food_notes_original_food_uid_index";
    public static final String TABLE_NAME = "food_notes";

    public static final class Columns {
        public static final String FOOD_LOCAL_ID = "food_local_id";
        public static final String FOOD_MASTER_ID = "food_master_id";
        public static final String FOOD_UID = "food_uid";
        public static final String ID = "id";
        public static final String MASTER_ID = "master_id";
        public static final String NOTES = "notes";
        public static final String ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
        public static final String ORIGINAL_FOOD_UID = "original_food_uid";
        public static final String UID = "uid";
        public static final String USER_ID = "user_id";
    }

    public void onCreate() {
    }

    @Inject
    public FoodNotesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, TABLE_NAME);
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(47, i, i2)) {
            createTableAndIndexes();
        }
    }

    public long replace(FoodNotes foodNotes) {
        Food food = new Food();
        food.setLocalId(foodNotes.getFoodLocalId());
        food.setMasterDatabaseId(foodNotes.getFoodMasterId());
        food.setUid(foodNotes.getUid());
        deleteByFood(food);
        return save(foodNotes);
    }

    public long save(FoodNotes foodNotes) {
        ContentValues contentValues = new ContentValues();
        Long l = null;
        contentValues.put("master_id", foodNotes.getMasterId() == 0 ? null : Long.valueOf(foodNotes.getMasterId()));
        contentValues.put("uid", foodNotes.getUid());
        contentValues.put("food_local_id", Long.valueOf(foodNotes.getFoodLocalId()));
        contentValues.put("food_master_id", foodNotes.getFoodMasterId() == 0 ? null : Long.valueOf(foodNotes.getFoodMasterId()));
        contentValues.put("food_uid", foodNotes.getFoodUid());
        String str = "original_food_master_id";
        if (foodNotes.getOriginalFoodMasterId() != 0) {
            l = Long.valueOf(foodNotes.getOriginalFoodMasterId());
        }
        contentValues.put(str, l);
        contentValues.put("original_food_uid", foodNotes.getOriginalFoodUid());
        contentValues.put("user_id", Long.valueOf(foodNotes.getUserId()));
        contentValues.put(Columns.NOTES, foodNotes.getNotes());
        return insertData(contentValues);
    }

    public FoodNotes findByLocalId(long j) {
        if (j <= 0) {
            return null;
        }
        return extractFirstFoodNote(queryData(ALL_COLUMNS, "id=?", String.valueOf(j)));
    }

    public FoodNotes findByFood(Food food) {
        long masterDatabaseId = food.getMasterDatabaseId();
        String uid = food.getUid();
        long localId = food.getLocalId();
        if (masterDatabaseId == 0 && Strings.isEmpty(uid) && localId == 0) {
            return null;
        }
        return extractFirstFoodNote(queryData(ALL_COLUMNS, "food_master_id=? OR food_uid=? OR food_local_id=?", String.valueOf(food.getMasterDatabaseId()), food.getUid(), String.valueOf(localId)));
    }

    /* JADX INFO: finally extract failed */
    private static final FoodNotes extractFirstFoodNote(Cursor cursor) {
        if (cursor != null) {
            try {
                if (cursor.moveToFirst()) {
                    FoodNotes foodNotes = new FoodNotes(new CursorMapper(cursor));
                    if (cursor != null) {
                        cursor.close();
                    }
                    return foodNotes;
                } else if (cursor != null) {
                    cursor.close();
                }
            } catch (Throwable th) {
                if (cursor != null) {
                    cursor.close();
                }
                throw th;
            }
        }
        return null;
    }

    public boolean deleteByLocalId(long j) {
        if (j > 0) {
            if (deleteData("id=?", String.valueOf(j)) > 0) {
                return true;
            }
        }
        return false;
    }

    public boolean deleteByFood(Food food) {
        int i;
        long localId = food.getLocalId();
        long masterDatabaseId = food.getMasterDatabaseId();
        String uid = food.getUid();
        if (localId != 0) {
            i = deleteData("food_local_id=?", String.valueOf(localId));
        } else {
            i = 0;
        }
        if (i == 0 && masterDatabaseId != 0) {
            i = deleteData("food_master_id=?", String.valueOf(masterDatabaseId));
        }
        if (i == 0 && Strings.notEmpty(uid)) {
            i = deleteData("food_uid=?", uid);
        }
        if (i > 0) {
            return true;
        }
        return false;
    }

    private void createTableAndIndexes() {
        createTable("id INTEGER PRIMARY KEY AUTOINCREMENT", "master_id INTEGER UNIQUE", "uid TEXT", "food_local_id INTEGER", "food_master_id INTEGER", "food_uid TEXT", "original_food_master_id INTEGER", "original_food_uid TEXT", "notes TEXT", "user_id INTEGER");
        createIndex(IDX_FOOD_UID, "food_uid");
        createIndex(IDX_ORIGINAL_FOOD_UID, "original_food_uid");
    }
}
