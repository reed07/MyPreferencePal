package com.myfitnesspal.shared.db.table;

import android.content.ContentValues;
import com.myfitnesspal.shared.model.mapper.ApiJsonMapper;
import com.myfitnesspal.shared.model.v2.MfpRecipe;
import com.uacf.core.database.SQLiteDatabaseWrapper;
import javax.inject.Inject;

public class RecipesTable extends MfpDatabaseTableV2Impl {
    private static final String TABLE_NAME = "recipes";

    private static final class Columns {
        private static final String DATA = "data";
        private static final String ID = "id";
        private static final String NAME = "name";
        private static final String RECIPE_FOOD_ID = "recipe_food_id";
        private static final String USER_ID = "user_id";
        private static final String VERSION = "version";

        private Columns() {
        }
    }

    public void onCreate() {
    }

    @Inject
    public RecipesTable(SQLiteDatabaseWrapper sQLiteDatabaseWrapper) {
        super(sQLiteDatabaseWrapper, "recipes");
    }

    public void onUpgrade(int i, int i2) {
        if (shouldRunUpgrade(45, i, i2)) {
            createTable();
        }
    }

    private void createTable() {
        createTable("id TEXT", "version TEXT", "name TEXT", "user_id TEXT", "recipe_food_id INTEGER", "data TEXT", String.format("PRIMARY KEY (%s, %s)", new Object[]{"id", "version"}));
    }

    /* JADX WARNING: Code restructure failed: missing block: B:11:0x004d, code lost:
        r0 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:15:0x0051, code lost:
        if (r9 != null) goto L_0x0053;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0053, code lost:
        if (r10 != null) goto L_0x0055;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:?, code lost:
        r9.close();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x0059, code lost:
        r9 = move-exception;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x005a, code lost:
        r10.addSuppressed(r9);
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x005e, code lost:
        r9.close();
     */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v2.MfpRecipe getRecipeForFoodId(long r9) {
        /*
            r8 = this;
            com.uacf.core.database.SQLiteDatabaseWrapper r0 = r8.getDatabase()
            java.lang.String r1 = "recipes"
            java.lang.String r2 = "data"
            java.lang.String[] r2 = new java.lang.String[]{r2}
            java.lang.String r3 = "recipe_food_id=?"
            r4 = 1
            java.lang.String[] r4 = new java.lang.String[r4]
            java.lang.Long r9 = java.lang.Long.valueOf(r9)
            java.lang.String r9 = com.uacf.core.util.Strings.toString(r9)
            r10 = 0
            r4[r10] = r9
            r5 = 0
            r6 = 0
            r7 = 0
            android.database.Cursor r9 = r0.query(r1, r2, r3, r4, r5, r6, r7)
            r10 = 0
            boolean r0 = r9.moveToFirst()     // Catch:{ Throwable -> 0x004f }
            if (r0 == 0) goto L_0x0047
            java.lang.String r0 = "data"
            int r0 = r9.getColumnIndex(r0)     // Catch:{ Throwable -> 0x004f }
            java.lang.String r0 = r9.getString(r0)     // Catch:{ Throwable -> 0x004f }
            com.myfitnesspal.shared.model.mapper.ApiJsonMapper r1 = new com.myfitnesspal.shared.model.mapper.ApiJsonMapper     // Catch:{ Throwable -> 0x004f }
            r1.<init>()     // Catch:{ Throwable -> 0x004f }
            java.lang.Class<com.myfitnesspal.shared.model.v2.MfpRecipe> r2 = com.myfitnesspal.shared.model.v2.MfpRecipe.class
            java.lang.Object r0 = r1.tryMapFrom(r0, r2)     // Catch:{ Throwable -> 0x004f }
            com.myfitnesspal.shared.model.v2.MfpRecipe r0 = (com.myfitnesspal.shared.model.v2.MfpRecipe) r0     // Catch:{ Throwable -> 0x004f }
            if (r9 == 0) goto L_0x0046
            r9.close()
        L_0x0046:
            return r0
        L_0x0047:
            if (r9 == 0) goto L_0x004c
            r9.close()
        L_0x004c:
            return r10
        L_0x004d:
            r0 = move-exception
            goto L_0x0051
        L_0x004f:
            r10 = move-exception
            throw r10     // Catch:{ all -> 0x004d }
        L_0x0051:
            if (r9 == 0) goto L_0x0061
            if (r10 == 0) goto L_0x005e
            r9.close()     // Catch:{ Throwable -> 0x0059 }
            goto L_0x0061
        L_0x0059:
            r9 = move-exception
            r10.addSuppressed(r9)
            goto L_0x0061
        L_0x005e:
            r9.close()
        L_0x0061:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.table.RecipesTable.getRecipeForFoodId(long):com.myfitnesspal.shared.model.v2.MfpRecipe");
    }

    public void insertRecipe(MfpRecipe mfpRecipe, long j) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("id", mfpRecipe.getId());
        contentValues.put("version", mfpRecipe.getVersion());
        contentValues.put("name", mfpRecipe.getName());
        contentValues.put("recipe_food_id", Long.valueOf(j));
        contentValues.put("user_id", mfpRecipe.getUserId());
        contentValues.put("data", new ApiJsonMapper().reverseMap((Object) mfpRecipe));
        insertData(contentValues);
    }

    public void deleteRecipeForFoodId(long j) {
        deleteData("recipe_food_id=?", Long.valueOf(j));
    }
}
