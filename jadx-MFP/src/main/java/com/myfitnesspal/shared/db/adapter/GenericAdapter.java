package com.myfitnesspal.shared.db.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.feature.exercise.service.ExerciseService;
import com.myfitnesspal.feature.settings.model.AppSettings;
import com.myfitnesspal.shared.db.Dataset;
import com.myfitnesspal.shared.db.DatasetManager;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.db.table.ExercisesTable;
import com.myfitnesspal.shared.model.v1.Exercise;
import com.myfitnesspal.shared.model.v1.FoodOrExercise;
import com.uacf.core.util.CollectionUtils;
import com.uacf.core.util.Ln;
import dagger.Lazy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;

public class GenericAdapter {
    private final int SEARCH_BATCH_LIMIT = 150;
    private final int SORT_PRIORITY_FOR_MAIN_DB = 3;
    private Lazy<AppSettings> appSettings;
    private final Context context;
    private DbConnectionManager dbConnectionManager;
    private Lazy<ExerciseService> exerciseService;
    SQLiteStatement stmt;

    public GenericAdapter(Context context2, Lazy<ExerciseService> lazy, Lazy<AppSettings> lazy2, DbConnectionManager dbConnectionManager2) {
        this.context = context2;
        this.exerciseService = lazy;
        this.appSettings = lazy2;
        this.dbConnectionManager = dbConnectionManager2;
    }

    public void eraseItemOfType(int i, long j, long j2) {
        String str;
        switch (i) {
            case 4:
                str = "delete from food_entries where user_id = ? and master_id = ?";
                break;
            case 5:
                str = "delete from exercise_entries where user_id = ? and master_id = ?";
                break;
            default:
                return;
        }
        try {
            DbConnectionManager.getDb(this.context).execSQL(str, new String[]{String.valueOf(j2), String.valueOf(j)});
        } catch (SQLException e) {
            Ln.e(e);
        }
    }

    public void updateOwnerUserMasterIdsForNewUserMasterId(long j, long j2) {
        try {
            ContentValues contentValues = new ContentValues();
            contentValues.put("owner_user_master_id", Long.valueOf(j));
            String str = "owner_user_id = ?";
            DbConnectionManager.getDb(this.context).update("foods", contentValues, str, new String[]{String.valueOf(j2)});
            ContentValues contentValues2 = new ContentValues();
            contentValues2.put("owner_user_master_id", Long.valueOf(j));
            DbConnectionManager.getDb(this.context).update(ExercisesTable.TABLE_NAME, contentValues2, str, new String[]{String.valueOf(j2)});
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public ArrayList<FoodOrExercise> searchFoods(String str, int i, long j, long j2) {
        return searchItemsOfType(1, 0, str, i, j, j2);
    }

    public ArrayList<FoodOrExercise> searchExercises(int i, String str, int i2, long j, long j2) {
        return searchItemsOfType(2, i, str, i2, j, j2);
    }

    private ArrayList<? extends FoodOrExercise> searchItemsOfType(int i, int i2, String str, int i3, long j, long j2) {
        ArrayList arrayList;
        String str2 = str;
        try {
            String convertToPhraseQuery = convertToPhraseQuery(str, '*');
            ArrayList arrayList2 = new ArrayList(1);
            Iterator it = DatasetManager.current(this.dbConnectionManager).getInstalledDatasets().iterator();
            ArrayList arrayList3 = arrayList2;
            while (it.hasNext()) {
                Dataset dataset = (Dataset) it.next();
                if (i == 1) {
                    StringBuilder sb = new StringBuilder();
                    sb.append("%");
                    sb.append(convertToPhraseQuery(str, '%'));
                    arrayList = findMatchingFoodsForSearchQuery(sb.toString(), dataset);
                    int i4 = i2;
                } else {
                    int i5 = i2;
                    arrayList = findMatchingExercisesForSearchQuery(i2, convertToPhraseQuery, dataset);
                }
                ArrayList arrayList4 = new ArrayList(150);
                mergeOldSearchResults(arrayList3, arrayList, arrayList4, 150);
                arrayList3 = arrayList4;
            }
            int i6 = i;
            ArrayList searchMainDBForItemsOfType = searchMainDBForItemsOfType(i, convertToPhraseQuery, 150, j, j2);
            ArrayList<? extends FoodOrExercise> arrayList5 = new ArrayList<>(150);
            mergeOldSearchResults(arrayList3, searchMainDBForItemsOfType, arrayList5, 150);
            return arrayList5;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    /* JADX WARNING: type inference failed for: r4v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r4v4, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r4v6 */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r4v8 */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r4v11 */
    /* JADX WARNING: type inference failed for: r4v13 */
    /* JADX WARNING: type inference failed for: r4v14 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0034, code lost:
        r4 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:14:0x0035, code lost:
        r2 = r5;
        r5 = r4;
        r4 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0039, code lost:
        r6 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x003a, code lost:
        r0 = null;
        r4 = r5;
        r5 = r6;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0047, code lost:
        r4.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:26:0x004e, code lost:
        r4.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x0034 A[ExcHandler: all (r4v7 'th' java.lang.Throwable A[CUSTOM_DECLARE]), Splitter:B:3:0x0019] */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x0047  */
    /* JADX WARNING: Removed duplicated region for block: B:26:0x004e  */
    /* JADX WARNING: Unknown variable types count: 4 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.Exercise fetchStockExerciseByMasterId(long r4, com.myfitnesspal.shared.db.Dataset r6) {
        /*
            r3 = this;
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]
            java.lang.String r4 = java.lang.String.valueOf(r4)
            r5 = 0
            r0[r5] = r4
            r4 = 0
            android.content.Context r5 = r3.context     // Catch:{ SQLiteException -> 0x0040 }
            dagger.Lazy<com.myfitnesspal.feature.settings.model.AppSettings> r1 = r3.appSettings     // Catch:{ SQLiteException -> 0x0040 }
            com.uacf.core.database.SQLiteDatabaseWrapper r5 = com.myfitnesspal.shared.db.DbConnectionManager.getStockDb(r5, r1)     // Catch:{ SQLiteException -> 0x0040 }
            java.lang.String r1 = "select master_id, owner_user_master_id, exercise_type, description, mets from stock_exercises where master_id = ?"
            android.database.Cursor r5 = r5.rawQuery(r1, r0)     // Catch:{ SQLiteException -> 0x0040 }
            boolean r0 = r5.moveToFirst()     // Catch:{ SQLiteException -> 0x0039, all -> 0x0034 }
            if (r0 == 0) goto L_0x002e
            com.myfitnesspal.shared.model.v1.Exercise r0 = new com.myfitnesspal.shared.model.v1.Exercise     // Catch:{ SQLiteException -> 0x0039, all -> 0x0034 }
            r0.<init>()     // Catch:{ SQLiteException -> 0x0039, all -> 0x0034 }
            com.myfitnesspal.shared.model.v1.Exercise r4 = r3.initStockExercise(r0, r6, r5)     // Catch:{ SQLiteException -> 0x0029, all -> 0x0034 }
            goto L_0x002e
        L_0x0029:
            r4 = move-exception
            r2 = r5
            r5 = r4
            r4 = r2
            goto L_0x0042
        L_0x002e:
            if (r5 == 0) goto L_0x004b
            r5.close()
            goto L_0x004b
        L_0x0034:
            r4 = move-exception
            r2 = r5
            r5 = r4
            r4 = r2
            goto L_0x004c
        L_0x0039:
            r6 = move-exception
            r0 = r4
            r4 = r5
            r5 = r6
            goto L_0x0042
        L_0x003e:
            r5 = move-exception
            goto L_0x004c
        L_0x0040:
            r5 = move-exception
            r0 = r4
        L_0x0042:
            com.uacf.core.util.Ln.e(r5)     // Catch:{ all -> 0x003e }
            if (r4 == 0) goto L_0x004a
            r4.close()
        L_0x004a:
            r4 = r0
        L_0x004b:
            return r4
        L_0x004c:
            if (r4 == 0) goto L_0x0051
            r4.close()
        L_0x0051:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.fetchStockExerciseByMasterId(long, com.myfitnesspal.shared.db.Dataset):com.myfitnesspal.shared.model.v1.Exercise");
    }

    private Exercise initStockExercise(Exercise exercise, Dataset dataset, Cursor cursor) {
        try {
            exercise.setMasterDatabaseId(cursor.getLong(0));
            if (exercise.getOriginalMasterId() == 0) {
                exercise.setOriginalMasterId(exercise.getMasterDatabaseId());
            }
            exercise.setOwnerUserMasterId(cursor.getLong(1));
            exercise.setExerciseType(cursor.getInt(2));
            exercise.setIsPublic(true);
            exercise.setIsDeleted(false);
            exercise.setSortPriority(dataset.getPriority());
            exercise.setDescription(cursor.getString(3));
            exercise.setMets((float) cursor.getDouble(4));
            return exercise;
        } catch (Exception e) {
            Ln.e(e);
            return null;
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0093  */
    /* JADX WARNING: Removed duplicated region for block: B:31:0x009a  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.shared.model.v1.Exercise> findMatchingExercisesForSearchQuery(int r12, java.lang.String r13, com.myfitnesspal.shared.db.Dataset r14) {
        /*
            r11 = this;
            r12 = 0
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            r1 = 150(0x96, float:2.1E-43)
            r0.<init>(r1)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            android.content.Context r1 = r11.context     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            dagger.Lazy<com.myfitnesspal.feature.settings.model.AppSettings> r2 = r11.appSettings     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            com.uacf.core.database.SQLiteDatabaseWrapper r1 = com.myfitnesspal.shared.db.DbConnectionManager.getStockDb(r1, r2)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            java.lang.String r2 = "SELECT master_id, owner_user_master_id, exercise_type, stock_exercises.description, mets FROM stock_exercises_fts LEFT JOIN stock_exercises ON stock_exercises_fts.rowid = stock_exercises.master_id WHERE stock_exercises.searchable=1 AND stock_exercises_fts.description match ? LIMIT 150"
            r3 = 1
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            r5 = 0
            r4[r5] = r13     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            android.database.Cursor r13 = r1.rawQuery(r2, r4)     // Catch:{ Exception -> 0x008c, all -> 0x0087 }
            boolean r1 = r13.moveToFirst()     // Catch:{ Exception -> 0x0085 }
            if (r1 == 0) goto L_0x007a
        L_0x0022:
            com.myfitnesspal.shared.model.v1.Exercise r1 = new com.myfitnesspal.shared.model.v1.Exercise     // Catch:{ Exception -> 0x0085 }
            r1.<init>()     // Catch:{ Exception -> 0x0085 }
            long r6 = r13.getLong(r5)     // Catch:{ Exception -> 0x0085 }
            r1.setMasterDatabaseId(r6)     // Catch:{ Exception -> 0x0085 }
            long r6 = r1.getOriginalMasterId()     // Catch:{ Exception -> 0x0085 }
            r8 = 0
            int r2 = (r6 > r8 ? 1 : (r6 == r8 ? 0 : -1))
            if (r2 != 0) goto L_0x003f
            long r6 = r1.getMasterDatabaseId()     // Catch:{ Exception -> 0x0085 }
            r1.setOriginalMasterId(r6)     // Catch:{ Exception -> 0x0085 }
        L_0x003f:
            r2 = 2
            long r6 = r13.getLong(r3)     // Catch:{ Exception -> 0x0085 }
            r1.setOwnerUserMasterId(r6)     // Catch:{ Exception -> 0x0085 }
            r4 = 3
            int r2 = r13.getInt(r2)     // Catch:{ Exception -> 0x0085 }
            r1.setExerciseType(r2)     // Catch:{ Exception -> 0x0085 }
            r1.setIsPublic(r3)     // Catch:{ Exception -> 0x0085 }
            r1.setIsDeleted(r5)     // Catch:{ Exception -> 0x0085 }
            int r2 = r14.getPriority()     // Catch:{ Exception -> 0x0085 }
            r1.setSortPriority(r2)     // Catch:{ Exception -> 0x0085 }
            r2 = 4
            java.lang.String r4 = r13.getString(r4)     // Catch:{ Exception -> 0x0085 }
            r1.setDescription(r4)     // Catch:{ Exception -> 0x0085 }
            float r2 = r13.getFloat(r2)     // Catch:{ Exception -> 0x0085 }
            r1.setMets(r2)     // Catch:{ Exception -> 0x0085 }
            r0.add(r1)     // Catch:{ Exception -> 0x0085 }
            boolean r1 = r13.moveToNext()     // Catch:{ Exception -> 0x0085 }
            if (r1 != 0) goto L_0x0022
            if (r13 == 0) goto L_0x0079
            r13.close()
        L_0x0079:
            return r0
        L_0x007a:
            java.util.ArrayList r14 = new java.util.ArrayList     // Catch:{ Exception -> 0x0085 }
            r14.<init>()     // Catch:{ Exception -> 0x0085 }
            if (r13 == 0) goto L_0x0084
            r13.close()
        L_0x0084:
            return r14
        L_0x0085:
            r14 = move-exception
            goto L_0x008e
        L_0x0087:
            r13 = move-exception
            r10 = r13
            r13 = r12
            r12 = r10
            goto L_0x0098
        L_0x008c:
            r14 = move-exception
            r13 = r12
        L_0x008e:
            com.uacf.core.util.Ln.e(r14)     // Catch:{ all -> 0x0097 }
            if (r13 == 0) goto L_0x0096
            r13.close()
        L_0x0096:
            return r12
        L_0x0097:
            r12 = move-exception
        L_0x0098:
            if (r13 == 0) goto L_0x009d
            r13.close()
        L_0x009d:
            throw r12
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.findMatchingExercisesForSearchQuery(int, java.lang.String, com.myfitnesspal.shared.db.Dataset):java.util.ArrayList");
    }

    /* JADX WARNING: Removed duplicated region for block: B:27:0x0135  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x013d  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<? extends com.myfitnesspal.shared.model.v1.FoodOrExercise> findMatchingFoodsForSearchQuery(java.lang.String r20, com.myfitnesspal.shared.db.Dataset r21) {
        /*
            r19 = this;
            r1 = r19
            r2 = 0
            java.lang.StringBuilder r0 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r0.<init>()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r3 = "country_"
            r0.append(r3)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            int r3 = r21.appropriateSortOrderCountryId()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r0.append(r3)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r3 = "_sort_order"
            r0.append(r3)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r0 = r0.toString()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r3 = 8
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r4 = "master_id"
            r5 = 0
            r3[r5] = r4     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r4 = "original_food_master_id"
            r6 = 1
            r3[r6] = r4     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4 = 2
            java.lang.String r7 = "owner_user_master_id"
            r3[r4] = r7     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r4 = "food_type"
            r7 = 3
            r3[r7] = r4     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4 = 4
            java.lang.String r8 = "description"
            r3[r4] = r8     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4 = 5
            java.lang.String r8 = "brand"
            r3[r4] = r8     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4 = 6
            java.lang.String r8 = "food_info"
            r3[r4] = r8     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4 = 7
            r3[r4] = r0     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r3 = com.uacf.core.database.DatabaseUtil.getColumnString(r3)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4.<init>()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r8 = "select %s from stock_foods where description like ?  order by "
            r4.append(r8)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4.append(r0)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r8 = " desc  limit "
            r4.append(r8)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r8 = 150(0x96, float:2.1E-43)
            r4.append(r8)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r4 = r4.toString()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.Object[] r8 = new java.lang.Object[r6]     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r8[r5] = r3     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r3 = java.lang.String.format(r4, r8)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.util.ArrayList r4 = new java.util.ArrayList     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r4.<init>()     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            android.content.Context r8 = r1.context     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            dagger.Lazy<com.myfitnesspal.feature.settings.model.AppSettings> r9 = r1.appSettings     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            com.uacf.core.database.SQLiteDatabaseWrapper r8 = com.myfitnesspal.shared.db.DbConnectionManager.getStockDb(r8, r9)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String[] r9 = new java.lang.String[r6]     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            r9[r5] = r20     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            android.database.Cursor r3 = r8.rawQuery(r3, r9)     // Catch:{ Exception -> 0x012e, all -> 0x012c }
            java.lang.String r8 = "master_id"
            int r8 = r3.getColumnIndex(r8)     // Catch:{ Exception -> 0x012a }
            java.lang.String r9 = "original_food_master_id"
            int r9 = r3.getColumnIndex(r9)     // Catch:{ Exception -> 0x012a }
            java.lang.String r10 = "owner_user_master_id"
            int r10 = r3.getColumnIndex(r10)     // Catch:{ Exception -> 0x012a }
            java.lang.String r11 = "food_type"
            int r11 = r3.getColumnIndex(r11)     // Catch:{ Exception -> 0x012a }
            java.lang.String r12 = "description"
            int r12 = r3.getColumnIndex(r12)     // Catch:{ Exception -> 0x012a }
            java.lang.String r13 = "brand"
            int r13 = r3.getColumnIndex(r13)     // Catch:{ Exception -> 0x012a }
            java.lang.String r14 = "food_info"
            int r14 = r3.getColumnIndex(r14)     // Catch:{ Exception -> 0x012a }
            int r0 = r3.getColumnIndex(r0)     // Catch:{ Exception -> 0x012a }
        L_0x00b1:
            boolean r15 = r3.moveToNext()     // Catch:{ Exception -> 0x012a }
            if (r15 == 0) goto L_0x0124
            com.myfitnesspal.shared.model.v1.Food r15 = new com.myfitnesspal.shared.model.v1.Food     // Catch:{ Exception -> 0x012a }
            r15.<init>()     // Catch:{ Exception -> 0x012a }
            long r5 = r3.getLong(r8)     // Catch:{ Exception -> 0x012a }
            r15.setMasterDatabaseId(r5)     // Catch:{ Exception -> 0x012a }
            long r5 = r3.getLong(r9)     // Catch:{ Exception -> 0x012a }
            r15.setOriginalMasterId(r5)     // Catch:{ Exception -> 0x012a }
            long r5 = r15.getOriginalMasterId()     // Catch:{ Exception -> 0x012a }
            r16 = 0
            int r18 = (r5 > r16 ? 1 : (r5 == r16 ? 0 : -1))
            if (r18 != 0) goto L_0x00db
            long r5 = r15.getMasterDatabaseId()     // Catch:{ Exception -> 0x012a }
            r15.setOriginalMasterId(r5)     // Catch:{ Exception -> 0x012a }
        L_0x00db:
            long r5 = r3.getLong(r10)     // Catch:{ Exception -> 0x012a }
            r15.setOwnerUserMasterId(r5)     // Catch:{ Exception -> 0x012a }
            int r5 = r3.getInt(r11)     // Catch:{ Exception -> 0x012a }
            if (r5 != 0) goto L_0x00ea
            r5 = 1
            goto L_0x00eb
        L_0x00ea:
            r5 = 3
        L_0x00eb:
            r15.setFoodType(r5)     // Catch:{ Exception -> 0x012a }
            r5 = 1
            r15.setIsPublic(r5)     // Catch:{ Exception -> 0x012a }
            r6 = 0
            r15.setIsDeleted(r6)     // Catch:{ Exception -> 0x012a }
            int r5 = r21.getPriority()     // Catch:{ Exception -> 0x012a }
            r15.setSortPriority(r5)     // Catch:{ Exception -> 0x012a }
            java.lang.String r5 = r3.getString(r12)     // Catch:{ Exception -> 0x012a }
            r15.setDescription(r5)     // Catch:{ Exception -> 0x012a }
            java.lang.String r5 = r3.getString(r13)     // Catch:{ Exception -> 0x012a }
            r15.setBrand(r5)     // Catch:{ Exception -> 0x012a }
            byte[] r5 = r3.getBlob(r14)     // Catch:{ Exception -> 0x012a }
            int r6 = com.myfitnesspal.shared.model.v1.Food.FOOD_INFO_V1     // Catch:{ Exception -> 0x012a }
            com.myfitnesspal.shared.model.v1.Food.unpackWithDecoder(r15, r5, r6)     // Catch:{ Exception -> 0x012a }
            int r5 = r3.getInt(r0)     // Catch:{ Exception -> 0x012a }
            r15.setSortOrder(r5)     // Catch:{ Exception -> 0x012a }
            r4.add(r15)     // Catch:{ Exception -> 0x012a }
            r3.moveToNext()     // Catch:{ Exception -> 0x012a }
            r5 = 0
            r6 = 1
            goto L_0x00b1
        L_0x0124:
            if (r3 == 0) goto L_0x0129
            r3.close()
        L_0x0129:
            return r4
        L_0x012a:
            r0 = move-exception
            goto L_0x0130
        L_0x012c:
            r0 = move-exception
            goto L_0x013b
        L_0x012e:
            r0 = move-exception
            r3 = r2
        L_0x0130:
            com.uacf.core.util.Ln.e(r0)     // Catch:{ all -> 0x0139 }
            if (r3 == 0) goto L_0x0138
            r3.close()
        L_0x0138:
            return r2
        L_0x0139:
            r0 = move-exception
            r2 = r3
        L_0x013b:
            if (r2 == 0) goto L_0x0140
            r2.close()
        L_0x0140:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.findMatchingFoodsForSearchQuery(java.lang.String, com.myfitnesspal.shared.db.Dataset):java.util.ArrayList");
    }

    /* access modifiers changed from: 0000 */
    /* JADX WARNING: Removed duplicated region for block: B:37:0x00a8  */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00af  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.shared.model.v1.FoodOrExercise> searchMainDBForItemsOfType(int r8, java.lang.String r9, int r10, long r11, long r13) {
        /*
            r7 = this;
            r0 = 0
            r1 = 1
            if (r8 != r1) goto L_0x0007
            java.lang.String r2 = "select docid from foods_fts where description match ? limit ?"
            goto L_0x0009
        L_0x0007:
            java.lang.String r2 = "select docid from exercises_fts where description match ? limit ?"
        L_0x0009:
            r3 = 2
            java.lang.String[] r3 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            r4 = 0
            r3[r4] = r9     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            java.lang.String r9 = java.lang.String.valueOf(r10)     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            r3[r1] = r9     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            android.content.Context r9 = r7.context     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            com.uacf.core.database.SQLiteDatabaseWrapper r9 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r9)     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            android.database.Cursor r9 = r9.rawQuery(r2, r3)     // Catch:{ SQLiteException -> 0x00a1, all -> 0x009e }
            java.util.ArrayList r2 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x009c }
            r2.<init>(r10)     // Catch:{ SQLiteException -> 0x009c }
            java.util.ArrayList r10 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x009c }
            int r3 = r2.size()     // Catch:{ SQLiteException -> 0x009c }
            r10.<init>(r3)     // Catch:{ SQLiteException -> 0x009c }
            boolean r3 = r9.moveToFirst()     // Catch:{ SQLiteException -> 0x009c }
            if (r3 == 0) goto L_0x0096
            r3 = 0
        L_0x0034:
            int r5 = r9.getCount()     // Catch:{ SQLiteException -> 0x009c }
            if (r3 >= r5) goto L_0x0048
            long r5 = r9.getLong(r4)     // Catch:{ SQLiteException -> 0x009c }
            java.lang.Long r5 = java.lang.Long.valueOf(r5)     // Catch:{ SQLiteException -> 0x009c }
            r2.add(r5)     // Catch:{ SQLiteException -> 0x009c }
            int r3 = r3 + 1
            goto L_0x0034
        L_0x0048:
            java.util.HashSet r3 = new java.util.HashSet     // Catch:{ SQLiteException -> 0x009c }
            r3.<init>(r2)     // Catch:{ SQLiteException -> 0x009c }
            r2.clear()     // Catch:{ SQLiteException -> 0x009c }
            r2.addAll(r3)     // Catch:{ SQLiteException -> 0x009c }
            java.util.Iterator r2 = r2.iterator()     // Catch:{ SQLiteException -> 0x009c }
        L_0x0057:
            boolean r3 = r2.hasNext()     // Catch:{ SQLiteException -> 0x009c }
            if (r3 == 0) goto L_0x0096
            java.lang.Object r3 = r2.next()     // Catch:{ SQLiteException -> 0x009c }
            java.lang.Long r3 = (java.lang.Long) r3     // Catch:{ SQLiteException -> 0x009c }
            long r3 = r3.longValue()     // Catch:{ SQLiteException -> 0x009c }
            if (r8 != r1) goto L_0x0079
            com.myfitnesspal.shared.db.DbConnectionManager r5 = r7.dbConnectionManager     // Catch:{ SQLiteException -> 0x009c }
            com.myfitnesspal.shared.db.adapter.FoodDBAdapter r5 = r5.foodDbAdapter()     // Catch:{ SQLiteException -> 0x009c }
            com.myfitnesspal.shared.model.v1.Food r3 = r5.fetchFoodById(r3)     // Catch:{ SQLiteException -> 0x009c }
            com.myfitnesspal.shared.db.DbConnectionManager r4 = r7.dbConnectionManager     // Catch:{ SQLiteException -> 0x009c }
            r3.lookupSortOrderByUsageCount(r11, r4)     // Catch:{ SQLiteException -> 0x009c }
            goto L_0x0085
        L_0x0079:
            dagger.Lazy<com.myfitnesspal.feature.exercise.service.ExerciseService> r5 = r7.exerciseService     // Catch:{ SQLiteException -> 0x009c }
            java.lang.Object r5 = r5.get()     // Catch:{ SQLiteException -> 0x009c }
            com.myfitnesspal.feature.exercise.service.ExerciseService r5 = (com.myfitnesspal.feature.exercise.service.ExerciseService) r5     // Catch:{ SQLiteException -> 0x009c }
            com.myfitnesspal.shared.model.v1.Exercise r3 = r5.getV1ExerciseLocally(r3)     // Catch:{ SQLiteException -> 0x009c }
        L_0x0085:
            if (r3 != 0) goto L_0x0088
            goto L_0x0057
        L_0x0088:
            r4 = 3
            r3.setSortPriority(r4)     // Catch:{ SQLiteException -> 0x009c }
            boolean r4 = r3.isPrivateItemOfAnotherUser(r13)     // Catch:{ SQLiteException -> 0x009c }
            if (r4 != 0) goto L_0x0057
            r10.add(r3)     // Catch:{ SQLiteException -> 0x009c }
            goto L_0x0057
        L_0x0096:
            if (r9 == 0) goto L_0x009b
            r9.close()
        L_0x009b:
            return r10
        L_0x009c:
            r8 = move-exception
            goto L_0x00a3
        L_0x009e:
            r8 = move-exception
            r9 = r0
            goto L_0x00ad
        L_0x00a1:
            r8 = move-exception
            r9 = r0
        L_0x00a3:
            com.uacf.core.util.Ln.e(r8)     // Catch:{ all -> 0x00ac }
            if (r9 == 0) goto L_0x00ab
            r9.close()
        L_0x00ab:
            return r0
        L_0x00ac:
            r8 = move-exception
        L_0x00ad:
            if (r9 == 0) goto L_0x00b2
            r9.close()
        L_0x00b2:
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.searchMainDBForItemsOfType(int, java.lang.String, int, long, long):java.util.ArrayList");
    }

    private void mergeOldSearchResults(ArrayList<FoodOrExercise> arrayList, ArrayList<FoodOrExercise> arrayList2, ArrayList<FoodOrExercise> arrayList3, int i) {
        try {
            int size = CollectionUtils.size((Collection<?>) arrayList);
            int size2 = CollectionUtils.size((Collection<?>) arrayList2);
            ArrayList arrayList4 = new ArrayList(size + size2);
            Iterator it = arrayList.iterator();
            while (it.hasNext()) {
                FoodOrExercise foodOrExercise = (FoodOrExercise) it.next();
                boolean z = true;
                if (size2 > 0) {
                    Iterator it2 = arrayList2.iterator();
                    while (true) {
                        if (it2.hasNext()) {
                            if (((FoodOrExercise) it2.next()).shadows(foodOrExercise)) {
                                z = false;
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                }
                if (z) {
                    arrayList4.add(foodOrExercise);
                }
            }
            if (size2 > 0) {
                arrayList4.addAll(arrayList2);
            }
            Collections.sort(arrayList4);
            Iterator it3 = arrayList4.iterator();
            while (it3.hasNext()) {
                FoodOrExercise foodOrExercise2 = (FoodOrExercise) it3.next();
                if (arrayList3.size() < i) {
                    arrayList3.add(foodOrExercise2);
                }
            }
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    private String convertToPhraseQuery(String str, char c) {
        StringBuilder sb;
        String[] strArr = tokenizeString(str);
        ArrayList arrayList = new ArrayList(strArr.length);
        for (String str2 : strArr) {
            int indexOf = str2.indexOf("'");
            if (indexOf != -1) {
                str2 = str2.substring(0, indexOf);
            }
            String replaceAll = str2.replaceAll("\\*", "").replaceAll("\\\\", "");
            StringBuilder sb2 = new StringBuilder();
            sb2.append(replaceAll);
            sb2.append(c);
            arrayList.add(sb2.toString());
        }
        String str3 = "";
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            String str4 = (String) it.next();
            if (str3.equals("")) {
                sb = new StringBuilder();
            } else {
                sb = new StringBuilder();
                sb.append(str3);
                str3 = " ";
            }
            sb.append(str3);
            sb.append(str4);
            str3 = sb.toString();
        }
        return str3;
    }

    /* access modifiers changed from: 0000 */
    public String[] tokenizeString(String str) {
        return str.split(" ");
    }

    /* JADX WARNING: Removed duplicated region for block: B:53:0x0129  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x0130  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.DatabaseObject[] fetchUnsyncedOwnedItemsForUserId(long r11, int r13) {
        /*
            r10 = this;
            r0 = 0
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r1.<init>(r13)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r2 = "select id from foods where owner_user_id = ? and master_id is null and uid is null order by id asc limit ?"
            r3 = 2
            java.lang.String[] r4 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r5 = java.lang.String.valueOf(r11)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r6 = 0
            r4[r6] = r5     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            java.lang.String r5 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            r7 = 1
            r4[r7] = r5     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            android.content.Context r5 = r10.context     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            com.uacf.core.database.SQLiteDatabaseWrapper r5 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r5)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            android.database.Cursor r2 = r5.rawQuery(r2, r4)     // Catch:{ SQLiteException -> 0x0122, all -> 0x011f }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x011d }
            if (r4 == 0) goto L_0x0040
        L_0x0029:
            com.myfitnesspal.shared.db.DbConnectionManager r4 = r10.dbConnectionManager     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.db.adapter.FoodDBAdapter r4 = r4.foodDbAdapter()     // Catch:{ SQLiteException -> 0x011d }
            long r8 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.Food r4 = r4.fetchFoodById(r8)     // Catch:{ SQLiteException -> 0x011d }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x011d }
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x011d }
            if (r4 != 0) goto L_0x0029
        L_0x0040:
            r2.close()     // Catch:{ SQLiteException -> 0x011d }
            int r4 = r1.size()     // Catch:{ SQLiteException -> 0x011d }
            int r13 = r13 - r4
            if (r13 > 0) goto L_0x005c
            int r11 = r1.size()     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r11 = new com.myfitnesspal.shared.model.v1.DatabaseObject[r11]     // Catch:{ SQLiteException -> 0x011d }
            java.lang.Object[] r11 = r1.toArray(r11)     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r11 = (com.myfitnesspal.shared.model.v1.DatabaseObject[]) r11     // Catch:{ SQLiteException -> 0x011d }
            if (r2 == 0) goto L_0x005b
            r2.close()
        L_0x005b:
            return r11
        L_0x005c:
            java.lang.String r4 = "select id from exercises where owner_user_id = ? and ((master_id is null AND uid is null) OR sync_flags = 1 OR sync_flags = 2) order by id asc limit ?"
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r8 = java.lang.String.valueOf(r11)     // Catch:{ SQLiteException -> 0x011d }
            r5[r6] = r8     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r8 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x011d }
            r5[r7] = r8     // Catch:{ SQLiteException -> 0x011d }
            android.content.Context r8 = r10.context     // Catch:{ SQLiteException -> 0x011d }
            com.uacf.core.database.SQLiteDatabaseWrapper r8 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r8)     // Catch:{ SQLiteException -> 0x011d }
            android.database.Cursor r2 = r8.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x011d }
            boolean r4 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x011d }
            if (r4 == 0) goto L_0x0095
        L_0x007c:
            dagger.Lazy<com.myfitnesspal.feature.exercise.service.ExerciseService> r4 = r10.exerciseService     // Catch:{ SQLiteException -> 0x011d }
            java.lang.Object r4 = r4.get()     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.feature.exercise.service.ExerciseService r4 = (com.myfitnesspal.feature.exercise.service.ExerciseService) r4     // Catch:{ SQLiteException -> 0x011d }
            long r8 = r2.getLong(r6)     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.Exercise r4 = r4.getV1ExerciseLocally(r8)     // Catch:{ SQLiteException -> 0x011d }
            r1.add(r4)     // Catch:{ SQLiteException -> 0x011d }
            boolean r4 = r2.moveToNext()     // Catch:{ SQLiteException -> 0x011d }
            if (r4 != 0) goto L_0x007c
        L_0x0095:
            r2.close()     // Catch:{ SQLiteException -> 0x011d }
            int r4 = r1.size()     // Catch:{ SQLiteException -> 0x011d }
            int r13 = r13 - r4
            if (r13 > 0) goto L_0x00b1
            int r11 = r1.size()     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r11 = new com.myfitnesspal.shared.model.v1.DatabaseObject[r11]     // Catch:{ SQLiteException -> 0x011d }
            java.lang.Object[] r11 = r1.toArray(r11)     // Catch:{ SQLiteException -> 0x011d }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r11 = (com.myfitnesspal.shared.model.v1.DatabaseObject[]) r11     // Catch:{ SQLiteException -> 0x011d }
            if (r2 == 0) goto L_0x00b0
            r2.close()
        L_0x00b0:
            return r11
        L_0x00b1:
            java.lang.String r4 = "select id, master_id, food_id, food_description from recipe_box_items where user_id = ? and master_id is null and uid is null order by id asc limit ?"
            java.lang.String[] r5 = new java.lang.String[r3]     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r11 = java.lang.String.valueOf(r11)     // Catch:{ SQLiteException -> 0x011d }
            r5[r6] = r11     // Catch:{ SQLiteException -> 0x011d }
            java.lang.String r11 = java.lang.String.valueOf(r13)     // Catch:{ SQLiteException -> 0x011d }
            r5[r7] = r11     // Catch:{ SQLiteException -> 0x011d }
            android.content.Context r11 = r10.context     // Catch:{ SQLiteException -> 0x011d }
            com.uacf.core.database.SQLiteDatabaseWrapper r11 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r11)     // Catch:{ SQLiteException -> 0x011d }
            android.database.Cursor r11 = r11.rawQuery(r4, r5)     // Catch:{ SQLiteException -> 0x011d }
            boolean r12 = r11.moveToFirst()     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            if (r12 == 0) goto L_0x0103
        L_0x00d1:
            com.myfitnesspal.shared.model.v1.RecipeBoxItem r12 = new com.myfitnesspal.shared.model.v1.RecipeBoxItem     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r12.<init>()     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            long r4 = r11.getLong(r6)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r12.setLocalId(r4)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            long r4 = r11.getLong(r7)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r12.setMasterDatabaseId(r4)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            long r4 = r11.getLong(r3)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            int r13 = (int) r4     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r12.setRecipeFoodId(r13)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r13 = 3
            java.lang.String r2 = r11.getString(r13)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            if (r2 == 0) goto L_0x00fa
            java.lang.String r13 = r11.getString(r13)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            r12.setFoodDescription(r13)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
        L_0x00fa:
            r1.add(r12)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            boolean r12 = r11.moveToNext()     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            if (r12 != 0) goto L_0x00d1
        L_0x0103:
            int r12 = r1.size()     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r12 = new com.myfitnesspal.shared.model.v1.DatabaseObject[r12]     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            java.lang.Object[] r12 = r1.toArray(r12)     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            com.myfitnesspal.shared.model.v1.DatabaseObject[] r12 = (com.myfitnesspal.shared.model.v1.DatabaseObject[]) r12     // Catch:{ SQLiteException -> 0x0119, all -> 0x0115 }
            if (r11 == 0) goto L_0x0114
            r11.close()
        L_0x0114:
            return r12
        L_0x0115:
            r12 = move-exception
            r2 = r11
            r11 = r12
            goto L_0x012e
        L_0x0119:
            r12 = move-exception
            r2 = r11
            r11 = r12
            goto L_0x0124
        L_0x011d:
            r11 = move-exception
            goto L_0x0124
        L_0x011f:
            r11 = move-exception
            r2 = r0
            goto L_0x012e
        L_0x0122:
            r11 = move-exception
            r2 = r0
        L_0x0124:
            com.uacf.core.util.Ln.e(r11)     // Catch:{ all -> 0x012d }
            if (r2 == 0) goto L_0x012c
            r2.close()
        L_0x012c:
            return r0
        L_0x012d:
            r11 = move-exception
        L_0x012e:
            if (r2 == 0) goto L_0x0133
            r2.close()
        L_0x0133:
            throw r11
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.fetchUnsyncedOwnedItemsForUserId(long, int):com.myfitnesspal.shared.model.v1.DatabaseObject[]");
    }

    /* JADX WARNING: Removed duplicated region for block: B:33:0x007e  */
    /* JADX WARNING: Removed duplicated region for block: B:38:0x0086  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.lang.Long[] fetchUnsyncedEntryItemIdsOfType(int r6, long r7, int r9) {
        /*
            r5 = this;
            r0 = 0
            switch(r6) {
                case 4: goto L_0x0021;
                case 5: goto L_0x001e;
                case 7: goto L_0x001b;
                case 8: goto L_0x0018;
                case 10: goto L_0x0015;
                case 15: goto L_0x0012;
                case 20: goto L_0x000f;
                case 21: goto L_0x000c;
                case 26: goto L_0x0009;
                case 27: goto L_0x0006;
                default: goto L_0x0004;
            }
        L_0x0004:
            goto L_0x008a
        L_0x0006:
            java.lang.String r6 = "select id from food_notes where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x0009:
            java.lang.String r6 = "select id from food_permissions where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x000c:
            java.lang.String r6 = "select id from tracked_nutrients where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x000f:
            java.lang.String r6 = "select id from reminders where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x0012:
            java.lang.String r6 = "select id from profile_images where user_id = ? and master_id is null and image_type = 1 order by id asc limit ?"
            goto L_0x0023
        L_0x0015:
            java.lang.String r6 = "select id from diary_notes where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x0018:
            java.lang.String r6 = "select id from measurements where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x001b:
            java.lang.String r6 = "select id from water_entries where user_id = ? and master_id is null order by id asc limit ?"
            goto L_0x0023
        L_0x001e:
            java.lang.String r6 = "select id from exercise_entries where user_id = ? and master_id is null and sync_flags IN (1,2) ORDER BY id ASC LIMIT ?"
            goto L_0x0023
        L_0x0021:
            java.lang.String r6 = "select id from food_entries where user_id = ? and master_id is null order by id asc limit ?"
        L_0x0023:
            java.util.ArrayList r1 = new java.util.ArrayList     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            r1.<init>(r9)     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            r2 = 2
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            java.lang.String r7 = java.lang.String.valueOf(r7)     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            r8 = 0
            r2[r8] = r7     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            r7 = 1
            java.lang.String r9 = java.lang.String.valueOf(r9)     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            r2[r7] = r9     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            android.content.Context r7 = r5.context     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            com.uacf.core.database.SQLiteDatabaseWrapper r7 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r7)     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            android.database.Cursor r6 = r7.rawQuery(r6, r2)     // Catch:{ Exception -> 0x0077, all -> 0x0075 }
            boolean r7 = r6.moveToFirst()     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            if (r7 == 0) goto L_0x005a
        L_0x0049:
            long r2 = r6.getLong(r8)     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            java.lang.Long r7 = java.lang.Long.valueOf(r2)     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            r1.add(r7)     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            boolean r7 = r6.moveToNext()     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            if (r7 != 0) goto L_0x0049
        L_0x005a:
            int r7 = r1.size()     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            java.lang.Long[] r7 = new java.lang.Long[r7]     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            java.lang.Object[] r7 = r1.toArray(r7)     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            java.lang.Long[] r7 = (java.lang.Long[]) r7     // Catch:{ Exception -> 0x0070, all -> 0x006c }
            if (r6 == 0) goto L_0x006b
            r6.close()
        L_0x006b:
            return r7
        L_0x006c:
            r7 = move-exception
            r0 = r6
            r6 = r7
            goto L_0x0084
        L_0x0070:
            r7 = move-exception
            r4 = r7
            r7 = r6
            r6 = r4
            goto L_0x0079
        L_0x0075:
            r6 = move-exception
            goto L_0x0084
        L_0x0077:
            r6 = move-exception
            r7 = r0
        L_0x0079:
            com.uacf.core.util.Ln.e(r6)     // Catch:{ all -> 0x0082 }
            if (r7 == 0) goto L_0x0081
            r7.close()
        L_0x0081:
            return r0
        L_0x0082:
            r6 = move-exception
            r0 = r7
        L_0x0084:
            if (r0 == 0) goto L_0x0089
            r0.close()
        L_0x0089:
            throw r6
        L_0x008a:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.GenericAdapter.fetchUnsyncedEntryItemIdsOfType(int, long, int):java.lang.Long[]");
    }

    public int countOwnedItemsOfType(int i, long j) {
        String str;
        String[] strArr;
        int i2 = 0;
        Cursor cursor = null;
        switch (i) {
            case 1:
            case 3:
                String[] strArr2 = {String.valueOf(j), String.valueOf(i)};
                str = "select count(*) from foods where owner_user_id = ? and deleted = 0 and food_type = ?";
                strArr = strArr2;
                break;
            case 2:
                str = "select count(*) from exercises where owner_user_id = ? and deleted = 0";
                strArr = new String[]{String.valueOf(j)};
                break;
            default:
                switch (i) {
                    case 11:
                        break;
                    case 12:
                        str = "select count(*) from recipe_box_items where user_id = ?";
                        try {
                            strArr = new String[]{String.valueOf(j)};
                            break;
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
                    default:
                        return 0;
                }
                String[] strArr22 = {String.valueOf(j), String.valueOf(i)};
                str = "select count(*) from foods where owner_user_id = ? and deleted = 0 and food_type = ?";
                strArr = strArr22;
                break;
        }
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(str, strArr);
        if (rawQuery.moveToFirst()) {
            i2 = rawQuery.getInt(0);
        }
        if (rawQuery != null) {
            rawQuery.close();
        }
        return i2;
    }

    public void completelyEraseFoodId(long j) {
        try {
            Object[] objArr = {Long.valueOf(j)};
            DbConnectionManager.getDb(this.context).execSQL("delete from food_entries where food_id = ?", objArr);
            this.dbConnectionManager.mealIngredientsDbAdapter().eraseIngredientsForMealFoodId(j);
            DbConnectionManager.getDb(this.context).execSQL("delete from foods where id = ?", objArr);
            this.dbConnectionManager.foodDbAdapter().purgeFoodReferencesForFoodId(j);
        } catch (SQLiteException e) {
            Ln.e(e);
        } catch (SQLException e2) {
            Ln.e(e2);
        }
    }
}
