package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.model.v1.WaterEntry;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;

public class WaterEntriesDBAdapter extends SessionDBAdapter {
    private static final String DATABASE_TABLE = "water_entries";
    public static final String KEY_CUPS = "cups";
    public static final String KEY_ENTRY_DATE = "entry_date";
    public static final String KEY_ID = "id";
    public static final String KEY_MASTER_ID = "master_id";
    public static final String KEY_MILLILITERS = "milliliters";
    public static final String KEY_UID = "uid";
    public static final String KEY_USER_ID = "user_id";
    private final Context context;
    private SQLiteStatement stmt;

    public WaterEntriesDBAdapter(Context context2) {
        this.context = context2;
    }

    /* JADX WARNING: type inference failed for: r1v0 */
    /* JADX WARNING: type inference failed for: r1v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r1v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r1v3, types: [com.myfitnesspal.shared.model.v1.WaterEntry] */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r6v1, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v3 */
    /* JADX WARNING: type inference failed for: r1v4 */
    /* JADX WARNING: type inference failed for: r7v4 */
    /* JADX WARNING: type inference failed for: r1v5 */
    /* JADX WARNING: type inference failed for: r1v6, types: [com.myfitnesspal.shared.model.v1.WaterEntry] */
    /* JADX WARNING: type inference failed for: r7v5, types: [com.myfitnesspal.shared.model.v1.WaterEntry] */
    /* JADX WARNING: type inference failed for: r1v7 */
    /* JADX WARNING: type inference failed for: r1v8 */
    /* JADX WARNING: type inference failed for: r1v9 */
    /* JADX WARNING: type inference failed for: r1v10 */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: Code restructure failed: missing block: B:13:0x0080, code lost:
        r0 = e;
        r7 = r7;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:16:0x0088, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0089, code lost:
        r1 = r6;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r7v3
  assigns: []
  uses: []
  mth insns count: 72
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
    	at jadx.api.JadxDecompiler.processClass(JadxDecompiler.java:311)
    	at jadx.api.JavaClass.decompile(JavaClass.java:62)
    	at jadx.api.JadxDecompiler.lambda$appendSourcesSave$0(JadxDecompiler.java:217)
     */
    /* JADX WARNING: Removed duplicated region for block: B:16:0x0088 A[ExcHandler: all (th java.lang.Throwable), Splitter:B:6:0x0041] */
    /* JADX WARNING: Removed duplicated region for block: B:30:0x009e  */
    /* JADX WARNING: Removed duplicated region for block: B:34:0x00a6  */
    /* JADX WARNING: Unknown variable types count: 8 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.WaterEntry fetchWaterEntryOnDate(java.util.Date r17) {
        /*
            r16 = this;
            r1 = 0
            java.lang.String r0 = com.myfitnesspal.shared.util.Database.encodeDate(r17)     // Catch:{ Exception -> 0x0095, all -> 0x0091 }
            java.lang.String r2 = "master_id"
            java.lang.String r3 = "id"
            java.lang.String r4 = "entry_date"
            java.lang.String r5 = "cups"
            java.lang.String r6 = "uid"
            java.lang.String r7 = "milliliters"
            java.lang.String[] r10 = new java.lang.String[]{r2, r3, r4, r5, r6, r7}     // Catch:{ Exception -> 0x0095, all -> 0x0091 }
            r2 = r16
            android.content.Context r3 = r2.context     // Catch:{ Exception -> 0x008f }
            com.uacf.core.database.SQLiteDatabaseWrapper r8 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r3)     // Catch:{ Exception -> 0x008f }
            java.lang.String r9 = "water_entries"
            java.lang.String r11 = "user_id= ? AND entry_date= ?"
            r3 = 2
            java.lang.String[] r12 = new java.lang.String[r3]     // Catch:{ Exception -> 0x008f }
            com.myfitnesspal.shared.service.session.Session r4 = r16.getSession()     // Catch:{ Exception -> 0x008f }
            com.myfitnesspal.shared.model.User r4 = r4.getUser()     // Catch:{ Exception -> 0x008f }
            long r4 = r4.getLocalId()     // Catch:{ Exception -> 0x008f }
            java.lang.String r4 = java.lang.String.valueOf(r4)     // Catch:{ Exception -> 0x008f }
            r5 = 0
            r12[r5] = r4     // Catch:{ Exception -> 0x008f }
            r4 = 1
            r12[r4] = r0     // Catch:{ Exception -> 0x008f }
            r13 = 0
            r14 = 0
            r15 = 0
            android.database.Cursor r6 = r8.query(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x008f }
            boolean r0 = r6.moveToFirst()     // Catch:{ Exception -> 0x008b, all -> 0x0088 }
            if (r0 == 0) goto L_0x0082
            com.myfitnesspal.shared.model.v1.WaterEntry r7 = new com.myfitnesspal.shared.model.v1.WaterEntry     // Catch:{ Exception -> 0x008b, all -> 0x0088 }
            r7.<init>()     // Catch:{ Exception -> 0x008b, all -> 0x0088 }
            long r0 = r6.getLong(r5)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setMasterDatabaseId(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            long r0 = r6.getLong(r4)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setLocalId(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            java.lang.String r0 = r6.getString(r3)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            java.util.Date r0 = com.myfitnesspal.shared.util.Database.decodeDateString(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setEntryDate(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r0 = 3
            int r0 = r6.getInt(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            float r0 = (float) r0     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setCups(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r0 = 4
            java.lang.String r0 = r6.getString(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setUid(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r0 = 5
            float r0 = r6.getFloat(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r7.setMilliliters(r0)     // Catch:{ Exception -> 0x0080, all -> 0x0088 }
            r1 = r7
            goto L_0x0082
        L_0x0080:
            r0 = move-exception
            goto L_0x008d
        L_0x0082:
            if (r6 == 0) goto L_0x00a2
            r6.close()
            goto L_0x00a2
        L_0x0088:
            r0 = move-exception
            r1 = r6
            goto L_0x00a4
        L_0x008b:
            r0 = move-exception
            r7 = r1
        L_0x008d:
            r1 = r6
            goto L_0x0099
        L_0x008f:
            r0 = move-exception
            goto L_0x0098
        L_0x0091:
            r0 = move-exception
            r2 = r16
            goto L_0x00a4
        L_0x0095:
            r0 = move-exception
            r2 = r16
        L_0x0098:
            r7 = r1
        L_0x0099:
            com.uacf.core.util.Ln.e(r0)     // Catch:{ all -> 0x00a3 }
            if (r1 == 0) goto L_0x00a1
            r1.close()
        L_0x00a1:
            r1 = r7
        L_0x00a2:
            return r1
        L_0x00a3:
            r0 = move-exception
        L_0x00a4:
            if (r1 == 0) goto L_0x00a9
            r1.close()
        L_0x00a9:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.WaterEntriesDBAdapter.fetchWaterEntryOnDate(java.util.Date):com.myfitnesspal.shared.model.v1.WaterEntry");
    }

    public void insertOrUpdateWaterEntry(WaterEntry waterEntry) {
        SQLiteStatement sQLiteStatement;
        try {
            long localId = getSession().getUser().getLocalId();
            String encodeDate = Database.encodeDate(waterEntry.getEntryDate());
            this.stmt = DbConnectionManager.preparedStatement(3);
            this.stmt.bindLong(1, localId);
            this.stmt.bindString(2, encodeDate);
            this.stmt.bindLong(3, waterEntry.masterDatabaseId);
            this.stmt.execute();
            this.stmt.clearBindings();
            this.stmt = DbConnectionManager.preparedStatement(4);
            if (waterEntry.hasMasterDatabaseId()) {
                this.stmt.bindLong(1, waterEntry.getMasterDatabaseId());
            } else {
                this.stmt.bindNull(1);
            }
            if (waterEntry.hasUid()) {
                this.stmt.bindString(2, waterEntry.getUid());
            } else {
                this.stmt.bindNull(2);
            }
            this.stmt.bindLong(3, localId);
            this.stmt.bindString(4, encodeDate);
            this.stmt.bindDouble(5, (double) waterEntry.getCups());
            this.stmt.bindDouble(6, (double) waterEntry.getMilliliters());
            waterEntry.setLocalId(this.stmt.executeInsert());
            sQLiteStatement = this.stmt;
            if (sQLiteStatement == null) {
                return;
            }
        } catch (Exception e) {
            Ln.e(e);
            sQLiteStatement = this.stmt;
            if (sQLiteStatement == null) {
                return;
            }
        } catch (Throwable th) {
            SQLiteStatement sQLiteStatement2 = this.stmt;
            if (sQLiteStatement2 != null) {
                sQLiteStatement2.clearBindings();
            }
            throw th;
        }
        sQLiteStatement.clearBindings();
    }

    /* JADX WARNING: Removed duplicated region for block: B:19:0x0065  */
    /* JADX WARNING: Removed duplicated region for block: B:23:0x006c  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public com.myfitnesspal.shared.model.v1.WaterEntry fetchWaterEntryById(long r6) {
        /*
            r5 = this;
            r0 = 0
            java.lang.String r1 = "select master_id, user_id, entry_date, cups, uid, milliliters from water_entries where id = ?"
            r2 = 1
            java.lang.String[] r2 = new java.lang.String[r2]     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            java.lang.String r3 = java.lang.String.valueOf(r6)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            r4 = 0
            r2[r4] = r3     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            android.content.Context r3 = r5.context     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            com.uacf.core.database.SQLiteDatabaseWrapper r3 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r3)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            android.database.Cursor r1 = r3.rawQuery(r1, r2)     // Catch:{ Exception -> 0x005e, all -> 0x005b }
            boolean r2 = r1.moveToFirst()     // Catch:{ Exception -> 0x0059 }
            if (r2 == 0) goto L_0x0053
            com.myfitnesspal.shared.model.v1.WaterEntry r2 = new com.myfitnesspal.shared.model.v1.WaterEntry     // Catch:{ Exception -> 0x0059 }
            r2.<init>()     // Catch:{ Exception -> 0x0059 }
            r2.setLocalId(r6)     // Catch:{ Exception -> 0x0059 }
            long r6 = r1.getLong(r4)     // Catch:{ Exception -> 0x0059 }
            r2.setMasterDatabaseId(r6)     // Catch:{ Exception -> 0x0059 }
            r6 = 2
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x0059 }
            java.util.Date r6 = com.myfitnesspal.shared.util.Database.decodeDateString(r6)     // Catch:{ Exception -> 0x0059 }
            r2.setEntryDate(r6)     // Catch:{ Exception -> 0x0059 }
            r6 = 3
            double r6 = r1.getDouble(r6)     // Catch:{ Exception -> 0x0059 }
            float r6 = (float) r6     // Catch:{ Exception -> 0x0059 }
            r2.setCups(r6)     // Catch:{ Exception -> 0x0059 }
            r6 = 4
            java.lang.String r6 = r1.getString(r6)     // Catch:{ Exception -> 0x0059 }
            r2.setUid(r6)     // Catch:{ Exception -> 0x0059 }
            r6 = 5
            double r6 = r1.getDouble(r6)     // Catch:{ Exception -> 0x0059 }
            float r6 = (float) r6     // Catch:{ Exception -> 0x0059 }
            r2.setMilliliters(r6)     // Catch:{ Exception -> 0x0059 }
            r0 = r2
        L_0x0053:
            if (r1 == 0) goto L_0x0058
            r1.close()
        L_0x0058:
            return r0
        L_0x0059:
            r6 = move-exception
            goto L_0x0060
        L_0x005b:
            r6 = move-exception
            r1 = r0
            goto L_0x006a
        L_0x005e:
            r6 = move-exception
            r1 = r0
        L_0x0060:
            com.uacf.core.util.Ln.e(r6)     // Catch:{ all -> 0x0069 }
            if (r1 == 0) goto L_0x0068
            r1.close()
        L_0x0068:
            return r0
        L_0x0069:
            r6 = move-exception
        L_0x006a:
            if (r1 == 0) goto L_0x006f
            r1.close()
        L_0x006f:
            throw r6
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.WaterEntriesDBAdapter.fetchWaterEntryById(long):com.myfitnesspal.shared.model.v1.WaterEntry");
    }
}
