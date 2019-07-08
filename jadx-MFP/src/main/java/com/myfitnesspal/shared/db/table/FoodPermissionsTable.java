package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import android.database.Cursor;
import com.myfitnesspal.shared.model.v1.FoodPermission;
import com.myfitnesspal.shared.model.v2.MfpFood;
import com.uacf.core.database.CursorMapper;
import com.uacf.core.database.DatabaseUtil;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.CursorUtils;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Inject;

public class FoodPermissionsTable extends MfpDatabaseTableV2Impl {
    private static final String[] ALL_REQUIRED_FOOD_PERMISSIONS_COLUMNS = {"id", "master_id", "food_local_id", "food_master_id", "food_uid", "original_food_master_id", "original_food_uid", "user_id", "permissions"};
    private static final String IDX_FOOD_LOCAL_ID = "food_permissions_food_local_id_index";
    private static final String IDX_FOOD_MASTER_ID = "food_permissions_food_master_id_index";
    private static final String IDX_FOOD_UID = "food_permissions_food_uid_index";
    private static final String IDX_MASTER_ID = "food_permissions_master_id_index";
    public static final String TABLE_NAME = "food_permissions";

    public static final class Columns extends com.myfitnesspal.shared.db.table.MfpDatabaseTableV2.Columns {
        public static final String FOOD_LOCAL_ID = "food_local_id";
        public static final String FOOD_MASTER_ID = "food_master_id";
        public static final String FOOD_UID = "food_uid";
        public static final String ORIGINAL_FOOD_MASTER_ID = "original_food_master_id";
        public static final String ORIGINAL_FOOD_UID = "original_food_uid";
        public static final String PERMISSIONS = "permissions";
        public static final String USER_ID = "user_id";
    }

    public void onCreate() {
    }

    @Inject
    public FoodPermissionsTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "food_permissions");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(44, i, i2)) {
            createTableAndIndex();
        }
    }

    private void createTableAndIndex() {
        createTable("id INTEGER PRIMARY KEY AUTOINCREMENT", "master_id INTEGER UNIQUE", "food_local_id INTEGER", "food_master_id INTEGER", "food_uid TEXT", "original_food_master_id INTEGER", "original_food_uid TEXT", "user_id INTEGER NOT NULL", "permissions INTEGER");
        createIndex(IDX_MASTER_ID, "master_id");
        createIndex(IDX_FOOD_LOCAL_ID, "food_local_id");
        createIndex(IDX_FOOD_MASTER_ID, "food_master_id");
        createIndex(IDX_FOOD_UID, "food_uid");
    }

    public long save(FoodPermission foodPermission) {
        ContentValues contentValues = new ContentValues();
        Long l = null;
        contentValues.put("master_id", foodPermission.getMasterId() == 0 ? null : Long.valueOf(foodPermission.getMasterId()));
        contentValues.put("food_local_id", Long.valueOf(foodPermission.getFoodLocalId()));
        contentValues.put("food_master_id", foodPermission.getFoodMasterId() == 0 ? null : Long.valueOf(foodPermission.getFoodMasterId()));
        contentValues.put("food_uid", foodPermission.getFoodUid());
        String str = "original_food_master_id";
        if (foodPermission.getOriginalFoodMasterId() != 0) {
            l = Long.valueOf(foodPermission.getOriginalFoodMasterId());
        }
        contentValues.put(str, l);
        contentValues.put("original_food_uid", foodPermission.getOriginalFoodUid());
        contentValues.put("user_id", Long.valueOf(foodPermission.getUserId()));
        contentValues.put("permissions", Long.valueOf(foodPermission.getPermissionValue()));
        return insertData(contentValues);
    }

    public long updatePermissionFromServer(FoodPermission foodPermission) {
        ContentValues contentValues = new ContentValues();
        Long l = null;
        contentValues.put("master_id", foodPermission.getMasterId() == 0 ? null : Long.valueOf(foodPermission.getMasterId()));
        contentValues.put("food_master_id", foodPermission.getFoodMasterId() == 0 ? null : Long.valueOf(foodPermission.getFoodMasterId()));
        contentValues.put("food_uid", foodPermission.getFoodUid());
        String str = "original_food_master_id";
        if (foodPermission.getOriginalFoodMasterId() != 0) {
            l = Long.valueOf(foodPermission.getOriginalFoodMasterId());
        }
        contentValues.put(str, l);
        contentValues.put("original_food_uid", foodPermission.getOriginalFoodUid());
        contentValues.put("user_id", Long.valueOf(foodPermission.getUserId()));
        contentValues.put("permissions", Long.valueOf(foodPermission.getPermissionValue()));
        return insertOrUpdateData(contentValues, "master_id = ?", Long.valueOf(foodPermission.getMasterId()));
    }

    public FoodPermission fetchByLocalId(long j) {
        return fetchByHelper("id = ?", Long.valueOf(j));
    }

    public FoodPermission fetchByMasterId(long j) {
        return fetchByHelper("master_id = ?", Long.valueOf(j));
    }

    public FoodPermission fetchByFoodLocalId(long j) {
        return fetchByHelper("food_local_id = ?", Long.valueOf(j));
    }

    public FoodPermission fetchByFoodMasterId(long j) {
        return fetchByHelper("food_master_id = ?", Long.valueOf(j));
    }

    public FoodPermission fetchByFoodUid(String str) {
        return fetchByHelper("food_uid = ?", str);
    }

    public FoodPermission fetchByFoodLocalOrMasterId(long j, long j2) {
        return fetchByHelper("food_local_id=? OR food_master_id=?", Long.valueOf(j), Long.valueOf(j2));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:18:0x002d, code lost:
        if (r4 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0030, code lost:
        return r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:8:0x0018, code lost:
        if (r4 != null) goto L_0x001a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:9:0x001a, code lost:
        r4.close();
     */
    /* JADX WARNING: Removed duplicated region for block: B:22:0x0034  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private com.myfitnesspal.shared.model.v1.FoodPermission fetchByHelper(java.lang.String r4, java.lang.Object... r5) {
        /*
            r3 = this;
            r0 = 0
            java.lang.String[] r1 = ALL_REQUIRED_FOOD_PERMISSIONS_COLUMNS     // Catch:{ Exception -> 0x0023, all -> 0x0020 }
            android.database.Cursor r4 = r3.queryData(r1, r4, r5)     // Catch:{ Exception -> 0x0023, all -> 0x0020 }
            boolean r5 = r4.moveToFirst()     // Catch:{ Exception -> 0x001e }
            if (r5 == 0) goto L_0x0018
            com.myfitnesspal.shared.model.v1.FoodPermission r5 = new com.myfitnesspal.shared.model.v1.FoodPermission     // Catch:{ Exception -> 0x001e }
            com.uacf.core.database.CursorMapper r1 = new com.uacf.core.database.CursorMapper     // Catch:{ Exception -> 0x001e }
            r1.<init>(r4)     // Catch:{ Exception -> 0x001e }
            r5.<init>(r1)     // Catch:{ Exception -> 0x001e }
            r0 = r5
        L_0x0018:
            if (r4 == 0) goto L_0x0030
        L_0x001a:
            r4.close()
            goto L_0x0030
        L_0x001e:
            r5 = move-exception
            goto L_0x0025
        L_0x0020:
            r5 = move-exception
            r4 = r0
            goto L_0x0032
        L_0x0023:
            r5 = move-exception
            r4 = r0
        L_0x0025:
            java.lang.String r1 = "FoodPermission.fetchByHelper"
            r2 = 0
            java.lang.Object[] r2 = new java.lang.Object[r2]     // Catch:{ all -> 0x0031 }
            com.uacf.core.util.Ln.e(r5, r1, r2)     // Catch:{ all -> 0x0031 }
            if (r4 == 0) goto L_0x0030
            goto L_0x001a
        L_0x0030:
            return r0
        L_0x0031:
            r5 = move-exception
        L_0x0032:
            if (r4 == 0) goto L_0x0037
            r4.close()
        L_0x0037:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.table.FoodPermissionsTable.fetchByHelper(java.lang.String, java.lang.Object[]):com.myfitnesspal.shared.model.v1.FoodPermission");
    }

    /* JADX INFO: finally extract failed */
    public Map<Long, FoodPermission> getFoodIdToFoodPermissionMapForMultipleFoodIds(List<Long> list, List<Long> list2) {
        Cursor cursor = null;
        try {
            StringBuilder sb = new StringBuilder();
            sb.append("food_local_id IN ");
            sb.append(DatabaseUtil.getArgsForList(list));
            StringBuilder sb2 = new StringBuilder(sb.toString());
            if (CollectionUtils.notEmpty((Collection<?>) list2)) {
                StringBuilder sb3 = new StringBuilder();
                sb3.append(" OR food_master_id IN ");
                sb3.append(DatabaseUtil.getArgsForList(list2));
                sb2.append(sb3.toString());
            }
            SQLiteDatabaseWrapper sQLiteDatabaseWrapper = this.database;
            String[] strArr = ALL_REQUIRED_FOOD_PERMISSIONS_COLUMNS;
            String sb4 = sb2.toString();
            Cursor query = sQLiteDatabaseWrapper.query("food_permissions", strArr, sb4, null, null, null, null);
            HashMap hashMap = new HashMap();
            CursorMapper cursorMapper = new CursorMapper(query);
            while (query.moveToNext()) {
                FoodPermission foodPermission = new FoodPermission(cursorMapper);
                if (foodPermission.hasFoodLocalId()) {
                    hashMap.put(Long.valueOf(foodPermission.getFoodLocalId()), foodPermission);
                } else {
                    hashMap.put(Long.valueOf(foodPermission.getFoodMasterId()), foodPermission);
                }
            }
            if (query != null) {
                query.close();
            }
            return hashMap;
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
    }

    public void updateFoodPermissionForFood(MfpFood mfpFood, int i, long j) {
        deleteData("food_master_id=?", Long.valueOf(mfpFood.getMasterId()));
        ContentValues contentValues = new ContentValues();
        contentValues.put("master_id", Integer.valueOf(0));
        contentValues.put("food_local_id", Long.valueOf(mfpFood.getLocalId()));
        contentValues.put("food_master_id", Long.valueOf(mfpFood.getMasterId()));
        contentValues.put("food_uid", mfpFood.getId());
        contentValues.put("original_food_master_id", Long.valueOf(mfpFood.getOriginalMasterId()));
        contentValues.put("original_food_uid", mfpFood.getId());
        contentValues.put("user_id", Long.valueOf(j));
        contentValues.put("permissions", Integer.valueOf(i));
        insertData(contentValues);
    }

    public int deletePermission(long j, long j2) {
        return deleteData("food_local_id=? AND user_id=?", Long.valueOf(j), Long.valueOf(j2));
    }

    public void setMasterIdToPermission(FoodPermission foodPermission) {
        foodPermission.setMasterId(CursorUtils.readLongAndClose(queryData(new String[]{"master_id"}, "id=?", Long.valueOf(foodPermission.getId())), 0));
    }
}
