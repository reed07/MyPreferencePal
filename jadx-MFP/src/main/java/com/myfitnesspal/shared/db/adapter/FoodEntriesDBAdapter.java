package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDoneException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.feature.externalsync.service.ExternalNutritionService;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.model.MealNames;
import com.myfitnesspal.shared.model.User;
import com.myfitnesspal.shared.model.v1.DatabaseObject;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodEntry;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.util.Database;
import com.myfitnesspal.shared.util.DateTimeUtils;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.ReturningFunction2;
import com.uacf.core.util.Strings;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

public class FoodEntriesDBAdapter extends SessionDBAdapter {
    private static final long CUTOFF_FOR_FREQUENTS = 3000;
    private static final String DATABASE_TABLE = "food_entries";
    private static final String KEY_ENTRY_DATE = "entry_date";
    private static final String KEY_ENTRY_TIME = "entry_time";
    private static final String KEY_FOOD_ID = "food_id";
    private static final String KEY_FRACTION = "fraction";
    private static final String KEY_ID = "id";
    private static final String KEY_LOGGED_AT = "logged_at";
    private static final String KEY_MASTER_ID = "master_id";
    private static final String KEY_MEAL_FOOD_ID = "meal_food_id";
    private static final String KEY_MEAL_ID = "meal_id";
    private static final String KEY_ORIGINAL_FOOD_ID = "original_food_id";
    private static final String KEY_QUANTITY = "quantity";
    private static final String KEY_UID = "uid";
    private static final String KEY_USER_ID = "user_id";
    private static final String KEY_WEIGHT_INDEX = "weight_index";
    private final Context context;
    private final DbConnectionManager dbConnectionManager;
    private SQLiteStatement stmt;

    public FoodEntriesDBAdapter(Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public boolean hasLoggedAtLeastOneItem() {
        Throwable th;
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT EXISTS (SELECT * FROM food_entries WHERE sync_flags != ");
        sb.append(String.valueOf(3));
        sb.append(" AND ");
        sb.append("user_id");
        sb.append("= ");
        sb.append(String.valueOf(getSession().getUser().getLocalId()));
        sb.append(" LIMIT 1)");
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(sb.toString(), null);
        boolean z = false;
        if (rawQuery != null) {
            try {
                rawQuery.moveToFirst();
                if (rawQuery.getInt(0) == 1) {
                    z = true;
                }
                if (rawQuery != null) {
                    rawQuery.close();
                }
                return z;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
            }
        } else {
            if (rawQuery != null) {
                rawQuery.close();
            }
            return false;
        }
        throw th;
    }

    /* JADX INFO: finally extract failed */
    public ArrayList<FoodEntry> fetchFoodEntriesOnDate(Date date) {
        int i;
        User user;
        HashMap hashMap;
        int i2;
        int i3;
        int i4;
        int i5;
        Cursor cursor = null;
        try {
            User user2 = getSession().getUser();
            Cursor query = DbConnectionManager.getDb(this.context).query("food_entries", new String[]{"id", "master_id", "food_id", "meal_id", "quantity", "weight_index", "fraction", "uid", "meal_food_id", "entry_time", "logged_at"}, "sync_flags != ? AND user_id= ? AND entry_date= ? ", new String[]{String.valueOf(3), String.valueOf(user2.getLocalId()), Database.encodeDate(date)}, null, null, null);
            int count = query.getCount();
            if (count > 0) {
                query.moveToFirst();
            }
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("master_id");
            int columnIndex3 = query.getColumnIndex("food_id");
            int columnIndex4 = query.getColumnIndex("meal_id");
            int columnIndex5 = query.getColumnIndex("quantity");
            int columnIndex6 = query.getColumnIndex("weight_index");
            int columnIndex7 = query.getColumnIndex("fraction");
            int columnIndex8 = query.getColumnIndex("uid");
            int columnIndex9 = query.getColumnIndex("meal_food_id");
            int columnIndex10 = query.getColumnIndex("entry_time");
            int columnIndex11 = query.getColumnIndex("logged_at");
            HashMap hashMap2 = new HashMap();
            ArrayList<FoodEntry> arrayList = new ArrayList<>(count);
            int i6 = columnIndex9;
            int i7 = 0;
            while (i7 < count) {
                int i8 = count;
                FoodEntry foodEntry = new FoodEntry();
                int i9 = i7;
                foodEntry.setDate(date);
                int i10 = columnIndex10;
                foodEntry.setLocalId(query.getLong(columnIndex));
                foodEntry.setMasterDatabaseId(query.getLong(columnIndex2));
                int i11 = columnIndex;
                int i12 = columnIndex2;
                Food fetchFoodById = this.dbConnectionManager.foodDbAdapter().fetchFoodById(query.getLong(columnIndex3));
                if (fetchFoodById != null) {
                    foodEntry.setFood(fetchFoodById);
                    i = i11;
                    foodEntry.setMealName(user2.getMealNames().nameForId(query.getLong(columnIndex4)));
                    foodEntry.setQuantity(query.getFloat(columnIndex5));
                    int i13 = query.getInt(columnIndex6);
                    FoodPortion foodPortionWithIndex = fetchFoodById.foodPortionWithIndex(i13);
                    if (foodPortionWithIndex == null) {
                        StringBuilder sb = new StringBuilder();
                        sb.append("warning: no food portion with index ");
                        sb.append(i13);
                        user = user2;
                        Ln.i(sb.toString(), new Object[0]);
                    } else {
                        user = user2;
                    }
                    foodEntry.setFoodPortion(foodPortionWithIndex);
                    foodEntry.setWeightIndex(i13);
                    foodEntry.setFraction(query.getInt(columnIndex7) != 0);
                    foodEntry.setUid(query.getString(columnIndex8));
                    arrayList.add(foodEntry);
                    i5 = i10;
                    String string = query.getString(i5);
                    if (string != null) {
                        foodEntry.setEntryTime(Database.decodeTimeString(string));
                    }
                    String string2 = query.getString(columnIndex11);
                    if (string2 != null) {
                        foodEntry.setLoggedAt(Database.decodeDateAndTimeString(string2));
                        i2 = columnIndex11;
                        i3 = columnIndex3;
                        i4 = i6;
                    } else {
                        i2 = columnIndex11;
                        i3 = columnIndex3;
                        i4 = i6;
                    }
                    hashMap = hashMap2;
                    addFoodEntryToMealFoodIdMapIfValid(hashMap, query.getLong(i4), foodEntry);
                } else {
                    user = user2;
                    i2 = columnIndex11;
                    i3 = columnIndex3;
                    i = i11;
                    hashMap = hashMap2;
                    i4 = i6;
                    i5 = i10;
                }
                query.moveToNext();
                i6 = i4;
                columnIndex3 = i3;
                columnIndex11 = i2;
                hashMap2 = hashMap;
                columnIndex2 = i12;
                columnIndex = i;
                columnIndex10 = i5;
                i7 = i9 + 1;
                user2 = user;
                count = i8;
            }
            hydrateMealFoodForFoodEntries(hashMap2);
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private void addFoodEntryToMealFoodIdMapIfValid(Map<Long, List<FoodEntry>> map, long j, FoodEntry foodEntry) {
        if (j > 0) {
            List list = (List) map.get(Long.valueOf(j));
            if (list == null) {
                list = new ArrayList();
                map.put(Long.valueOf(j), list);
            }
            list.add(foodEntry);
        }
    }

    private void hydrateMealFoodForFoodEntries(Map<Long, List<FoodEntry>> map) {
        if (!map.isEmpty()) {
            for (MealFood mealFood : this.dbConnectionManager.foodDbAdapter().getMealFoodsForLocalIds(new ArrayList(map.keySet()))) {
                for (FoodEntry mealFood2 : (List) map.get(Long.valueOf(mealFood.getLocalId()))) {
                    mealFood2.setMealFood(mealFood);
                }
            }
        }
    }

    public void insertFoodEntry(FoodEntry foodEntry, DbConnectionManager dbConnectionManager2) {
        if (foodEntry != null) {
            try {
                User user = getSession().getUser();
                Food food = foodEntry.getFood();
                int mealIdForName = user.getMealNames().mealIdForName(foodEntry.getMealName());
                Food insertFoodIfMissing = dbConnectionManager2.foodDbAdapter().insertFoodIfMissing(food, dbConnectionManager2);
                this.stmt = DbConnectionManager.preparedStatement(1);
                this.stmt.bindLong(1, user.getLocalId());
                if (foodEntry.hasMasterDatabaseId()) {
                    this.stmt.bindLong(2, foodEntry.masterDatabaseId);
                } else {
                    this.stmt.bindNull(2);
                }
                if (foodEntry.hasUid()) {
                    this.stmt.bindString(3, foodEntry.getUid());
                } else {
                    this.stmt.bindNull(3);
                }
                this.stmt.bindString(4, Database.encodeDate(foodEntry.getDate()));
                this.stmt.bindLong(5, insertFoodIfMissing.getLocalId());
                this.stmt.bindLong(6, insertFoodIfMissing.getOriginalId());
                this.stmt.bindLong(7, (long) mealIdForName);
                this.stmt.bindDouble(8, (double) foodEntry.getQuantity());
                this.stmt.bindLong(9, (long) foodEntry.getWeightIndex());
                this.stmt.bindLong(10, foodEntry.isFraction() ? 1 : 0);
                MealFood mealFood = foodEntry.getMealFood();
                this.stmt.bindLong(11, mealFood != null ? mealFood.getLocalId() : 0);
                if (foodEntry.getEntryTime() != null) {
                    this.stmt.bindString(12, Database.encodeTime(foodEntry.getEntryTime()));
                } else {
                    this.stmt.bindNull(12);
                }
                if (foodEntry.getLoggedAt() != null) {
                    this.stmt.bindString(13, Database.encodeDateAndTime(foodEntry.getLoggedAt()));
                } else {
                    this.stmt.bindNull(13);
                }
                long executeInsert = this.stmt.executeInsert();
                this.stmt.clearBindings();
                if (executeInsert >= 0) {
                    foodEntry.setLocalId(executeInsert);
                }
            } catch (SQLiteConstraintException e) {
                Ln.e(e);
            }
        }
    }

    private long getCutoffIdForLimit(long j, long j2) {
        long j3;
        this.stmt = DbConnectionManager.preparedStatement(63);
        this.stmt.bindLong(1, j);
        this.stmt.bindLong(2, j2);
        try {
            j3 = this.stmt.simpleQueryForLong();
        } catch (SQLiteDoneException e) {
            Ln.d(e);
            j3 = 0;
        }
        this.stmt.clearBindings();
        return j3;
    }

    public List<DiaryEntryCellModel> fetchFrequentFoodsForUserId(long j, int i, int i2) {
        return createListOfFoodsBasedOnId(fetchFrequentlyUsedFoodIdsForUserIdIncludingMealFoods(j, (long) i, i2));
    }

    public void deleteFoodEntry(FoodEntry foodEntry, @Nonnull Lazy<ExternalNutritionService> lazy) {
        try {
            long masterDatabaseId = foodEntry.getMasterDatabaseId();
            FoodEntry fetchFoodEntryById = fetchFoodEntryById(foodEntry.getLocalId());
            long masterDatabaseId2 = (masterDatabaseId != 0 || fetchFoodEntryById == null) ? masterDatabaseId : fetchFoodEntryById.getMasterDatabaseId();
            if (masterDatabaseId2 > 0) {
                this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(getSession().getUser().getLocalId(), 4, masterDatabaseId2, true);
                this.stmt = DbConnectionManager.preparedStatement(59);
                this.stmt.bindLong(1, foodEntry.getLocalId());
                this.stmt.execute();
                this.stmt.clearBindings();
            } else {
                SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
                ContentValues contentValues = new ContentValues();
                contentValues.put(Columns.SYNC_FLAGS, Integer.valueOf(3));
                db.update("food_entries", contentValues, "id = ?", new String[]{String.valueOf(foodEntry.getLocalId())});
            }
            if (fetchFoodEntryById != null) {
                foodEntry = fetchFoodEntryById;
            }
            ((ExternalNutritionService) lazy.get()).onFoodEntryDeleted(foodEntry, ((Session) this.session.get()).getUser().getUserId());
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public int fetchOverallUsageCountForOriginalFoodLocalId(long j, long j2) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(58);
            this.stmt.bindLong(1, j2);
            this.stmt.bindLong(2, j);
            int simpleQueryForLong = (int) this.stmt.simpleQueryForLong();
            this.stmt.clearBindings();
            return simpleQueryForLong;
        } catch (Exception e) {
            Ln.e(e);
            return -1;
        }
    }

    public List<DiaryEntryCellModel> fetchRecentlyUsedFoodsForUserId(long j, long j2, int i) {
        return createListOfFoodsBasedOnId(fetchRecentFoodIdsForUserIdIncludingMealFoods(j, j2, i));
    }

    /* JADX INFO: finally extract failed */
    private List<Long> fetchRecentFoodIdsForUserIdIncludingMealFoods(long j, long j2, int i) {
        boolean z = j2 != 0;
        StringBuilder sb = new StringBuilder("SELECT\n       CASE\n           WHEN meal_food_id > 0 THEN meal_food_id\n           ELSE food_id\n       END AS food_id, entry_date\n   FROM food_entries\n   WHERE user_id=?\n");
        if (z) {
            sb.append(" AND meal_id = ?\n");
        }
        sb.append("   ORDER BY entry_date DESC, food_id DESC");
        String format = String.format("SELECT DISTINCT(food_id) AS food_id FROM (%s) AS inner_query LIMIT ?", new Object[]{sb.toString()});
        String valueOf = String.valueOf(j);
        String valueOf2 = String.valueOf(j2);
        String valueOf3 = String.valueOf(i);
        ArrayList arrayList = new ArrayList();
        arrayList.add(valueOf);
        if (z) {
            arrayList.add(valueOf2);
        }
        arrayList.add(valueOf3);
        Cursor cursor = null;
        try {
            Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(format, (String[]) arrayList.toArray(new String[0]));
            int columnIndex = rawQuery.getColumnIndex("food_id");
            ArrayList arrayList2 = new ArrayList();
            while (rawQuery.moveToNext()) {
                arrayList2.add(Long.valueOf(rawQuery.getLong(columnIndex)));
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private List<Long> fetchFrequentlyUsedFoodIdsForUserIdIncludingMealFoods(long j, long j2, int i) {
        boolean z = j2 != 0;
        String format = String.format("SELECT food_id, usage_count FROM (%s UNION ALL %s) AS frequent_foods_query ORDER BY usage_count DESC, food_id DESC LIMIT ?", new Object[]{getQueryForFrequentFoodIdsExcludingMealFoods(z), getQueryForFrequentMealFoodIds(z)});
        String valueOf = String.valueOf(getCutoffIdForLimit(j, CUTOFF_FOR_FREQUENTS));
        String valueOf2 = String.valueOf(j);
        String valueOf3 = String.valueOf(j2);
        ArrayList arrayList = new ArrayList();
        arrayList.add(valueOf2);
        arrayList.add(valueOf2);
        arrayList.add(valueOf);
        if (z) {
            arrayList.add(valueOf3);
        }
        arrayList.add(valueOf2);
        arrayList.add(valueOf2);
        arrayList.add(valueOf);
        if (z) {
            arrayList.add(valueOf3);
        }
        arrayList.add(Strings.toString(Integer.valueOf(i)));
        Cursor cursor = null;
        try {
            Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(format, (String[]) arrayList.toArray(new String[0]));
            int columnIndex = rawQuery.getColumnIndex("food_id");
            ArrayList arrayList2 = new ArrayList();
            while (rawQuery.moveToNext()) {
                arrayList2.add(Long.valueOf(rawQuery.getLong(columnIndex)));
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return arrayList2;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    private String getQueryForFrequentFoodIdsExcludingMealFoods(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT\n    fe.food_id AS food_id,\n    COUNT(*) AS usage_count\n  FROM foods AS f, food_entries AS fe\n  WHERE\n    f.id = fe.food_id\n    AND f.original_food_master_id NOT IN (\n      SELECT original_food_master_id FROM deleted_most_used_foods\n      WHERE user_id = ?\n    )\n    AND fe.user_id = ?\n    AND fe.id > ?\n    AND fe.meal_food_id = 0\n    AND f.destroyed = 0\n");
        sb.append(z ? "    AND fe.meal_id = ?\n" : "");
        sb.append("  GROUP BY fe.food_id");
        return sb.toString();
    }

    private String getQueryForFrequentMealFoodIds(boolean z) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT\n    fe.meal_food_id AS food_id,\n    COUNT(*) AS usage_count\n  FROM foods AS f, food_entries AS fe\n  WHERE\n    f.id = fe.meal_food_id\n    AND fe.meal_food_id > 0\n    AND fe.user_id = ?\n    AND f.original_food_master_id NOT IN (\n      SELECT original_food_master_id FROM deleted_most_used_foods\n      WHERE user_id = ?\n    )\n    AND fe.id > ?\n");
        sb.append(z ? "    AND fe.meal_id = ?\n" : "");
        sb.append("  GROUP BY fe.meal_food_id");
        return sb.toString();
    }

    public List<DiaryEntryCellModel> fetchRecentFrequentAndOwnedFoodsForUserId(int i, long j, int i2, int i3) {
        long j2 = j;
        long j3 = (long) i2;
        int i4 = i3;
        List fetchRecentFoodIdsForUserIdIncludingMealFoods = fetchRecentFoodIdsForUserIdIncludingMealFoods(j2, j3, i4);
        List fetchFrequentlyUsedFoodIdsForUserIdIncludingMealFoods = fetchFrequentlyUsedFoodIdsForUserIdIncludingMealFoods(j2, j3, i4);
        List fetchOwnedFoodIdsOfType = this.dbConnectionManager.foodDbAdapter().fetchOwnedFoodIdsOfType(i, SortOrder.RECENTLY_USED, i3, 0);
        HashSet hashSet = new HashSet();
        Iterator it = fetchRecentFoodIdsForUserIdIncludingMealFoods.iterator();
        Iterator it2 = fetchFrequentlyUsedFoodIdsForUserIdIncludingMealFoods.iterator();
        Iterator it3 = fetchOwnedFoodIdsOfType.iterator();
        ArrayList arrayList = new ArrayList();
        while (hashSet.size() < i3 && (it.hasNext() || it2.hasNext() || it3.hasNext())) {
            if (it.hasNext()) {
                Long l = (Long) it.next();
                if (hashSet.add(l)) {
                    arrayList.add(l);
                    if (hashSet.size() >= i3) {
                        break;
                    }
                }
            }
            if (it2.hasNext()) {
                Long l2 = (Long) it2.next();
                if (hashSet.add(l2)) {
                    arrayList.add(l2);
                    if (hashSet.size() >= i3) {
                        break;
                    }
                }
            }
            if (it3.hasNext()) {
                Long l3 = (Long) it3.next();
                if (hashSet.add(l3)) {
                    arrayList.add(l3);
                    if (hashSet.size() >= i3) {
                        break;
                    }
                } else {
                    continue;
                }
            }
        }
        return createListOfFoodsBasedOnId(arrayList);
    }

    /* JADX WARNING: Removed duplicated region for block: B:47:0x015c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.FoodEntry fetchFoodEntryById(long r20) {
        /*
            r19 = this;
            r1 = r19
            r2 = 0
            r4 = 0
            int r0 = (r20 > r2 ? 1 : (r20 == r2 ? 0 : -1))
            if (r0 != 0) goto L_0x000a
            return r4
        L_0x000a:
            android.content.Context r0 = r1.context     // Catch:{ all -> 0x0158 }
            com.uacf.core.database.SQLiteDatabaseWrapper r5 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)     // Catch:{ all -> 0x0158 }
            java.lang.String r6 = "food_entries"
            java.lang.String r7 = "id"
            java.lang.String r8 = "master_id"
            java.lang.String r9 = "entry_date"
            java.lang.String r10 = "food_id"
            java.lang.String r11 = "meal_id"
            java.lang.String r12 = "quantity"
            java.lang.String r13 = "weight_index"
            java.lang.String r14 = "fraction"
            java.lang.String r15 = "uid"
            java.lang.String r16 = "meal_food_id"
            java.lang.String r17 = "entry_time"
            java.lang.String r18 = "logged_at"
            java.lang.String[] r7 = new java.lang.String[]{r7, r8, r9, r10, r11, r12, r13, r14, r15, r16, r17, r18}     // Catch:{ all -> 0x0158 }
            java.lang.String r8 = "id=?"
            r0 = 1
            java.lang.String[] r9 = new java.lang.String[r0]     // Catch:{ all -> 0x0158 }
            java.lang.String r10 = java.lang.String.valueOf(r20)     // Catch:{ all -> 0x0158 }
            r13 = 0
            r9[r13] = r10     // Catch:{ all -> 0x0158 }
            r10 = 0
            r11 = 0
            r12 = 0
            android.database.Cursor r5 = r5.query(r6, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x0158 }
            boolean r6 = r5.moveToFirst()     // Catch:{ all -> 0x0156 }
            if (r6 == 0) goto L_0x0123
            com.myfitnesspal.shared.model.v1.FoodEntry r6 = new com.myfitnesspal.shared.model.v1.FoodEntry     // Catch:{ all -> 0x0156 }
            r6.<init>()     // Catch:{ all -> 0x0156 }
            java.lang.String r7 = "id"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0156 }
            long r7 = r5.getLong(r7)     // Catch:{ all -> 0x0156 }
            r6.setLocalId(r7)     // Catch:{ all -> 0x0156 }
            java.lang.String r7 = "master_id"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0156 }
            long r7 = r5.getLong(r7)     // Catch:{ all -> 0x0156 }
            r6.setMasterDatabaseId(r7)     // Catch:{ all -> 0x0156 }
            java.lang.String r7 = "entry_date"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0156 }
            java.lang.String r7 = r5.getString(r7)     // Catch:{ all -> 0x0156 }
            java.util.Date r7 = com.myfitnesspal.shared.util.Database.decodeDateString(r7)     // Catch:{ all -> 0x0156 }
            r6.setDate(r7)     // Catch:{ all -> 0x0156 }
            java.lang.String r7 = "food_id"
            int r7 = r5.getColumnIndex(r7)     // Catch:{ all -> 0x0156 }
            long r7 = r5.getLong(r7)     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.service.session.Session r9 = r19.getSession()     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.model.User r9 = r9.getUser()     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.model.MealNames r9 = r9.getMealNames()     // Catch:{ all -> 0x0156 }
            java.lang.String r10 = "meal_id"
            int r10 = r5.getColumnIndex(r10)     // Catch:{ all -> 0x0156 }
            int r10 = r5.getInt(r10)     // Catch:{ all -> 0x0156 }
            long r10 = (long) r10     // Catch:{ all -> 0x0156 }
            java.lang.String r9 = r9.nameForId(r10)     // Catch:{ all -> 0x0156 }
            r6.setMealName(r9)     // Catch:{ all -> 0x0156 }
            java.lang.String r9 = "quantity"
            int r9 = r5.getColumnIndex(r9)     // Catch:{ all -> 0x0156 }
            double r9 = r5.getDouble(r9)     // Catch:{ all -> 0x0156 }
            float r9 = (float) r9     // Catch:{ all -> 0x0156 }
            r6.setQuantity(r9)     // Catch:{ all -> 0x0156 }
            java.lang.String r9 = "weight_index"
            int r9 = r5.getColumnIndex(r9)     // Catch:{ all -> 0x0156 }
            int r9 = r5.getInt(r9)     // Catch:{ all -> 0x0156 }
            r6.setWeightIndex(r9)     // Catch:{ all -> 0x0156 }
            java.lang.String r10 = "fraction"
            int r10 = r5.getColumnIndex(r10)     // Catch:{ all -> 0x0156 }
            int r10 = r5.getInt(r10)     // Catch:{ all -> 0x0156 }
            if (r10 == 0) goto L_0x00c7
            r13 = 1
        L_0x00c7:
            r6.setIsFraction(r13)     // Catch:{ all -> 0x0156 }
            java.lang.String r0 = "uid"
            int r0 = r5.getColumnIndex(r0)     // Catch:{ all -> 0x0156 }
            java.lang.String r0 = r5.getString(r0)     // Catch:{ all -> 0x0156 }
            r6.setUid(r0)     // Catch:{ all -> 0x0156 }
            java.lang.String r0 = "entry_time"
            int r0 = r5.getColumnIndex(r0)     // Catch:{ all -> 0x0156 }
            java.lang.String r0 = r5.getString(r0)     // Catch:{ all -> 0x0156 }
            boolean r10 = com.uacf.core.util.Strings.isEmpty(r0)     // Catch:{ all -> 0x0156 }
            if (r10 != 0) goto L_0x00ee
            java.util.Date r0 = com.myfitnesspal.shared.util.Database.decodeTimeString(r0)     // Catch:{ all -> 0x0156 }
            r6.setEntryTime(r0)     // Catch:{ all -> 0x0156 }
        L_0x00ee:
            java.lang.String r0 = "logged_at"
            int r0 = r5.getColumnIndex(r0)     // Catch:{ all -> 0x0156 }
            java.lang.String r0 = r5.getString(r0)     // Catch:{ all -> 0x0156 }
            boolean r10 = com.uacf.core.util.Strings.isEmpty(r0)     // Catch:{ all -> 0x0156 }
            if (r10 != 0) goto L_0x0105
            java.util.Date r0 = com.myfitnesspal.shared.util.Database.decodeDateAndTimeString(r0)     // Catch:{ all -> 0x0156 }
            r6.setLoggedAt(r0)     // Catch:{ all -> 0x0156 }
        L_0x0105:
            java.lang.String r0 = "meal_food_id"
            int r0 = r5.getColumnIndex(r0)     // Catch:{ all -> 0x0156 }
            long r10 = r5.getLong(r0)     // Catch:{ all -> 0x0156 }
            int r0 = (r10 > r2 ? 1 : (r10 == r2 ? 0 : -1))
            if (r0 <= 0) goto L_0x0120
            com.myfitnesspal.shared.db.DbConnectionManager r0 = r1.dbConnectionManager     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.db.adapter.FoodDBAdapter r0 = r0.foodDbAdapter()     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.model.v1.MealFood r0 = r0.getMealFoodForLocalId(r10)     // Catch:{ all -> 0x0156 }
            r6.setMealFood(r0)     // Catch:{ all -> 0x0156 }
        L_0x0120:
            r2 = r7
            r13 = r9
            goto L_0x0124
        L_0x0123:
            r6 = r4
        L_0x0124:
            if (r6 != 0) goto L_0x012c
            if (r5 == 0) goto L_0x012b
            r5.close()
        L_0x012b:
            return r4
        L_0x012c:
            com.myfitnesspal.shared.db.DbConnectionManager r0 = r1.dbConnectionManager     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.db.adapter.FoodDBAdapter r0 = r0.foodDbAdapter()     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.model.v1.Food r0 = r0.fetchFoodById(r2)     // Catch:{ all -> 0x0156 }
            if (r0 != 0) goto L_0x013e
            if (r5 == 0) goto L_0x013d
            r5.close()
        L_0x013d:
            return r4
        L_0x013e:
            r6.setFood(r0)     // Catch:{ all -> 0x0156 }
            com.myfitnesspal.shared.model.v1.FoodPortion r0 = r0.foodPortionWithIndex(r13)     // Catch:{ all -> 0x0156 }
            if (r0 != 0) goto L_0x014d
            if (r5 == 0) goto L_0x014c
            r5.close()
        L_0x014c:
            return r4
        L_0x014d:
            r6.setFoodPortion(r0)     // Catch:{ all -> 0x0156 }
            if (r5 == 0) goto L_0x0155
            r5.close()
        L_0x0155:
            return r6
        L_0x0156:
            r0 = move-exception
            goto L_0x015a
        L_0x0158:
            r0 = move-exception
            r5 = r4
        L_0x015a:
            if (r5 == 0) goto L_0x015f
            r5.close()
        L_0x015f:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter.fetchFoodEntryById(long):com.myfitnesspal.shared.model.v1.FoodEntry");
    }

    public List<DiaryEntryCellModel> replaceFoodsWithCorrespondingRecentFoodEntries(List<DiaryEntryCellModel> list) {
        HashMap hashMap = new HashMap(list.size());
        HashMap hashMap2 = new HashMap();
        HashMap hashMap3 = new HashMap();
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        for (DiaryEntryCellModel diaryEntryCellModel : list) {
            if (!diaryEntryCellModel.isFood()) {
                hashMap.put(Long.valueOf(((DatabaseObject) diaryEntryCellModel).getLocalId()), diaryEntryCellModel);
            } else {
                Food food = (Food) diaryEntryCellModel;
                if (food instanceof MealFood) {
                    MealFood mealFood = (MealFood) food;
                    if (CollectionUtils.notEmpty((Collection<?>) mealFood.getIngredients())) {
                        hashMap.put(Long.valueOf(mealFood.getLocalId()), mealFood);
                    } else if (mealFood.getLocalId() > 0) {
                        mealFood.setIngredients(this.dbConnectionManager.mealIngredientsDbAdapter().mealIngredientsForMealFood(mealFood));
                        hashMap.put(Long.valueOf(mealFood.getLocalId()), mealFood);
                    }
                }
                long j = food.getOriginalId() > 0 ? food.getOriginalId() : food.getOriginalMasterId() > 0 ? this.dbConnectionManager.foodDbAdapter().lookupOriginalFoodIdFromMasterId(food.getOriginalMasterId()) : 0;
                if (j > 0) {
                    arrayList.add(Long.valueOf(j));
                } else {
                    j = food.getLocalId();
                    arrayList2.add(Long.valueOf(j));
                }
                hashMap3.put(Long.valueOf(j), food);
            }
        }
        if (!arrayList.isEmpty()) {
            getFoodEntriesForFoodIdAndPopulateMealFoodMap(arrayList, hashMap, hashMap3, hashMap2, "original_food_id");
        }
        if (!arrayList2.isEmpty()) {
            getFoodEntriesForFoodIdAndPopulateMealFoodMap(arrayList2, hashMap, hashMap3, hashMap2, "food_id");
        }
        hydrateMealFoodForFoodEntries(hashMap2);
        return createFoodEntriesListWithSameOrderingAsFoods(hashMap, list);
    }

    /* JADX WARNING: type inference failed for: r2v4, types: [com.myfitnesspal.shared.model.v1.DiaryEntryCellModel] */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<com.myfitnesspal.shared.model.v1.DiaryEntryCellModel> createFoodEntriesListWithSameOrderingAsFoods(java.util.Map<java.lang.Long, com.myfitnesspal.shared.model.v1.DiaryEntryCellModel> r5, java.util.List<com.myfitnesspal.shared.model.v1.DiaryEntryCellModel> r6) {
        /*
            r4 = this;
            java.util.ArrayList r0 = new java.util.ArrayList
            int r1 = r6.size()
            r0.<init>(r1)
            java.util.Iterator r6 = r6.iterator()
        L_0x000d:
            boolean r1 = r6.hasNext()
            if (r1 == 0) goto L_0x004a
            java.lang.Object r1 = r6.next()
            com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r1 = (com.myfitnesspal.shared.model.v1.DiaryEntryCellModel) r1
            boolean r2 = r1.isFood()
            if (r2 != 0) goto L_0x0033
            com.myfitnesspal.shared.model.v1.DatabaseObject r1 = (com.myfitnesspal.shared.model.v1.DatabaseObject) r1
            long r1 = r1.getLocalId()
            java.lang.Long r1 = java.lang.Long.valueOf(r1)
            java.lang.Object r1 = r5.get(r1)
            com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r1 = (com.myfitnesspal.shared.model.v1.DiaryEntryCellModel) r1
            r0.add(r1)
            goto L_0x000d
        L_0x0033:
            com.myfitnesspal.shared.model.v1.Food r1 = (com.myfitnesspal.shared.model.v1.Food) r1
            long r2 = r1.getLocalId()
            java.lang.Long r2 = java.lang.Long.valueOf(r2)
            java.lang.Object r2 = r5.get(r2)
            com.myfitnesspal.shared.model.v1.DiaryEntryCellModel r2 = (com.myfitnesspal.shared.model.v1.DiaryEntryCellModel) r2
            if (r2 == 0) goto L_0x0046
            r1 = r2
        L_0x0046:
            r0.add(r1)
            goto L_0x000d
        L_0x004a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter.createFoodEntriesListWithSameOrderingAsFoods(java.util.Map, java.util.List):java.util.List");
    }

    /* JADX INFO: finally extract failed */
    private void getFoodEntriesForFoodIdAndPopulateMealFoodMap(List<Long> list, Map<Long, DiaryEntryCellModel> map, Map<Long, Food> map2, Map<Long, List<FoodEntry>> map3, String str) {
        Map<Long, DiaryEntryCellModel> map4 = map;
        String str2 = str;
        Cursor cursor = null;
        try {
            String[] strArr = {"meal_id", "quantity", "weight_index", "fraction", "meal_food_id", "original_food_id", "food_id", "entry_time", "logged_at"};
            StringBuilder sb = new StringBuilder();
            sb.append(str2);
            sb.append(" IN ");
            sb.append(DatabaseUtil.getArgsForList(list));
            Cursor query = DbConnectionManager.getDb(this.context).query("food_entries", strArr, sb.toString(), null, null, null, "id DESC");
            User user = getSession().getUser();
            MealNames mealNames = user.getMealNames();
            CursorMapper cursorMapper = new CursorMapper(query);
            while (cursorMapper.moveToNext()) {
                Food food = (Food) map2.get(Long.valueOf(cursorMapper.getLong(str2)));
                if (!map4.containsKey(Long.valueOf(food.getLocalId()))) {
                    FoodEntry foodEntry = new FoodEntry();
                    foodEntry.setDate(user.getActiveDate());
                    foodEntry.setLocalId(0);
                    foodEntry.setMasterDatabaseId(0);
                    foodEntry.setFood(food);
                    foodEntry.setMealName(mealNames.nameForId(cursorMapper.getLong("meal_id")));
                    foodEntry.setQuantity(cursorMapper.getFloat("quantity"));
                    int i = cursorMapper.getInt("weight_index");
                    FoodPortion foodPortionWithIndex = food.foodPortionWithIndex(i);
                    if (foodPortionWithIndex == null) {
                        foodPortionWithIndex = food.defaultPortion();
                    }
                    foodEntry.setFoodPortion(foodPortionWithIndex);
                    foodEntry.setWeightIndex(i);
                    foodEntry.setIsFraction(cursorMapper.getInt("fraction") != 0);
                    addFoodEntryToMealFoodIdMapIfValid(map3, cursorMapper.getLong("meal_food_id"), foodEntry);
                    String string = cursorMapper.getString("entry_time");
                    if (!Strings.isEmpty(string)) {
                        foodEntry.setEntryTime(Database.decodeTimeString(string));
                    }
                    String string2 = cursorMapper.getString("logged_at");
                    if (!Strings.isEmpty(string2)) {
                        foodEntry.setLoggedAt(Database.decodeDateAndTimeString(string2));
                    }
                    map4.put(Long.valueOf(food.getLocalId()), foodEntry);
                }
            }
            if (query != null) {
                query.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public ArrayList<DiaryEntryCellModel> fetchLatestPreviousFoodEntriesForMealId(int i) {
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery("select entry_date from food_entries where user_id = ? and entry_date < ? and meal_id = ? order by entry_date desc limit 1", new String[]{String.valueOf(getSession().getUser().getLocalId()), DateTimeUtils.formatDb(new Date()), String.valueOf(i)});
        try {
            String string = rawQuery.moveToFirst() ? rawQuery.getString(rawQuery.getColumnIndexOrThrow("entry_date")) : null;
            if (rawQuery != null) {
                rawQuery.close();
            }
            if (string == null) {
                return null;
            }
            return fetchFoodEntriesForMealId(i, string);
        } catch (Throwable th) {
            if (rawQuery != null) {
                rawQuery.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    private ArrayList<DiaryEntryCellModel> fetchFoodEntriesForMealId(int i, String str) {
        ArrayList arrayList;
        int i2;
        User user = getSession().getUser();
        ArrayList arrayList2 = new ArrayList();
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("food_entries", new String[]{"id", "master_id", "food_id", "quantity", "weight_index", "fraction", "uid", "meal_food_id", "entry_time", "logged_at"}, "user_id=? AND entry_date=? AND meal_id=?", new String[]{String.valueOf(user.getLocalId()), str, String.valueOf(i)}, null, null, null);
            HashMap hashMap = new HashMap();
            if (query.moveToFirst()) {
                int columnIndex = query.getColumnIndex("id");
                int columnIndex2 = query.getColumnIndex("master_id");
                int columnIndex3 = query.getColumnIndex("food_id");
                int columnIndex4 = query.getColumnIndex("quantity");
                int columnIndex5 = query.getColumnIndex("weight_index");
                int columnIndex6 = query.getColumnIndex("fraction");
                int columnIndex7 = query.getColumnIndex("uid");
                int columnIndex8 = query.getColumnIndex("meal_food_id");
                int columnIndex9 = query.getColumnIndex("entry_time");
                int columnIndex10 = query.getColumnIndex("logged_at");
                while (true) {
                    FoodEntry foodEntry = new FoodEntry();
                    ArrayList arrayList3 = arrayList2;
                    foodEntry.setDate(DateTimeUtils.parseDb(str));
                    int i3 = columnIndex10;
                    int i4 = columnIndex8;
                    foodEntry.setLocalId(query.getLong(columnIndex));
                    foodEntry.setMasterDatabaseId(query.getLong(columnIndex2));
                    Food fetchFoodById = fetchFoodById(query.getLong(columnIndex3));
                    foodEntry.setFood(fetchFoodById);
                    User user2 = user;
                    int i5 = columnIndex;
                    int i6 = columnIndex2;
                    foodEntry.setMealName(user.getMealNames().nameForId((long) i));
                    foodEntry.setQuantity(query.getFloat(columnIndex4));
                    int i7 = query.getInt(columnIndex5);
                    foodEntry.setWeightIndex(i7);
                    foodEntry.setFoodPortion(fetchFoodById.foodPortionWithIndex(i7));
                    foodEntry.setIsFraction(query.getInt(columnIndex6) == 1);
                    foodEntry.setUid(query.getString(columnIndex7));
                    addFoodEntryToMealFoodIdMapIfValid(hashMap, query.getLong(i4), foodEntry);
                    String string = query.getString(columnIndex9);
                    if (!Strings.isEmpty(string)) {
                        foodEntry.setEntryTime(Database.decodeTimeString(string));
                        i2 = i3;
                    } else {
                        i2 = i3;
                    }
                    String string2 = query.getString(i2);
                    if (!Strings.isEmpty(string2)) {
                        foodEntry.setLoggedAt(Database.decodeDateAndTimeString(string2));
                        arrayList = arrayList3;
                    } else {
                        arrayList = arrayList3;
                    }
                    arrayList.add(foodEntry);
                    if (!query.moveToNext()) {
                        break;
                    }
                    columnIndex8 = i4;
                    arrayList2 = arrayList;
                    user = user2;
                    columnIndex2 = i6;
                    columnIndex10 = i2;
                    columnIndex = i5;
                }
            } else {
                arrayList = arrayList2;
            }
            hydrateMealFoodForFoodEntries(hashMap);
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public Food fetchFoodById(long j) {
        Food food;
        boolean z = true;
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(String.format("select %s from foods where id = ?", new Object[]{DatabaseUtil.getColumnString(new String[]{"id", "master_id", "original_food_id", "original_food_master_id", "owner_user_id", "owner_user_master_id", "food_type", "is_public", "deleted", "description", "brand", "food_barcode", "food_grams"})}), new String[]{String.valueOf(j)});
        try {
            if (rawQuery.moveToFirst()) {
                int i = CursorUtils.getInt(rawQuery, "food_type");
                if (i == 1) {
                    food = new Food();
                } else if (i == 3) {
                    food = new MealFood();
                } else if (i != 11) {
                    return null;
                } else {
                    food = new RecipeFood();
                }
                food.setLocalId(CursorUtils.getLong(rawQuery, "id"));
                food.setMasterDatabaseId(CursorUtils.getLong(rawQuery, "master_id"));
                food.setOriginalId(CursorUtils.getLong(rawQuery, "original_food_id"));
                food.setOriginalMasterId(CursorUtils.getLong(rawQuery, "original_food_master_id"));
                food.setOwnerUserId(CursorUtils.getLong(rawQuery, "owner_user_id"));
                food.setOwnerUserMasterId(CursorUtils.getLong(rawQuery, "owner_user_master_id"));
                food.setIsPublic(CursorUtils.getInt(rawQuery, "is_public") != 0);
                if (CursorUtils.getInt(rawQuery, "deleted") == 0) {
                    z = false;
                }
                food.setIsDeleted(z);
                food.setGrams(CursorUtils.getFloat(rawQuery, "food_grams"));
                food.setDescription(CursorUtils.getString(rawQuery, "description"));
                if (food.getDescription() == null || food.getDescription().length() == 0) {
                    food.setDescription("Invalid Food");
                }
                if (rawQuery.getColumnIndex("brand") == -1) {
                    food.setBrand(null);
                } else {
                    food.setBrand(rawQuery.getString(rawQuery.getColumnIndex("brand")));
                }
                food.setBarcode(CursorUtils.getString(rawQuery, "food_barcode"));
                long localId = food.getLocalId();
                food.setFoodPortions(this.dbConnectionManager.foodPortionsDBAdapter().getFoodPortions(localId));
                food.setNutritionalValues(this.dbConnectionManager.nutritionalValuesDBAdapter().getNutritionalValues(localId));
            } else {
                food = null;
            }
            rawQuery.close();
            return food;
        } finally {
            rawQuery.close();
        }
    }

    private List<DiaryEntryCellModel> createListOfFoodsBasedOnId(List<Long> list) {
        FoodDBAdapter foodDbAdapter = this.dbConnectionManager.foodDbAdapter();
        Map foodIdToFoodMapForMultipleIds = foodDbAdapter.getFoodIdToFoodMapForMultipleIds(list);
        if (foodIdToFoodMapForMultipleIds == null) {
            return new ArrayList();
        }
        ArrayList arrayList = new ArrayList();
        for (Long l : list) {
            Food food = (Food) foodIdToFoodMapForMultipleIds.get(l);
            if (food != null) {
                if (food.isMeal() && food.isDeleted()) {
                    if (food.getOriginalId() != 0) {
                        food = foodDbAdapter.fetchLatestVersionOfFoodForOriginalId(food.getOriginalId());
                    }
                }
                arrayList.add(food);
            }
        }
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    private List<String> getDatesForOriginalFoodId(long j) {
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("food_entries", new String[]{"entry_date"}, "original_food_id=?", new String[]{Long.toString(j)}, null, null, null);
            ArrayList arrayList = new ArrayList(query.getCount());
            int columnIndex = query.getColumnIndex("entry_date");
            while (query.moveToNext()) {
                arrayList.add(query.getString(columnIndex));
            }
            if (query != null) {
                query.close();
            }
            return arrayList;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:16:0x0074  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private java.util.List<java.lang.Long> getOriginalFoodIdsForFoodsOnDate(long r14, java.util.List<java.lang.String> r16, long r17) {
        /*
            r13 = this;
            r1 = 0
            java.lang.String r0 = com.uacf.core.database.DatabaseUtil.getArgsForList(r16)     // Catch:{ all -> 0x0070 }
            r2 = r13
            android.content.Context r3 = r2.context     // Catch:{ all -> 0x006e }
            com.uacf.core.database.SQLiteDatabaseWrapper r4 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r3)     // Catch:{ all -> 0x006e }
            java.lang.String r5 = "food_entries"
            java.lang.String r3 = "original_food_id"
            java.lang.String r6 = "COUNT(*) AS frequency"
            java.lang.String[] r6 = new java.lang.String[]{r3, r6}     // Catch:{ all -> 0x006e }
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ all -> 0x006e }
            r3.<init>()     // Catch:{ all -> 0x006e }
            java.lang.String r7 = "meal_id=? AND original_food_id!=? AND entry_date IN "
            r3.append(r7)     // Catch:{ all -> 0x006e }
            r3.append(r0)     // Catch:{ all -> 0x006e }
            java.lang.String r7 = r3.toString()     // Catch:{ all -> 0x006e }
            r0 = 2
            java.lang.String[] r8 = new java.lang.String[r0]     // Catch:{ all -> 0x006e }
            r0 = 0
            java.lang.String r3 = java.lang.Long.toString(r17)     // Catch:{ all -> 0x006e }
            r8[r0] = r3     // Catch:{ all -> 0x006e }
            r0 = 1
            java.lang.String r3 = java.lang.Long.toString(r14)     // Catch:{ all -> 0x006e }
            r8[r0] = r3     // Catch:{ all -> 0x006e }
            java.lang.String r9 = "original_food_id"
            r10 = 0
            java.lang.String r11 = "frequency DESC"
            r0 = 10
            java.lang.String r12 = java.lang.Integer.toString(r0)     // Catch:{ all -> 0x006e }
            android.database.Cursor r1 = r4.query(r5, r6, r7, r8, r9, r10, r11, r12)     // Catch:{ all -> 0x006e }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x006e }
            int r3 = r1.getCount()     // Catch:{ all -> 0x006e }
            r0.<init>(r3)     // Catch:{ all -> 0x006e }
            java.lang.String r3 = "original_food_id"
            int r3 = r1.getColumnIndex(r3)     // Catch:{ all -> 0x006e }
        L_0x0056:
            boolean r4 = r1.moveToNext()     // Catch:{ all -> 0x006e }
            if (r4 == 0) goto L_0x0068
            long r4 = r1.getLong(r3)     // Catch:{ all -> 0x006e }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ all -> 0x006e }
            r0.add(r4)     // Catch:{ all -> 0x006e }
            goto L_0x0056
        L_0x0068:
            if (r1 == 0) goto L_0x006d
            r1.close()
        L_0x006d:
            return r0
        L_0x006e:
            r0 = move-exception
            goto L_0x0072
        L_0x0070:
            r0 = move-exception
            r2 = r13
        L_0x0072:
            if (r1 == 0) goto L_0x0077
            r1.close()
        L_0x0077:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodEntriesDBAdapter.getOriginalFoodIdsForFoodsOnDate(long, java.util.List, long):java.util.List");
    }

    public List<DiaryEntryCellModel> fetchPairedFoods(long j, long j2, final String str) {
        List datesForOriginalFoodId = getDatesForOriginalFoodId(j);
        if (CollectionUtils.isEmpty((Collection<?>) datesForOriginalFoodId)) {
            return null;
        }
        List originalFoodIdsForFoodsOnDate = getOriginalFoodIdsForFoodsOnDate(j, datesForOriginalFoodId, j2);
        if (CollectionUtils.isEmpty((Collection<?>) originalFoodIdsForFoodsOnDate)) {
            return null;
        }
        return new ArrayList(Enumerable.where((Collection<T>) replaceFoodsWithCorrespondingRecentFoodEntries(new ArrayList(Enumerable.where((Collection<T>) this.dbConnectionManager.foodDbAdapter().fetchFoodsByOriginalIds(originalFoodIdsForFoodsOnDate), (ReturningFunction1<Boolean, T>) new ReturningFunction1<Boolean, Food>() {
            final Set<String> originalUidsAdded = new HashSet();

            {
                if (Strings.notEmpty(str)) {
                    this.originalUidsAdded.add(str);
                }
            }

            public Boolean execute(Food food) throws RuntimeException {
                boolean z;
                if (food.hasOriginalUid()) {
                    String originalUid = food.getOriginalUid();
                    if (!this.originalUidsAdded.contains(originalUid)) {
                        z = !food.isQuickAddOfAnySort();
                        this.originalUidsAdded.add(originalUid);
                        return Boolean.valueOf(z);
                    }
                }
                z = false;
                return Boolean.valueOf(z);
            }
        }))), (ReturningFunction2<Boolean, T, Integer>) new ReturningFunction2<Boolean, DiaryEntryCellModel, Integer>() {
            public Boolean execute(DiaryEntryCellModel diaryEntryCellModel, Integer num) throws RuntimeException {
                return Boolean.valueOf(num.intValue() < 10 && diaryEntryCellModel.isFoodEntry());
            }
        }));
    }
}
