package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.TrackedNutrient;
import com.uacf.core.util.Ln;

public class TrackedNutrientDbAdapter extends SessionDBAdapter {
    public static final String KEY_ID = "id";
    public static final String KEY_MASTER_ID = "master_id";
    public static final String KEY_NUTRIENT_NAME_ID = "nutrient_name_id";
    public static final String KEY_POSITION = "position";
    public static final String KEY_UID = "uid";
    public static final String KEY_USER_ID = "user_id";
    private final Context context;
    String queryString;
    SQLiteStatement stmt;

    public TrackedNutrientDbAdapter(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r0v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v2, types: [com.myfitnesspal.shared.model.v1.TrackedNutrient] */
    /* JADX WARNING: type inference failed for: r0v2 */
    /* JADX WARNING: type inference failed for: r2v1 */
    /* JADX WARNING: type inference failed for: r0v6, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r2v5 */
    /* JADX WARNING: type inference failed for: r1v3 */
    /* JADX WARNING: type inference failed for: r2v6 */
    /* JADX WARNING: type inference failed for: r1v4, types: [com.myfitnesspal.shared.model.v1.TrackedNutrient] */
    /* JADX WARNING: type inference failed for: r2v8, types: [com.myfitnesspal.shared.model.v1.TrackedNutrient] */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6 */
    /* JADX WARNING: type inference failed for: r0v7 */
    /* JADX WARNING: type inference failed for: r2v9 */
    /* JADX WARNING: type inference failed for: r2v10 */
    /* JADX WARNING: Code restructure failed: missing block: B:10:0x0063, code lost:
        r5 = e;
        r2 = r2;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x006b, code lost:
        r5 = th;
        r0 = r0;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:24:0x007b, code lost:
        r1.close();
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v5
  assigns: []
  uses: []
  mth insns count: 57
    	at jadx.core.dex.visitors.typeinference.TypeSearch.fillTypeCandidates(TypeSearch.java:237)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.typeinference.TypeSearch.run(TypeSearch.java:53)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runMultiVariableSearch(TypeInferenceVisitor.java:99)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:92)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:27)
    	at jadx.core.dex.visitors.DepthTraversal.lambda$visit$1(DepthTraversal.java:14)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.dex.visitors.DepthTraversal.visit(DepthTraversal.java:14)
    	at jadx.core.ProcessClass.process(ProcessClass.java:30)
    	at jadx.core.ProcessClass.lambda$processDependencies$0(ProcessClass.java:49)
    	at java.base/java.util.ArrayList.forEach(Unknown Source)
    	at jadx.core.ProcessClass.processDependencies(ProcessClass.java:49)
    	at jadx.core.ProcessClass.process(ProcessClass.java:35)
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:13:0x006b A[ExcHandler: all (th java.lang.Throwable), Splitter:B:3:0x001f] */
    /* JADX WARNING: Removed duplicated region for block: B:24:0x007b  */
    /* JADX WARNING: Removed duplicated region for block: B:27:0x0082  */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.TrackedNutrient fetchTrackedNutrientById(long r5) {
        /*
            r4 = this;
            r0 = 120(0x78, float:1.68E-43)
            r1 = 0
            java.lang.String r0 = com.myfitnesspal.shared.db.DbConnectionManager.queryString(r0)     // Catch:{ Exception -> 0x0074 }
            r4.queryString = r0     // Catch:{ Exception -> 0x0074 }
            r0 = 1
            java.lang.String[] r0 = new java.lang.String[r0]     // Catch:{ Exception -> 0x0074 }
            r2 = 0
            java.lang.String r3 = java.lang.String.valueOf(r5)     // Catch:{ Exception -> 0x0074 }
            r0[r2] = r3     // Catch:{ Exception -> 0x0074 }
            android.content.Context r2 = r4.context     // Catch:{ Exception -> 0x0074 }
            com.uacf.core.database.SQLiteDatabaseWrapper r2 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r2)     // Catch:{ Exception -> 0x0074 }
            java.lang.String r3 = r4.queryString     // Catch:{ Exception -> 0x0074 }
            android.database.Cursor r0 = r2.rawQuery(r3, r0)     // Catch:{ Exception -> 0x0074 }
            boolean r2 = r0.moveToFirst()     // Catch:{ Exception -> 0x006d, all -> 0x006b }
            if (r2 == 0) goto L_0x0065
            com.myfitnesspal.shared.model.v1.TrackedNutrient r2 = new com.myfitnesspal.shared.model.v1.TrackedNutrient     // Catch:{ Exception -> 0x006d, all -> 0x006b }
            r2.<init>()     // Catch:{ Exception -> 0x006d, all -> 0x006b }
            r2.setLocalId(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            java.lang.String r5 = "master_id"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            long r5 = r0.getLong(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            r2.setMasterDatabaseId(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            java.lang.String r5 = "uid"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            java.lang.String r5 = r0.getString(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            r2.setUid(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            java.lang.String r5 = "position"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            int r5 = r0.getInt(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            r2.setPosition(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            java.lang.String r5 = "nutrient_name_id"
            int r5 = r0.getColumnIndex(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            long r5 = r0.getLong(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            r2.setNutrientNameId(r5)     // Catch:{ Exception -> 0x0063, all -> 0x006b }
            r1 = r2
            goto L_0x0065
        L_0x0063:
            r5 = move-exception
            goto L_0x006f
        L_0x0065:
            if (r0 == 0) goto L_0x007f
            r0.close()
            goto L_0x007f
        L_0x006b:
            r5 = move-exception
            goto L_0x0080
        L_0x006d:
            r5 = move-exception
            r2 = r1
        L_0x006f:
            r1 = r0
            goto L_0x0076
        L_0x0071:
            r5 = move-exception
            r0 = r1
            goto L_0x0080
        L_0x0074:
            r5 = move-exception
            r2 = r1
        L_0x0076:
            com.uacf.core.util.Ln.e(r5)     // Catch:{ all -> 0x0071 }
            if (r1 == 0) goto L_0x007e
            r1.close()
        L_0x007e:
            r1 = r2
        L_0x007f:
            return r1
        L_0x0080:
            if (r0 == 0) goto L_0x0085
            r0.close()
        L_0x0085:
            throw r5
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.TrackedNutrientDbAdapter.fetchTrackedNutrientById(long):com.myfitnesspal.shared.model.v1.TrackedNutrient");
    }

    public void eraseTrackedNutrientWithMasterId(long j) {
        try {
            this.stmt = DbConnectionManager.preparedStatement(123);
            this.stmt.bindLong(1, j);
            this.stmt.execute();
            this.stmt.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void insertTrackedNutrient(TrackedNutrient trackedNutrient) {
        long localId = getSession().getUser().getLocalId();
        Cursor cursor = null;
        try {
            this.queryString = DbConnectionManager.queryString(122);
            boolean z = false;
            cursor = DbConnectionManager.getDb(this.context).rawQuery(this.queryString, new String[]{String.valueOf(localId), String.valueOf(trackedNutrient.getMasterDatabaseId())});
            if (cursor.moveToFirst()) {
                trackedNutrient.setLocalId(cursor.getLong(0));
                z = true;
            }
            if (!z) {
                this.stmt = DbConnectionManager.preparedStatement(125);
                if (trackedNutrient.hasMasterDatabaseId()) {
                    this.stmt.bindLong(1, trackedNutrient.getMasterDatabaseId());
                } else {
                    this.stmt.bindNull(1);
                }
                if (trackedNutrient.hasUid()) {
                    this.stmt.bindString(2, trackedNutrient.getUid());
                } else {
                    this.stmt.bindNull(2);
                }
                this.stmt.bindLong(3, localId);
                this.stmt.bindLong(4, (long) trackedNutrient.getPosition());
                this.stmt.bindLong(5, trackedNutrient.getNutrientNameId());
                this.stmt.executeInsert();
            }
            if (cursor == null) {
                return;
            }
        } catch (Exception e) {
            Ln.e(e);
            if (cursor == null) {
                return;
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        cursor.close();
    }
}
