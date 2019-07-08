package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.brightcove.player.event.EventType;
import com.myfitnesspal.feature.search.model.SortOrder;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.SqlOp;
import com.myfitnesspal.shared.db.table.FoodPermissionsTable;
import com.myfitnesspal.shared.db.table.ImageAssociationsTable;
import com.myfitnesspal.shared.db.table.ImagesTable;
import com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns;
import com.myfitnesspal.shared.model.FoodImages;
import com.myfitnesspal.shared.model.v1.DiaryEntryCellModel;
import com.myfitnesspal.shared.model.v1.Food;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v1.FoodPortion;
import com.myfitnesspal.shared.model.v1.MealFood;
import com.myfitnesspal.shared.model.v1.MealIngredient;
import com.myfitnesspal.shared.model.v1.NutritionalValues;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.myfitnesspal.shared.model.v2.MfpImage;
import com.myfitnesspal.shared.service.session.Session;
import com.myfitnesspal.shared.service.syncv2.ops.FindAndCorrectFoodsWithMissingInfoOp;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.ArrayUtil;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import com.uacf.core.util.Enumerable;
import com.uacf.core.util.Function0;
import com.uacf.core.util.Ln;
import com.uacf.core.util.ReturningFunction1;
import com.uacf.core.util.Strings;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;

public class FoodDBAdapter extends SessionDBAdapter {
    private static final String[] ALL_REQUIRED_FOOD_COLUMNS = {"id", "master_id", "original_food_id", "original_food_master_id", "owner_user_id", "original_uid", "uid", "owner_user_master_id", "food_type", "is_public", "deleted", "description", "brand", "food_info", "food_barcode", "uid", "original_uid", "food_verified", "food_info_version", "food_grams"};
    private static final String DATABASE_TABLE = "foods";
    public static final String KEY_BRAND = "brand";
    public static final String KEY_DELETED = "deleted";
    public static final String KEY_DESCRIPTION = "description";
    public static final String KEY_FOOD_BARCODE = "food_barcode";
    public static final String KEY_FOOD_GRAMS = "food_grams";
    public static final String KEY_FOOD_INFO = "food_info";
    public static final String KEY_FOOD_INFO_VERSION = "food_info_version";
    public static final String KEY_FOOD_TYPE = "food_type";
    public static final String KEY_FOOD_VERIFIED = "food_verified";
    public static final String KEY_ID = "id";
    public static final String KEY_IS_PUBLIC = "is_public";
    public static final String KEY_MASTER_ID = "master_id";
    public static final String KEY_ORIGINAL_FOOD_ID = "original_food_id";
    public static final String KEY_ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
    public static final String KEY_ORIGINAL_UID = "original_uid";
    public static final String KEY_OWNER_USER_ID = "owner_user_id";
    public static final String KEY_OWNER_USER_MASTER_ID = "owner_user_master_id";
    public static final String KEY_UID = "uid";
    private final Context context;
    /* access modifiers changed from: private */
    public final DbConnectionManager dbConnectionManager;
    private long[] foodOriginalId = {0};
    SQLiteStatement stmt;
    int updatesExecuted;

    public enum DBTaskResult {
        SUCCESS,
        FAILURE
    }

    public FoodDBAdapter(@Nonnull Context context2, @Nonnull DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public void insertFood(Food food, DbConnectionManager dbConnectionManager2) {
        insertFood(food, null, dbConnectionManager2);
    }

    public DBTaskResult insertFood(Food food, Food food2, DbConnectionManager dbConnectionManager2) {
        try {
            insertFoodUnsafe(food, food2, dbConnectionManager2);
            return DBTaskResult.SUCCESS;
        } catch (SQLiteException e) {
            Ln.e(e);
            return DBTaskResult.FAILURE;
        }
    }

    public void insertFoodUnsafe(Food food, Food food2, DbConnectionManager dbConnectionManager2) {
        if (food2 != null) {
            food2.updateOriginalFoodIdsIfNeeded(dbConnectionManager2);
            long originalId = food2.getOriginalId();
            long originalMasterId = food2.getOriginalMasterId();
            if (originalId <= 0) {
                originalId = food2.getLocalId();
            }
            food.setOriginalId(originalId);
            if (originalMasterId <= 0) {
                originalMasterId = food2.getMasterDatabaseId();
            }
            food.setOriginalMasterId(originalMasterId);
        }
        DatabaseUtil.ensureDatabaseTransaction(DbConnectionManager.getDb(this.context), new Function0(food, dbConnectionManager2, food2) {
            private final /* synthetic */ Food f$1;
            private final /* synthetic */ DbConnectionManager f$2;
            private final /* synthetic */ Food f$3;

            {
                this.f$1 = r2;
                this.f$2 = r3;
                this.f$3 = r4;
            }

            public final void execute() {
                FoodDBAdapter.lambda$insertFoodUnsafe$0(FoodDBAdapter.this, this.f$1, this.f$2, this.f$3);
            }
        });
    }

    public static /* synthetic */ void lambda$insertFoodUnsafe$0(FoodDBAdapter foodDBAdapter, Food food, DbConnectionManager dbConnectionManager2, Food food2) throws RuntimeException {
        foodDBAdapter.stmt = DbConnectionManager.preparedStatement(14);
        if (food.hasMasterDatabaseId()) {
            foodDBAdapter.stmt.bindLong(1, food.masterDatabaseId);
        } else {
            foodDBAdapter.stmt.bindNull(1);
        }
        if (food.originalId.longValue() > 0) {
            foodDBAdapter.stmt.bindLong(2, food.originalId.longValue());
        } else {
            foodDBAdapter.stmt.bindNull(2);
        }
        if (food.originalMasterId.longValue() > 0) {
            foodDBAdapter.stmt.bindLong(3, food.originalMasterId.longValue());
        } else {
            foodDBAdapter.stmt.bindNull(3);
        }
        if (food.ownerUserId > 0) {
            foodDBAdapter.stmt.bindLong(4, food.ownerUserId);
        } else {
            foodDBAdapter.stmt.bindNull(4);
        }
        if (food.ownerUserMasterId > 0) {
            foodDBAdapter.stmt.bindLong(5, food.ownerUserMasterId);
        } else {
            foodDBAdapter.stmt.bindNull(5);
        }
        foodDBAdapter.stmt.bindLong(6, (long) food.itemType());
        long j = 1;
        foodDBAdapter.stmt.bindLong(7, food.isDeleted ? 1 : 0);
        foodDBAdapter.stmt.bindLong(8, food.isPublic ? 1 : 0);
        foodDBAdapter.stmt.bindString(9, food.description);
        if (food.hasBrand()) {
            foodDBAdapter.stmt.bindString(10, food.getBrand());
        } else {
            foodDBAdapter.stmt.bindNull(10);
        }
        foodDBAdapter.stmt.bindLong(11, 0);
        if (Strings.notEmpty(food.getBarcode())) {
            foodDBAdapter.stmt.bindString(12, food.getBarcode());
        } else {
            foodDBAdapter.stmt.bindNull(12);
        }
        if (Strings.notEmpty(food.getUid())) {
            foodDBAdapter.stmt.bindString(13, food.getUid());
        } else {
            foodDBAdapter.stmt.bindNull(13);
        }
        if (Strings.notEmpty(food.getOriginalUid())) {
            foodDBAdapter.stmt.bindString(14, food.getOriginalUid());
        } else {
            foodDBAdapter.stmt.bindNull(14);
        }
        SQLiteStatement sQLiteStatement = foodDBAdapter.stmt;
        if (!food.isVerified()) {
            j = 0;
        }
        sQLiteStatement.bindLong(15, j);
        foodDBAdapter.stmt.bindDouble(16, (double) food.getGrams());
        if (food.getPromotedFromMasterId() > 0) {
            foodDBAdapter.stmt.bindLong(17, food.getPromotedFromMasterId());
        } else {
            foodDBAdapter.stmt.bindNull(17);
        }
        if (Strings.notEmpty(food.getPromotedFromUid())) {
            foodDBAdapter.stmt.bindString(18, food.getPromotedFromUid());
        } else {
            foodDBAdapter.stmt.bindNull(18);
        }
        long executeInsert = foodDBAdapter.stmt.executeInsert();
        foodDBAdapter.stmt.clearBindings();
        food.localId = executeInsert;
        food.updateOriginalFoodIdsIfNeededUnsafe(dbConnectionManager2);
        dbConnectionManager2.foodPortionsDBAdapter().insertFoodPortions(food.getFoodPortions(), food.getLocalId());
        dbConnectionManager2.nutritionalValuesDBAdapter().insertNutritionalValues(food.getNutritionalValues(), food.getLocalId());
        foodDBAdapter.updateFoodReferencesForUnsafe(food);
        if (food.isRecipe() && food.hasMasterDatabaseId()) {
            foodDBAdapter.updateRecipeBoxItemReferencesForRecipeFood((RecipeFood) food);
        }
        if (food2 != null) {
            foodDBAdapter.deleteFoodUnsafe(food2, false, true);
        }
    }

    public void updateRecipeBoxItemReferencesForRecipeFood(RecipeFood recipeFood) {
        this.dbConnectionManager.recipeBoxItemsDBAdapter().updateRecipeBoxItemReferencesForRecipeFood(recipeFood);
    }

    public void deleteFood(Food food, boolean z, boolean z2) {
        try {
            deleteFoodUnsafe(food, z, z2);
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    private void deleteFoodUnsafe(Food food, boolean z, boolean z2) {
        this.stmt = DbConnectionManager.preparedStatement(6);
        this.stmt.bindLong(1, z ? 1 : 0);
        this.stmt.bindLong(2, food.getLocalId());
        this.stmt.execute();
        this.stmt.clearBindings();
        food.setIsDeleted(true);
        if (food.hasMasterDatabaseId() && z2) {
            long localId = getSession().getUser().getLocalId();
            if (food.masterDatabaseId != food.getOriginalMasterId() || !z) {
                this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(localId, 1, food.masterDatabaseId, false);
            }
            if (z) {
                this.dbConnectionManager.deletedItemsDbAdapter().recordDeletedItemForUserId(localId, 1, food.getOriginalMasterId(), true);
                new FoodPermissionsTable(DbConnectionManager.getDb(this.context)).deletePermission(food.localId, ((Session) this.session.get()).getUser().getLocalId());
            }
        }
    }

    private void updateFoodReferencesForUnsafe(Food food) {
        if (food.hasMasterDatabaseId()) {
            this.stmt = DbConnectionManager.preparedStatement(18);
            this.stmt.bindLong(1, food.getOriginalId());
            this.stmt.bindLong(2, food.getMasterDatabaseId());
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
        }
        this.stmt = DbConnectionManager.preparedStatement(19);
        this.stmt.bindLong(1, food.getOriginalId());
        this.stmt.bindLong(2, food.getLocalId());
        this.stmt.execute();
        this.stmt.clearBindings();
        this.updatesExecuted++;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:10:0x005b, code lost:
        if (r7 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0068, code lost:
        if (r7 != null) goto L_0x006a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x006a, code lost:
        r7.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x007b, code lost:
        return com.uacf.core.util.Tuple.create(java.lang.Long.valueOf(0), java.lang.Long.valueOf(0), null, null);
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007f  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.core.util.Tuple4<java.lang.Long, java.lang.Long, java.lang.String, java.lang.String> lookupFoodMasterAndUidsFromLocalId(long r7) {
        /*
            r6 = this;
            r0 = 16
            r1 = 0
            java.lang.String r0 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r0)     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            r3 = 0
            java.lang.String r7 = java.lang.Long.toString(r7)     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            r2[r3] = r7     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            android.content.Context r7 = r6.context     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            com.uacf.core.database.SQLiteDatabaseWrapper r7 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r7)     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            android.database.Cursor r7 = r7.rawQuery(r0, r2)     // Catch:{ Exception -> 0x0063, all -> 0x0060 }
            boolean r8 = r7.moveToFirst()     // Catch:{ Exception -> 0x005e }
            if (r8 == 0) goto L_0x005b
            java.lang.String r8 = "master_id"
            int r8 = r7.getColumnIndex(r8)     // Catch:{ Exception -> 0x005e }
            long r2 = r7.getLong(r8)     // Catch:{ Exception -> 0x005e }
            java.lang.String r8 = "original_food_master_id"
            int r8 = r7.getColumnIndex(r8)     // Catch:{ Exception -> 0x005e }
            long r4 = r7.getLong(r8)     // Catch:{ Exception -> 0x005e }
            java.lang.String r8 = "uid"
            int r8 = r7.getColumnIndex(r8)     // Catch:{ Exception -> 0x005e }
            java.lang.String r8 = r7.getString(r8)     // Catch:{ Exception -> 0x005e }
            java.lang.String r0 = "original_uid"
            int r0 = r7.getColumnIndex(r0)     // Catch:{ Exception -> 0x005e }
            java.lang.String r0 = r7.getString(r0)     // Catch:{ Exception -> 0x005e }
            java.lang.Long r2 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x005e }
            java.lang.Long r3 = java.lang.Long.valueOf(r4)     // Catch:{ Exception -> 0x005e }
            com.uacf.core.util.Tuple4 r8 = com.uacf.core.util.Tuple.create(r2, r3, r8, r0)     // Catch:{ Exception -> 0x005e }
            if (r7 == 0) goto L_0x005a
            r7.close()
        L_0x005a:
            return r8
        L_0x005b:
            if (r7 == 0) goto L_0x006d
            goto L_0x006a
        L_0x005e:
            r8 = move-exception
            goto L_0x0065
        L_0x0060:
            r8 = move-exception
            r7 = r1
            goto L_0x007d
        L_0x0063:
            r8 = move-exception
            r7 = r1
        L_0x0065:
            com.uacf.core.util.Ln.e(r8)     // Catch:{ all -> 0x007c }
            if (r7 == 0) goto L_0x006d
        L_0x006a:
            r7.close()
        L_0x006d:
            r7 = 0
            java.lang.Long r0 = java.lang.Long.valueOf(r7)
            java.lang.Long r7 = java.lang.Long.valueOf(r7)
            com.uacf.core.util.Tuple4 r7 = com.uacf.core.util.Tuple.create(r0, r7, r1, r1)
            return r7
        L_0x007c:
            r8 = move-exception
        L_0x007d:
            if (r7 == 0) goto L_0x0082
            r7.close()
        L_0x0082:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.lookupFoodMasterAndUidsFromLocalId(long):com.uacf.core.util.Tuple4");
    }

    public long lookupFoodLocalIdFromMasterId(long j) {
        try {
            return lookupFoodLocalIdFromMasterId(j, null);
        } catch (Exception e) {
            Ln.e(e);
            return -1;
        }
    }

    public long lookupFoodLocalIdFromMasterId(long j, long[] jArr) {
        long j2 = 0;
        Cursor cursor = null;
        try {
            Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(2), new String[]{String.valueOf(j)});
            if (rawQuery.moveToFirst()) {
                j2 = rawQuery.getLong(0);
                if (jArr != null) {
                    jArr[0] = rawQuery.getLong(1);
                }
            }
            if (rawQuery != null) {
                rawQuery.close();
            }
            return j2;
        } catch (Exception e) {
            Ln.e(e);
            if (cursor != null) {
                cursor.close();
            }
            return -1;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public long lookupOriginalFoodIdFromMasterId(long j) {
        return CursorUtils.readLongAndClose(DbConnectionManager.getDb(this.context).query("foods", new String[]{"original_food_id"}, "master_id=?", new String[]{Strings.toString(Long.valueOf(j))}, null, null, null), 0);
    }

    public long lookupOriginalFoodIdFromLocalId(long j) {
        return CursorUtils.readLongAndClose(DbConnectionManager.getDb(this.context).query("foods", new String[]{"original_food_id"}, "id=?", new String[]{Strings.toString(Long.valueOf(j))}, null, null, null), j);
    }

    public void updateOriginalIdsForFoodId(long j, long j2, long j3, String str) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(17);
            this.stmt.bindLong(1, j2);
            this.stmt.bindLong(2, j3);
            if (Strings.notEmpty(str)) {
                this.stmt.bindString(3, str);
            } else {
                this.stmt.bindNull(3);
            }
            this.stmt.bindLong(4, j);
            this.stmt.execute();
            this.stmt.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* JADX INFO: finally extract failed */
    public void setMasterIdAndUidToFood(Food food) {
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("foods", new String[]{"master_id", "uid"}, "id=?", new String[]{Strings.toString(Long.valueOf(food.getLocalId()))}, null, null, null);
            if (query.moveToFirst()) {
                food.setMasterDatabaseId(query.getLong(query.getColumnIndex("master_id")));
                food.setUid(query.getString(query.getColumnIndex("uid")));
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

    private List<Food> readFoods(Cursor cursor) {
        CursorMapper cursorMapper = new CursorMapper(cursor);
        ArrayList<Food> arrayList = new ArrayList<>(cursorMapper.getCount());
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        while (cursorMapper.moveToNext()) {
            Food food = null;
            int i = cursorMapper.getInt("food_type");
            if (i == 1) {
                food = new Food();
            } else if (i == 3) {
                food = new MealFood();
            } else if (i == 11) {
                food = new RecipeFood();
            }
            if (food != null) {
                long j = cursorMapper.getLong("id");
                long j2 = cursorMapper.getLong("master_id");
                food.setLocalId(j);
                food.setMasterDatabaseId(j2);
                food.setOriginalId(cursorMapper.getLong("original_food_id"));
                food.setOriginalMasterId(cursorMapper.getLong("original_food_master_id"));
                food.setOwnerUserId(cursorMapper.getLong("owner_user_id"));
                food.setOwnerUserMasterId(cursorMapper.getLong("owner_user_master_id"));
                food.setIsPublic(cursorMapper.getBoolean("is_public").booleanValue());
                food.setIsDeleted(cursorMapper.getBoolean("deleted").booleanValue());
                food.setGrams(cursorMapper.getFloat("food_grams"));
                String string = cursorMapper.getString("description");
                if (Strings.isEmpty(string)) {
                    food.setDescription("Invalid Food");
                } else {
                    food.setDescription(string);
                }
                food.setBrand(cursorMapper.getString("brand"));
                food.setBarcode(cursorMapper.getString("food_barcode"));
                food.setUid(cursorMapper.getString("uid"));
                food.setOriginalUid(cursorMapper.getString("original_uid"));
                food.setVerified(cursorMapper.getBoolean("food_verified").booleanValue());
                arrayList.add(food);
                arrayList2.add(Long.valueOf(j));
                arrayList3.add(Long.valueOf(j2));
            }
        }
        ArrayList arrayList4 = new ArrayList();
        if (!arrayList2.isEmpty()) {
            Map foodIdToFoodPortionsMapForMultipleIds = this.dbConnectionManager.foodPortionsDBAdapter().getFoodIdToFoodPortionsMapForMultipleIds(arrayList2);
            Map foodIdToNutritionalValuesMapForMultipleIds = this.dbConnectionManager.nutritionalValuesDBAdapter().getFoodIdToNutritionalValuesMapForMultipleIds(arrayList2);
            Map foodIdToFoodPermissionMapForMultipleFoodIds = new FoodPermissionsTable(DbConnectionManager.getDb(this.context)).getFoodIdToFoodPermissionMapForMultipleFoodIds(arrayList2, arrayList3);
            for (Food food2 : arrayList) {
                long localId = food2.getLocalId();
                NutritionalValues nutritionalValues = (NutritionalValues) foodIdToNutritionalValuesMapForMultipleIds.get(Long.valueOf(localId));
                List list = (List) foodIdToFoodPortionsMapForMultipleIds.get(Long.valueOf(localId));
                if (nutritionalValues == null || list == null) {
                    logIncompleteInfoFood(food2);
                } else {
                    food2.setNutritionalValues(nutritionalValues);
                    food2.setFoodPortions((FoodPortion[]) list.toArray(new FoodPortion[0]));
                    FoodPermission foodPermission = (FoodPermission) foodIdToFoodPermissionMapForMultipleFoodIds.get(Long.valueOf(localId));
                    if (foodPermission == null) {
                        foodPermission = (FoodPermission) foodIdToFoodPermissionMapForMultipleFoodIds.get(Long.valueOf(food2.getMasterDatabaseId()));
                    }
                    food2.setFoodPermission(foodPermission);
                    arrayList4.add(food2);
                }
            }
        }
        return arrayList4;
    }

    private void logIncompleteInfoFood(Food food) {
        Exception exc = new Exception(String.format("Incomplete Food found. MasterId:%s OriginalMasterId:%s UID:%s OriginalUID:%s", new Object[]{Long.valueOf(food.getMasterDatabaseId()), Long.valueOf(food.getOriginalMasterId()), food.getUid(), food.getOriginalUid()}));
        exc.fillInStackTrace();
        Ln.e(exc);
    }

    private Food readSingleFood(Cursor cursor) {
        return (Food) Enumerable.firstOrDefault(readFoods(cursor));
    }

    private Food fetchSingleFood(Context context2, String str, String[] strArr) {
        return fetchSingleFood(context2, str, strArr, null, null);
    }

    /* JADX WARNING: Code restructure failed: missing block: B:17:0x002f, code lost:
        if (r10 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x0032, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x001c, code lost:
        if (r10 != null) goto L_0x001e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001e, code lost:
        r10.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:21:0x0036  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v1.Food fetchSingleFood(android.content.Context r10, java.lang.String r11, java.lang.String[] r12, java.lang.String r13, java.lang.String r14) {
        /*
            r9 = this;
            r0 = 0
            com.uacf.core.database.SQLiteDatabaseWrapper r1 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r10)     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            java.lang.String r2 = "foods"
            java.lang.String[] r3 = ALL_REQUIRED_FOOD_COLUMNS     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            r7 = 0
            r4 = r11
            r5 = r12
            r6 = r13
            r8 = r14
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ Exception -> 0x0025, all -> 0x0022 }
            if (r10 == 0) goto L_0x001c
            com.myfitnesspal.shared.model.v1.Food r11 = r9.readSingleFood(r10)     // Catch:{ Exception -> 0x001a }
            r0 = r11
            goto L_0x001c
        L_0x001a:
            r11 = move-exception
            goto L_0x0027
        L_0x001c:
            if (r10 == 0) goto L_0x0032
        L_0x001e:
            r10.close()
            goto L_0x0032
        L_0x0022:
            r11 = move-exception
            r10 = r0
            goto L_0x0034
        L_0x0025:
            r11 = move-exception
            r10 = r0
        L_0x0027:
            java.lang.String r12 = "FoodDBAdapter.fetchSingleFood"
            r13 = 0
            java.lang.Object[] r13 = new java.lang.Object[r13]     // Catch:{ all -> 0x0033 }
            com.uacf.core.util.Ln.e(r11, r12, r13)     // Catch:{ all -> 0x0033 }
            if (r10 == 0) goto L_0x0032
            goto L_0x001e
        L_0x0032:
            return r0
        L_0x0033:
            r11 = move-exception
        L_0x0034:
            if (r10 == 0) goto L_0x0039
            r10.close()
        L_0x0039:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.fetchSingleFood(android.content.Context, java.lang.String, java.lang.String[], java.lang.String, java.lang.String):com.myfitnesspal.shared.model.v1.Food");
    }

    public Food fetchLatestVersionOfFoodForOriginalId(long j) {
        return fetchSingleFood(this.context, "original_food_id=?", new String[]{Strings.toString(Long.valueOf(j))}, "original_food_id", "id DESC");
    }

    public Food fetchFoodById(long j) {
        return fetchSingleFood(this.context, "id= ? ", new String[]{Strings.toString(Long.valueOf(j))});
    }

    public Food fetchFoodByOriginalUid(String str) {
        return fetchSingleFood(this.context, "original_uid = ? ", new String[]{Strings.toString(str)});
    }

    public Food fetchFoodByUid(String str) {
        return fetchSingleFood(this.context, "uid = ? ", new String[]{Strings.toString(str)});
    }

    public Food insertFoodIfMissing(Food food, DbConnectionManager dbConnectionManager2) {
        try {
            return insertFoodIfMissingUnsafe(food, dbConnectionManager2);
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public Food insertFoodIfMissingUnsafe(Food food, DbConnectionManager dbConnectionManager2) {
        if (food.hasLocalId()) {
            return food;
        }
        this.foodOriginalId[0] = 0;
        long lookupFoodLocalIdFromMasterId = lookupFoodLocalIdFromMasterId(food.masterDatabaseId, this.foodOriginalId);
        if (lookupFoodLocalIdFromMasterId > 0) {
            food.localId = lookupFoodLocalIdFromMasterId;
            food.originalId = Long.valueOf(this.foodOriginalId[0]);
            return food;
        }
        insertFoodUnsafe(food, null, dbConnectionManager2);
        return food;
    }

    public FoodImages fetchImagesForFoodsOfType(int i) {
        String join = Strings.join(",", (T[]) new String[]{SqlOp.as(SqlOp.col("foods", "id"), "food_id"), SqlOp.as(SqlOp.col("foods", "uid"), "food_uid"), SqlOp.col(ImagesTable.TABLE_NAME, EventType.ANY)});
        String join2 = Strings.join(",", (T[]) new String[]{"foods", ImagesTable.TABLE_NAME, ImageAssociationsTable.TABLE_NAME});
        String join3 = Strings.join("AND", (T[]) new String[]{SqlOp.eq(SqlOp.col("foods", "food_type"), "?"), SqlOp.or(SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "image_id"), SqlOp.col(ImagesTable.TABLE_NAME, "id")), SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "image_uid"), SqlOp.col(ImagesTable.TABLE_NAME, "uid"))), SqlOp.or(SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "resource_id"), SqlOp.col("foods", "id")), SqlOp.eq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, "resource_uid"), SqlOp.col("foods", "uid"))), SqlOp.and(SqlOp.neq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, Columns.SYNC_FLAGS), String.valueOf(3)), SqlOp.neq(SqlOp.col(ImageAssociationsTable.TABLE_NAME, Columns.SYNC_FLAGS), String.valueOf(5)))});
        String[] strArr = {String.valueOf(i)};
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(join);
        sb.append(" FROM ");
        sb.append(join2);
        sb.append(" WHERE ");
        sb.append(join3);
        sb.append(";");
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(sb.toString(), strArr);
        FoodImages foodImages = new FoodImages();
        if (rawQuery != null) {
            try {
                CursorMapper cursorMapper = new CursorMapper(rawQuery);
                int columnIndexOrThrow = rawQuery.getColumnIndexOrThrow("food_id");
                int columnIndexOrThrow2 = rawQuery.getColumnIndexOrThrow("food_uid");
                while (rawQuery.moveToNext()) {
                    foodImages.add(rawQuery.getLong(columnIndexOrThrow), rawQuery.getString(columnIndexOrThrow2), new MfpImage(cursorMapper));
                }
            } finally {
                rawQuery.close();
            }
        }
        return foodImages;
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x008a  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x00a0  */
    /* JADX WARNING: Removed duplicated region for block: B:36:0x00a8  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.List<java.lang.Long> fetchOwnedFoodIdsOfType(int r6, @org.jetbrains.annotations.NotNull com.myfitnesspal.feature.search.model.SortOrder r7, int r8, int r9) {
        /*
            r5 = this;
            r0 = 0
            int[] r1 = com.myfitnesspal.shared.db.adapter.FoodDBAdapter.AnonymousClass3.$SwitchMap$com$myfitnesspal$feature$search$model$SortOrder     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            int r7 = r7.ordinal()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r7 = r1[r7]     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            switch(r7) {
                case 1: goto L_0x002f;
                case 2: goto L_0x0028;
                case 3: goto L_0x0021;
                case 4: goto L_0x001a;
                case 5: goto L_0x0013;
                default: goto L_0x000c;
            }     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
        L_0x000c:
            r7 = 128(0x80, float:1.794E-43)
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            goto L_0x0035
        L_0x0013:
            r7 = 67
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            goto L_0x0035
        L_0x001a:
            r7 = 127(0x7f, float:1.78E-43)
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            goto L_0x0035
        L_0x0021:
            r7 = 126(0x7e, float:1.77E-43)
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            goto L_0x0035
        L_0x0028:
            r7 = 66
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            goto L_0x0035
        L_0x002f:
            r7 = 65
            java.lang.String r7 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r7)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
        L_0x0035:
            r1 = 4
            java.lang.String[] r1 = new java.lang.String[r1]     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            com.myfitnesspal.shared.service.session.Session r2 = r5.getSession()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            com.myfitnesspal.shared.model.User r2 = r2.getUser()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            long r2 = r2.getLocalId()     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.lang.String r2 = java.lang.String.valueOf(r2)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r3 = 0
            r1[r3] = r2     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r2 = 1
            java.lang.String r6 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r1[r2] = r6     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r6 = 2
            java.lang.String r8 = java.lang.String.valueOf(r8)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r1[r6] = r8     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r6 = 3
            java.lang.String r8 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            r1[r6] = r8     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            android.content.Context r6 = r5.context     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            com.uacf.core.database.SQLiteDatabaseWrapper r6 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r6)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            android.database.Cursor r6 = r6.rawQuery(r7, r1)     // Catch:{ Exception -> 0x0099, all -> 0x0097 }
            java.util.ArrayList r7 = new java.util.ArrayList     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            r7.<init>()     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            if (r6 == 0) goto L_0x0088
            boolean r8 = r6.moveToFirst()     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            if (r8 == 0) goto L_0x0088
        L_0x0077:
            long r8 = r6.getLong(r3)     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            java.lang.Long r8 = java.lang.Long.valueOf(r8)     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            r7.add(r8)     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            boolean r8 = r6.moveToNext()     // Catch:{ Exception -> 0x0092, all -> 0x008e }
            if (r8 != 0) goto L_0x0077
        L_0x0088:
            if (r6 == 0) goto L_0x008d
            r6.close()
        L_0x008d:
            return r7
        L_0x008e:
            r7 = move-exception
            r0 = r6
            r6 = r7
            goto L_0x00a6
        L_0x0092:
            r7 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
            goto L_0x009b
        L_0x0097:
            r6 = move-exception
            goto L_0x00a6
        L_0x0099:
            r6 = move-exception
            r7 = r0
        L_0x009b:
            com.uacf.core.util.Ln.e(r6)     // Catch:{ all -> 0x00a4 }
            if (r7 == 0) goto L_0x00a3
            r7.close()
        L_0x00a3:
            return r0
        L_0x00a4:
            r6 = move-exception
            r0 = r7
        L_0x00a6:
            if (r0 == 0) goto L_0x00ab
            r0.close()
        L_0x00ab:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.fetchOwnedFoodIdsOfType(int, com.myfitnesspal.feature.search.model.SortOrder, int, int):java.util.List");
    }

    public <T extends DiaryEntryCellModel> ArrayList<T> fetchOwnedFoodsOfType(int i, SortOrder sortOrder, int i2, int i3) {
        try {
            List fetchOwnedFoodIdsOfType = fetchOwnedFoodIdsOfType(i, sortOrder, i2, i3);
            if (i == 3) {
                return (ArrayList) getMealFoodsForLocalIds(fetchOwnedFoodIdsOfType, sortOrder);
            }
            return (ArrayList) Enumerable.select((Collection<T>) fetchOwnedFoodIdsOfType, (ReturningFunction1<U, T>) new ReturningFunction1() {
                public final Object execute(Object obj) {
                    return FoodDBAdapter.this.fetchFoodById(((Long) obj).longValue());
                }
            });
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    public void updateFoodsOwnerUserIdForOwnerUserMasterId(long j, long j2) {
        String str = "owner_user_master_id = ?";
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("owner_user_id", Long.valueOf(j));
            DbConnectionManager.getDb(this.context).update("foods", contentValues, str, new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void purgeFoodReferencesForFoodId(long j) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(56);
            this.stmt.bindLong(1, j);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
            this.stmt = DbConnectionManager.preparedStatement(57);
            this.stmt.bindLong(1, j);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
        } catch (SQLException e) {
            Ln.e(e);
        }
    }

    public void markFoodLocalIdAsDeleted(long j) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(6);
            this.stmt.bindLong(1, 0);
            this.stmt.bindLong(2, j);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public void updateFoodTypeToRecipeIfNeeded(long j, Context context2) {
        boolean z = true;
        String[] strArr = {String.valueOf(j)};
        Cursor rawQuery = DbConnectionManager.getDb(context2).rawQuery("select food_type from foods where id = ?", strArr);
        try {
            if (!rawQuery.moveToFirst() || rawQuery.getInt(0) == 11) {
                z = false;
            }
            if (z) {
                updateFoodTypeForLocalId(j, 11);
            }
        } finally {
            rawQuery.close();
        }
    }

    public void updateFoodTypeForLocalId(long j, int i) {
        this.stmt = DbConnectionManager.preparedStatement(82);
        this.stmt.bindLong(1, (long) i);
        this.stmt.bindLong(2, j);
        this.stmt.execute();
        this.stmt.clearBindings();
        this.updatesExecuted++;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:12:0x004b, code lost:
        if (r11 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0058, code lost:
        if (r11 != null) goto L_0x005a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        r11.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005d, code lost:
        return null;
     */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x0061  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.MealFood getExistingMealFoodWithDescription(java.lang.String r11, long r12) {
        /*
            r10 = this;
            r0 = 4
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r12 = java.lang.String.valueOf(r12)
            r13 = 0
            r0[r13] = r12
            java.lang.String r12 = "0"
            r13 = 1
            r0[r13] = r12
            r12 = 3
            java.lang.String r13 = java.lang.String.valueOf(r12)
            r1 = 2
            r0[r1] = r13
            java.lang.String r11 = com.uacf.core.util.Strings.toString(r11)
            r0[r12] = r11
            java.lang.String r2 = "foods"
            java.lang.String[] r3 = ALL_REQUIRED_FOOD_COLUMNS
            java.lang.String r4 = "owner_user_id = ? AND deleted = ? AND food_type = ? AND description = ?"
            r1 = 0
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            java.lang.String r11 = android.database.sqlite.SQLiteQueryBuilder.buildQueryString(r1, r2, r3, r4, r5, r6, r7, r8)
            r12 = 0
            android.content.Context r13 = r10.context     // Catch:{ SQLException -> 0x0053, all -> 0x004e }
            com.uacf.core.database.SQLiteDatabaseWrapper r13 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r13)     // Catch:{ SQLException -> 0x0053, all -> 0x004e }
            android.database.Cursor r11 = r13.rawQuery(r11, r0)     // Catch:{ SQLException -> 0x0053, all -> 0x004e }
            if (r11 == 0) goto L_0x004b
            com.myfitnesspal.shared.model.v1.Food r13 = r10.readSingleFood(r11)     // Catch:{ SQLException -> 0x0049 }
            boolean r0 = r13 instanceof com.myfitnesspal.shared.model.v1.MealFood     // Catch:{ SQLException -> 0x0049 }
            if (r0 == 0) goto L_0x004b
            com.myfitnesspal.shared.model.v1.MealFood r13 = (com.myfitnesspal.shared.model.v1.MealFood) r13     // Catch:{ SQLException -> 0x0049 }
            if (r11 == 0) goto L_0x0048
            r11.close()
        L_0x0048:
            return r13
        L_0x0049:
            r13 = move-exception
            goto L_0x0055
        L_0x004b:
            if (r11 == 0) goto L_0x005d
            goto L_0x005a
        L_0x004e:
            r11 = move-exception
            r9 = r12
            r12 = r11
            r11 = r9
            goto L_0x005f
        L_0x0053:
            r13 = move-exception
            r11 = r12
        L_0x0055:
            com.uacf.core.util.Ln.e(r13)     // Catch:{ all -> 0x005e }
            if (r11 == 0) goto L_0x005d
        L_0x005a:
            r11.close()
        L_0x005d:
            return r12
        L_0x005e:
            r12 = move-exception
        L_0x005f:
            if (r11 == 0) goto L_0x0064
            r11.close()
        L_0x0064:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.getExistingMealFoodWithDescription(java.lang.String, long):com.myfitnesspal.shared.model.v1.MealFood");
    }

    /* JADX WARNING: Removed duplicated region for block: B:77:0x0144  */
    /* JADX WARNING: Removed duplicated region for block: B:79:0x0149  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0150  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0155  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public boolean checkForExistingFoodWithDescription(java.lang.String r7, java.lang.String r8, java.lang.Boolean r9, java.lang.Boolean r10, int r11, long r12, dagger.Lazy<com.myfitnesspal.feature.settings.model.AppSettings> r14) {
        /*
            r6 = this;
            r0 = 0
            r1 = 0
            java.lang.String r2 = ""
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r3.<init>()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r4 = "select count(*) from foods where"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            boolean r4 = r10.booleanValue()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            if (r4 == 0) goto L_0x001a
            java.lang.String r4 = "(is_public = 1 or owner_user_id = ?)"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            goto L_0x001f
        L_0x001a:
            java.lang.String r4 = "owner_user_id = ?"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
        L_0x001f:
            java.lang.String r4 = "and deleted = 0 and food_type = ? and description = ?"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            boolean r4 = r9.booleanValue()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            if (r4 == 0) goto L_0x0037
            if (r8 == 0) goto L_0x0032
            java.lang.String r4 = "and brand = ?"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            goto L_0x0037
        L_0x0032:
            java.lang.String r4 = "and brand is null"
            r3.add(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
        L_0x0037:
            java.util.Iterator r3 = r3.iterator()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
        L_0x003b:
            boolean r4 = r3.hasNext()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            if (r4 == 0) goto L_0x005c
            java.lang.Object r4 = r3.next()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r4 = (java.lang.String) r4     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.StringBuilder r5 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r5.<init>()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r5.append(r2)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r5.append(r4)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r2 = " "
            r5.append(r2)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r2 = r5.toString()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            goto L_0x003b
        L_0x005c:
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r3.<init>()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r12 = java.lang.String.valueOf(r12)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r3.add(r12)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String r12 = java.lang.String.valueOf(r11)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r3.add(r12)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            r3.add(r7)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            boolean r12 = r9.booleanValue()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            if (r12 == 0) goto L_0x007d
            if (r8 == 0) goto L_0x007d
            r3.add(r8)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
        L_0x007d:
            android.content.Context r12 = r6.context     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            com.uacf.core.database.SQLiteDatabaseWrapper r12 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r12)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            int r13 = r3.size()     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String[] r13 = new java.lang.String[r13]     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.Object[] r13 = r3.toArray(r13)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            java.lang.String[] r13 = (java.lang.String[]) r13     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            android.database.Cursor r12 = r12.rawQuery(r2, r13)     // Catch:{ Exception -> 0x013d, all -> 0x013a }
            boolean r13 = r12.moveToFirst()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r2 = 1
            if (r13 == 0) goto L_0x00a4
            int r13 = r12.getInt(r1)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r13 <= 0) goto L_0x00a2
            r13 = 1
            goto L_0x00a5
        L_0x00a2:
            r13 = 0
            goto L_0x00a5
        L_0x00a4:
            r13 = 0
        L_0x00a5:
            if (r13 == 0) goto L_0x00ad
            if (r12 == 0) goto L_0x00ac
            r12.close()
        L_0x00ac:
            return r2
        L_0x00ad:
            boolean r10 = r10.booleanValue()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r10 != 0) goto L_0x00b9
            if (r12 == 0) goto L_0x00b8
            r12.close()
        L_0x00b8:
            return r1
        L_0x00b9:
            boolean r10 = r9.booleanValue()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r10 == 0) goto L_0x00c7
            if (r8 == 0) goto L_0x00c4
            java.lang.String r10 = " and brand = ?"
            goto L_0x00c9
        L_0x00c4:
            java.lang.String r10 = " and brand is null"
            goto L_0x00c9
        L_0x00c7:
            java.lang.String r10 = ""
        L_0x00c9:
            java.lang.StringBuilder r3 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r3.<init>()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.String r4 = "select count(*) from stock_foods where food_type = "
            r3.append(r4)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r3.append(r11)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.String r11 = " and description = ?"
            r3.append(r11)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r3.append(r10)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.String r10 = r3.toString()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r11.<init>()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            r11.add(r7)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            boolean r7 = r9.booleanValue()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r7 == 0) goto L_0x00f5
            if (r8 == 0) goto L_0x00f5
            r11.add(r8)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
        L_0x00f5:
            android.content.Context r7 = r6.context     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            com.uacf.core.database.SQLiteDatabaseWrapper r7 = com.myfitnesspal.shared.db.DbConnectionManager.getStockDb(r7, r14)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            int r8 = r11.size()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.String[] r8 = new java.lang.String[r8]     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.Object[] r8 = r11.toArray(r8)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            java.lang.String[] r8 = (java.lang.String[]) r8     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            android.database.Cursor r0 = r7.rawQuery(r10, r8)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            boolean r7 = r0.moveToFirst()     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r7 == 0) goto L_0x011a
            int r7 = r0.getInt(r1)     // Catch:{ Exception -> 0x0136, all -> 0x0132 }
            if (r7 <= 0) goto L_0x0119
            r13 = 1
            goto L_0x011a
        L_0x0119:
            r13 = 0
        L_0x011a:
            if (r13 == 0) goto L_0x0127
            if (r12 == 0) goto L_0x0121
            r12.close()
        L_0x0121:
            if (r0 == 0) goto L_0x0126
            r0.close()
        L_0x0126:
            return r2
        L_0x0127:
            if (r12 == 0) goto L_0x012c
            r12.close()
        L_0x012c:
            if (r0 == 0) goto L_0x0131
            r0.close()
        L_0x0131:
            return r1
        L_0x0132:
            r7 = move-exception
            r8 = r0
            r0 = r12
            goto L_0x014e
        L_0x0136:
            r7 = move-exception
            r8 = r0
            r0 = r12
            goto L_0x013f
        L_0x013a:
            r7 = move-exception
            r8 = r0
            goto L_0x014e
        L_0x013d:
            r7 = move-exception
            r8 = r0
        L_0x013f:
            com.uacf.core.util.Ln.e(r7)     // Catch:{ all -> 0x014d }
            if (r0 == 0) goto L_0x0147
            r0.close()
        L_0x0147:
            if (r8 == 0) goto L_0x014c
            r8.close()
        L_0x014c:
            return r1
        L_0x014d:
            r7 = move-exception
        L_0x014e:
            if (r0 == 0) goto L_0x0153
            r0.close()
        L_0x0153:
            if (r8 == 0) goto L_0x0158
            r8.close()
        L_0x0158:
            throw r7
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.checkForExistingFoodWithDescription(java.lang.String, java.lang.String, java.lang.Boolean, java.lang.Boolean, int, long, dagger.Lazy):boolean");
    }

    public Map<Long, Food> getFoodIdToFoodMapForMultipleIds(List<Long> list) {
        List<Food> fetchFoodsByIds = fetchFoodsByIds(list);
        if (CollectionUtils.isEmpty((Collection<?>) fetchFoodsByIds)) {
            return null;
        }
        HashMap hashMap = new HashMap();
        for (Food food : fetchFoodsByIds) {
            hashMap.put(Long.valueOf(food.getLocalId()), food);
        }
        return hashMap;
    }

    public List<Food> fetchFoodsByIds(List<Long> list) {
        return fetchFoodsByIdsOfTypeWithOrdering(list, "id", null);
    }

    public List<Food> fetchFoodsByIdsWithOrdering(List<Long> list, String str) {
        return fetchFoodsByIdsOfTypeWithOrdering(list, "id", str);
    }

    public List<Food> fetchFoodsByOriginalIds(List<Long> list) {
        return fetchFoodsByIdsOfTypeWithOrdering(list, "original_food_id", null);
    }

    private List<Food> fetchFoodsByIdsOfTypeWithOrdering(List<Long> list, String str, String str2) {
        if (CollectionUtils.isEmpty((Collection<?>) list)) {
            return new ArrayList();
        }
        Cursor cursor = null;
        try {
            String argsForList = DatabaseUtil.getArgsForList(list);
            String[] strArr = ALL_REQUIRED_FOOD_COLUMNS;
            StringBuilder sb = new StringBuilder();
            sb.append(str);
            sb.append(" IN ");
            sb.append(argsForList);
            cursor = DbConnectionManager.getDb(this.context).query("foods", strArr, sb.toString(), null, null, null, str2);
            return readFoods(cursor);
        } finally {
            if (cursor != null) {
                cursor.close();
            }
        }
    }

    /* JADX INFO: finally extract failed */
    public void updateFoodInfo(Food food) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("food_grams", Float.valueOf(food.getGrams()));
        SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
        String str = "uid=? AND original_uid=?";
        String[] strArr = {food.getUid(), food.getOriginalUid()};
        db.update("foods", contentValues, str, strArr);
        Cursor cursor = null;
        try {
            Cursor query = db.query("foods", new String[]{"id"}, str, strArr, null, null, null);
            int columnIndex = query.getColumnIndex("id");
            while (query.moveToNext()) {
                long j = query.getLong(columnIndex);
                this.dbConnectionManager.nutritionalValuesDBAdapter().insertOrReplaceNutritionalValues(food.getNutritionalValues(), j);
                this.dbConnectionManager.foodPortionsDBAdapter().insertOrReplaceFoodPortions(food.getFoodPortions(), j);
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

    public MealFood getMealFoodForMasterId(long j) {
        MealFood mealFood = (MealFood) fetchSingleFood(this.context, "master_id= ? ", new String[]{Strings.toString(Long.valueOf(j))});
        setMealIngredientsForMealFood(mealFood);
        return mealFood;
    }

    public MealFood getMealFoodForLocalId(long j) {
        MealFood mealFood = (MealFood) fetchFoodById(j);
        setMealIngredientsForMealFood(mealFood);
        return mealFood;
    }

    public MealFood getMealFoodForUid(String str) {
        MealFood mealFood = (MealFood) fetchFoodByUid(str);
        setMealIngredientsForMealFood(mealFood);
        return mealFood;
    }

    private void setMealIngredientsForMealFood(MealFood mealFood) {
        if (mealFood != null) {
            mealFood.setIngredients(this.dbConnectionManager.mealIngredientsDbAdapter().mealIngredientsForMealFood(mealFood));
        }
    }

    public List<MealFood> getMealFoodsForLocalIds(List<Long> list) {
        return getMealFoodsForLocalIds(list, SortOrder.NONE);
    }

    public List<MealFood> getMealFoodsForLocalIds(List<Long> list, SortOrder sortOrder) {
        String str;
        int i = AnonymousClass3.$SwitchMap$com$myfitnesspal$feature$search$model$SortOrder[sortOrder.ordinal()];
        if (i != 4) {
            switch (i) {
                case 1:
                    str = "description asc";
                    break;
                case 2:
                    str = "description desc";
                    break;
                default:
                    str = null;
                    break;
            }
        } else {
            str = "id desc";
        }
        List fetchFoodsByIdsWithOrdering = fetchFoodsByIdsWithOrdering(list, str);
        if (CollectionUtils.isEmpty((Collection<?>) fetchFoodsByIdsWithOrdering)) {
            return new ArrayList();
        }
        final HashMap hashMap = new HashMap();
        List<MealFood> select = Enumerable.select((Collection<T>) fetchFoodsByIdsWithOrdering, (ReturningFunction1<U, T>) new ReturningFunction1<MealFood, Food>() {
            public MealFood execute(Food food) throws RuntimeException {
                MealFood mealFood = (MealFood) food;
                hashMap.put(Long.valueOf(food.getLocalId()), mealFood);
                return mealFood;
            }
        });
        for (MealIngredient mealIngredient : this.dbConnectionManager.mealIngredientsDbAdapter().mealIngredientsForMealFoods(select)) {
            MealFood mealFood = (MealFood) hashMap.get(Long.valueOf(mealIngredient.getMealFoodId()));
            if (mealFood != null) {
                mealFood.addIngredient(mealIngredient);
            }
        }
        return select;
    }

    /* JADX WARNING: Removed duplicated region for block: B:29:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.uacf.core.util.Tuple2<java.lang.Boolean, java.util.Set<java.lang.String>> migrateFoodInfoBlobData() {
        /*
            r21 = this;
            r11 = r21
            android.content.Context r0 = r11.context
            com.uacf.core.database.SQLiteDatabaseWrapper r12 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)
            java.util.HashSet r13 = new java.util.HashSet
            r13.<init>()
            r9 = 0
            java.lang.String r2 = "foods"
            java.lang.String r0 = "id"
            java.lang.String r1 = "food_info"
            java.lang.String r3 = "food_info_version"
            java.lang.String r4 = "uid"
            java.lang.String r5 = "original_uid"
            java.lang.String[] r3 = new java.lang.String[]{r0, r1, r3, r4, r5}     // Catch:{ all -> 0x00ab }
            java.lang.String r4 = "id NOT IN (SELECT food_id FROM nutritional_values) AND uid NOT NULL AND original_uid NOT NULL"
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            r1 = r12
            android.database.Cursor r14 = r1.query(r2, r3, r4, r5, r6, r7, r8)     // Catch:{ all -> 0x00ab }
            java.lang.String r0 = "id"
            int r15 = r14.getColumnIndex(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = "uid"
            int r16 = r14.getColumnIndex(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = "original_uid"
            int r17 = r14.getColumnIndex(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = "food_info"
            int r18 = r14.getColumnIndex(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = "food_info_version"
            int r19 = r14.getColumnIndex(r0)     // Catch:{ all -> 0x00a9 }
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ all -> 0x00a9 }
            r0.<init>()     // Catch:{ all -> 0x00a9 }
            java.lang.String r1 = "Number of foods to migrate: "
            r0.append(r1)     // Catch:{ all -> 0x00a9 }
            int r1 = r14.getCount()     // Catch:{ all -> 0x00a9 }
            r0.append(r1)     // Catch:{ all -> 0x00a9 }
            java.lang.String r0 = r0.toString()     // Catch:{ all -> 0x00a9 }
            r10 = 0
            java.lang.Object[] r1 = new java.lang.Object[r10]     // Catch:{ all -> 0x00a9 }
            com.uacf.core.util.Ln.d(r0, r1)     // Catch:{ all -> 0x00a9 }
            r0 = 1
        L_0x0063:
            boolean r1 = r14.moveToNext()     // Catch:{ all -> 0x00a9 }
            if (r1 == 0) goto L_0x009b
            com.myfitnesspal.shared.db.adapter.FoodDBAdapter$2 r9 = new com.myfitnesspal.shared.db.adapter.FoodDBAdapter$2     // Catch:{ Exception -> 0x008c }
            r1 = r9
            r2 = r21
            r3 = r14
            r4 = r15
            r5 = r16
            r6 = r17
            r7 = r18
            r8 = r19
            r11 = r9
            r9 = r13
            r20 = r15
            r15 = 0
            r10 = r12
            r1.<init>(r3, r4, r5, r6, r7, r8, r9, r10)     // Catch:{ Exception -> 0x008a }
            com.uacf.core.database.DatabaseUtil.ensureDatabaseTransaction(r12, r11)     // Catch:{ Exception -> 0x008a }
            r15 = r20
        L_0x0086:
            r10 = 0
            r11 = r21
            goto L_0x0063
        L_0x008a:
            r0 = move-exception
            goto L_0x0090
        L_0x008c:
            r0 = move-exception
            r20 = r15
            r15 = 0
        L_0x0090:
            java.lang.String r1 = "Error while migrating a food"
            java.lang.Object[] r2 = new java.lang.Object[r15]     // Catch:{ all -> 0x00a9 }
            com.uacf.core.util.Ln.e(r0, r1, r2)     // Catch:{ all -> 0x00a9 }
            r15 = r20
            r0 = 0
            goto L_0x0086
        L_0x009b:
            java.lang.Boolean r0 = java.lang.Boolean.valueOf(r0)     // Catch:{ all -> 0x00a9 }
            com.uacf.core.util.Tuple2 r0 = com.uacf.core.util.Tuple2.create(r0, r13)     // Catch:{ all -> 0x00a9 }
            if (r14 == 0) goto L_0x00a8
            r14.close()
        L_0x00a8:
            return r0
        L_0x00a9:
            r0 = move-exception
            goto L_0x00ad
        L_0x00ab:
            r0 = move-exception
            r14 = r9
        L_0x00ad:
            if (r14 == 0) goto L_0x00b2
            r14.close()
        L_0x00b2:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.FoodDBAdapter.migrateFoodInfoBlobData():com.uacf.core.util.Tuple2");
    }

    /* JADX INFO: finally extract failed */
    public Set<String> getUidAndVersionOfFoodsWithoutFoodPortionsOrNutritionInfo() {
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("foods", new String[]{"uid", "original_uid"}, "(id NOT IN (SELECT food_id FROM food_portions) OR id NOT IN (SELECT food_id FROM nutritional_values)) AND uid NOT NULL AND original_uid NOT NULL", null, null, null, null);
            HashSet hashSet = new HashSet();
            int columnIndex = query.getColumnIndex("uid");
            int columnIndex2 = query.getColumnIndex("original_uid");
            while (query.moveToNext()) {
                hashSet.add(String.format(FindAndCorrectFoodsWithMissingInfoOp.FOOD_IDS_FORMAT, new Object[]{query.getString(columnIndex2), query.getString(columnIndex)}));
            }
            if (query != null) {
                query.close();
            }
            return hashSet;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    /* JADX INFO: finally extract failed */
    public long getFoodIdForOriginalUidAndUid(String str, String str2) {
        Cursor cursor = null;
        try {
            Cursor query = DbConnectionManager.getDb(this.context).query("foods", new String[]{"id"}, "uid=? AND original_uid=?", new String[]{str2, str}, null, null, null);
            if (query.moveToFirst()) {
                long j = CursorUtils.getLong(query, "id");
                if (query != null) {
                    query.close();
                }
                return j;
            }
            if (query != null) {
                query.close();
            }
            return 0;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public int countFoodsOfType(long j, int i) {
        SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
        StringBuilder sb = new StringBuilder();
        sb.append("select count(*) from foods where owner_user_master_id=");
        sb.append(j);
        sb.append(" and ");
        sb.append("deleted");
        sb.append("=0 and ");
        sb.append("food_type");
        sb.append(" =");
        sb.append(i);
        return CursorUtils.readIntAndClose(db.rawQuery(sb.toString(), null), 0);
    }

    /* access modifiers changed from: private */
    public boolean isFoodMissingNutritionalMultiplier(Food food) {
        FoodPortion[] foodPortions = food.getFoodPortions();
        if (Float.compare(food.getGrams(), 1.0f) != 0 || ArrayUtil.size(foodPortions) <= 1) {
            return false;
        }
        boolean z = true;
        for (FoodPortion foodPortion : foodPortions) {
            if (Double.compare(foodPortion.getNutritionMultiplier().doubleValue(), FoodPortion.DEFAULT_NUTRITION_MULTIPLIER.doubleValue()) != 0 || Float.compare(foodPortion.getGramWeight(), 1.0f) != 0) {
                z = false;
            }
        }
        return z;
    }

    /* JADX INFO: finally extract failed */
    public void correctOriginalIdOfFoods() {
        Cursor cursor = null;
        try {
            SQLiteDatabaseWrapper db = DbConnectionManager.getDb(this.context);
            Cursor query = db.query("foods", new String[]{"id", "original_food_id"}, "id != original_food_id", null, null, null, null);
            int columnIndex = query.getColumnIndex("id");
            int columnIndex2 = query.getColumnIndex("original_food_id");
            while (query.moveToNext()) {
                long j = query.getLong(columnIndex);
                long j2 = query.getLong(columnIndex2);
                if (j2 != 0) {
                    Food firstVersionOfFoodForOriginalId = getFirstVersionOfFoodForOriginalId(j2);
                    if (firstVersionOfFoodForOriginalId != null) {
                        ContentValues contentValues = new ContentValues();
                        contentValues.put("original_food_id", Long.valueOf(firstVersionOfFoodForOriginalId.getLocalId()));
                        contentValues.put("original_food_master_id", Long.valueOf(firstVersionOfFoodForOriginalId.getMasterDatabaseId()));
                        db.update("foods", contentValues, "id=?", new String[]{Strings.toString(Long.valueOf(j))});
                    }
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

    private Food getFirstVersionOfFoodForOriginalId(long j) {
        Food fetchFoodById = fetchFoodById(j);
        if (fetchFoodById == null) {
            return null;
        }
        long originalId = fetchFoodById.getOriginalId();
        while (originalId != fetchFoodById.getLocalId() && originalId != 0) {
            fetchFoodById = fetchFoodById(fetchFoodById.getOriginalId());
            if (fetchFoodById == null) {
                return null;
            }
            originalId = fetchFoodById.getOriginalId();
        }
        return fetchFoodById;
    }
}
