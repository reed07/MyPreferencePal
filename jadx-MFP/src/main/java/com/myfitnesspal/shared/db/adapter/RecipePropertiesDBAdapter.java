package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.RecipeFood;
import com.uacf.core.util.Ln;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RecipePropertiesDBAdapter {
    private final Context context;
    SQLiteStatement stmt;
    int updatesExecuted;

    public RecipePropertiesDBAdapter(Context context2) {
        this.context = context2;
    }

    public void eraseRecipePropertiesForRecipeFoodId(long j) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(76);
            this.stmt.bindLong(1, j);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.updatesExecuted++;
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    public void insertRecipeProperties(long j, Map<String, List<String>> map) {
        try {
            insertRecipePropertiesUnsafe(j, map);
        } catch (SQLiteException e) {
            Ln.e(e);
        }
    }

    private void insertRecipePropertiesUnsafe(long j, Map<String, List<String>> map) {
        this.stmt = DbConnectionManager.preparedStatement(77);
        for (String str : map.keySet()) {
            int i = 1;
            for (String str2 : (List) map.get(str)) {
                this.stmt.bindLong(1, j);
                int i2 = i + 1;
                this.stmt.bindLong(2, (long) i);
                this.stmt.bindString(3, str);
                this.stmt.bindString(4, str2);
                this.stmt.execute();
                this.stmt.clearBindings();
                i = i2;
            }
        }
    }

    private Map<String, List<String>> recipePropertiesForRecipeFood(long j) {
        HashMap hashMap = new HashMap(5);
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(90), new String[]{String.valueOf(j)});
        try {
            if (rawQuery.moveToFirst()) {
                do {
                    String string = rawQuery.getString(0);
                    String string2 = rawQuery.getString(1);
                    if (!(string == null || string2 == null)) {
                        List list = (List) hashMap.get(string);
                        if (list == null) {
                            list = new ArrayList(3);
                            hashMap.put(string, list);
                        }
                        list.add(string2);
                    }
                } while (rawQuery.moveToNext());
            }
            return hashMap;
        } finally {
            rawQuery.close();
        }
    }

    public Map<String, List<String>> recipePropertiesForRecipeFood(RecipeFood recipeFood) {
        return recipePropertiesForRecipeFood(recipeFood.localId);
    }

    public long findRecipeFoodIdForV2Recipe(String str) {
        Cursor rawQuery = DbConnectionManager.getDb(this.context).rawQuery(DbConnectionManager.queryString(136), new String[]{String.valueOf(str)});
        try {
            if (rawQuery.moveToFirst()) {
                long j = rawQuery.getLong(0);
                rawQuery.close();
                return j;
            }
        } catch (SQLiteException e) {
            Ln.e(e);
        } catch (Throwable th) {
            rawQuery.close();
            throw th;
        }
        rawQuery.close();
        return 0;
    }

    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0084, code lost:
        r1 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0088, code lost:
        if (r10 != null) goto L_0x008a;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x008a, code lost:
        if (r0 != null) goto L_0x008c;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:?, code lost:
        r10.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:23:0x0090, code lost:
        r10 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x0091, code lost:
        r0.addSuppressed(r10);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:25:0x0095, code lost:
        r10.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.Map<java.lang.Long, java.util.Map<java.lang.String, java.util.List<java.lang.String>>> getRecipePropertiesForFoodIds(java.util.List<java.lang.Long> r10) {
        /*
            r9 = this;
            java.lang.String r10 = com.uacf.core.database.DatabaseUtil.getArgsForList(r10)
            android.content.Context r0 = r9.context
            com.uacf.core.database.SQLiteDatabaseWrapper r1 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r0)
            java.lang.String r2 = "recipe_properties"
            java.lang.String r0 = "recipe_food_id"
            java.lang.String r3 = "property_type"
            java.lang.String r4 = "property_value"
            java.lang.String[] r3 = new java.lang.String[]{r0, r3, r4}
            java.lang.StringBuilder r0 = new java.lang.StringBuilder
            r0.<init>()
            java.lang.String r4 = "recipe_food_id IN "
            r0.append(r4)
            r0.append(r10)
            java.lang.String r4 = r0.toString()
            r5 = 0
            r6 = 0
            r7 = 0
            r8 = 0
            android.database.Cursor r10 = r1.query(r2, r3, r4, r5, r6, r7, r8)
            r0 = 0
            java.util.HashMap r1 = new java.util.HashMap     // Catch:{ Throwable -> 0x0086 }
            r1.<init>()     // Catch:{ Throwable -> 0x0086 }
            com.uacf.core.database.CursorMapper r2 = new com.uacf.core.database.CursorMapper     // Catch:{ Throwable -> 0x0086 }
            r2.<init>(r10)     // Catch:{ Throwable -> 0x0086 }
        L_0x003a:
            boolean r3 = r2.moveToNext()     // Catch:{ Throwable -> 0x0086 }
            if (r3 == 0) goto L_0x007e
            java.lang.String r3 = "recipe_food_id"
            long r3 = r2.getLong(r3)     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r5 = "property_type"
            java.lang.String r5 = r2.getString(r5)     // Catch:{ Throwable -> 0x0086 }
            java.lang.String r6 = "property_value"
            java.lang.String r6 = r2.getString(r6)     // Catch:{ Throwable -> 0x0086 }
            java.lang.Long r7 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x0086 }
            java.lang.Object r7 = r1.get(r7)     // Catch:{ Throwable -> 0x0086 }
            java.util.Map r7 = (java.util.Map) r7     // Catch:{ Throwable -> 0x0086 }
            if (r7 != 0) goto L_0x006a
            java.util.HashMap r7 = new java.util.HashMap     // Catch:{ Throwable -> 0x0086 }
            r7.<init>()     // Catch:{ Throwable -> 0x0086 }
            java.lang.Long r3 = java.lang.Long.valueOf(r3)     // Catch:{ Throwable -> 0x0086 }
            r1.put(r3, r7)     // Catch:{ Throwable -> 0x0086 }
        L_0x006a:
            java.lang.Object r3 = r7.get(r5)     // Catch:{ Throwable -> 0x0086 }
            java.util.List r3 = (java.util.List) r3     // Catch:{ Throwable -> 0x0086 }
            if (r3 != 0) goto L_0x007a
            java.util.ArrayList r3 = new java.util.ArrayList     // Catch:{ Throwable -> 0x0086 }
            r3.<init>()     // Catch:{ Throwable -> 0x0086 }
            r7.put(r5, r3)     // Catch:{ Throwable -> 0x0086 }
        L_0x007a:
            r3.add(r6)     // Catch:{ Throwable -> 0x0086 }
            goto L_0x003a
        L_0x007e:
            if (r10 == 0) goto L_0x0083
            r10.close()
        L_0x0083:
            return r1
        L_0x0084:
            r1 = move-exception
            goto L_0x0088
        L_0x0086:
            r0 = move-exception
            throw r0     // Catch:{ all -> 0x0084 }
        L_0x0088:
            if (r10 == 0) goto L_0x0098
            if (r0 == 0) goto L_0x0095
            r10.close()     // Catch:{ Throwable -> 0x0090 }
            goto L_0x0098
        L_0x0090:
            r10 = move-exception
            r0.addSuppressed(r10)
            goto L_0x0098
        L_0x0095:
            r10.close()
        L_0x0098:
            throw r1
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.RecipePropertiesDBAdapter.getRecipePropertiesForFoodIds(java.util.List):java.util.Map");
    }
}
