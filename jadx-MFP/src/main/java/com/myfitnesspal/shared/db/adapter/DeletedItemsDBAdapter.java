package com.myfitnesspal.shared.db.adapter;

import android.content.Context;
import android.database.sqlite.SQLiteStatement;
import com.myfitnesspal.shared.db.DbConnectionManager;
import com.myfitnesspal.shared.util.Database;
import com.uacf.core.util.Ln;
import java.util.Date;
import javax.inject.Inject;

public class DeletedItemsDBAdapter {
    private static final String DATABASE_TABLE = "deleted_items";
    private static final String KEY_DELETED_AT = "deleted_at";
    private static final String KEY_ID = "id";
    private static final String KEY_IS_DESTROYED = "is_destroyed";
    private static final String KEY_ITEM_MASTER_ID = "item_master_id";
    private static final String KEY_ITEM_TYPE = "item_type";
    private static final String KEY_USER_ID = "user_id";
    private final Context context;

    @Inject
    public DeletedItemsDBAdapter(Context context2) {
        this.context = context2;
    }

    public void recordDeletedItemForUserId(long j, int i, long j2, boolean z) {
        try {
            SQLiteStatement preparedStatement = DbConnectionManager.preparedStatement(20);
            preparedStatement.bindLong(1, j);
            preparedStatement.bindLong(2, (long) i);
            preparedStatement.bindLong(3, j2);
            preparedStatement.bindNull(4);
            preparedStatement.bindLong(5, z ? 1 : 0);
            preparedStatement.bindString(6, Database.encodeDateAndTime(new Date()));
            preparedStatement.execute();
            preparedStatement.clearBindings();
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    public void purgeDeletedItemsRowsUpToId(long j) {
        String str = "id <= ?";
        try {
            DbConnectionManager.getDb(this.context).delete(DATABASE_TABLE, str, new String[]{String.valueOf(j)});
        } catch (Exception e) {
            Ln.e(e);
        }
    }

    /* JADX WARNING: Removed duplicated region for block: B:28:0x0094  */
    /* JADX WARNING: Removed duplicated region for block: B:32:0x009b  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public java.util.ArrayList<com.myfitnesspal.shared.model.v1.DatabaseObjectReference> fetchUnsyncedDeletedItemsForUserId(long r17, int r19) {
        /*
            r16 = this;
            r0 = r19
            r1 = 1
            r2 = 0
            java.lang.String r3 = "id"
            java.lang.String r4 = "item_type"
            java.lang.String r5 = "item_master_id"
            java.lang.String r6 = "is_destroyed"
            java.lang.String r7 = "deleted_at"
            java.lang.String[] r10 = new java.lang.String[]{r3, r4, r5, r6, r7}     // Catch:{ Exception -> 0x0087, all -> 0x0083 }
            java.lang.String r11 = "user_id= ? "
            java.lang.String[] r12 = new java.lang.String[r1]     // Catch:{ Exception -> 0x0087, all -> 0x0083 }
            java.lang.String r3 = java.lang.String.valueOf(r17)     // Catch:{ Exception -> 0x0087, all -> 0x0083 }
            r4 = 0
            r12[r4] = r3     // Catch:{ Exception -> 0x0087, all -> 0x0083 }
            java.lang.String r15 = " id asc"
            r3 = r16
            android.content.Context r5 = r3.context     // Catch:{ Exception -> 0x0081 }
            com.uacf.core.database.SQLiteDatabaseWrapper r8 = com.myfitnesspal.shared.db.DbConnectionManager.getDb(r5)     // Catch:{ Exception -> 0x0081 }
            java.lang.String r9 = "deleted_items"
            r13 = 0
            r14 = 0
            android.database.Cursor r2 = r8.query(r9, r10, r11, r12, r13, r14, r15)     // Catch:{ Exception -> 0x0081 }
            r2.moveToFirst()     // Catch:{ Exception -> 0x0081 }
            java.util.ArrayList r5 = new java.util.ArrayList     // Catch:{ Exception -> 0x0081 }
            r5.<init>(r0)     // Catch:{ Exception -> 0x0081 }
            int r6 = r2.getCount()     // Catch:{ Exception -> 0x0081 }
            if (r6 <= r0) goto L_0x003e
            goto L_0x0042
        L_0x003e:
            int r0 = r2.getCount()     // Catch:{ Exception -> 0x0081 }
        L_0x0042:
            r6 = 0
        L_0x0043:
            if (r6 >= r0) goto L_0x007b
            com.myfitnesspal.shared.model.v1.DatabaseObjectReference r7 = new com.myfitnesspal.shared.model.v1.DatabaseObjectReference     // Catch:{ Exception -> 0x0081 }
            r7.<init>()     // Catch:{ Exception -> 0x0081 }
            long r8 = r2.getLong(r4)     // Catch:{ Exception -> 0x0081 }
            r7.setReferenceId(r8)     // Catch:{ Exception -> 0x0081 }
            int r8 = r2.getInt(r1)     // Catch:{ Exception -> 0x0081 }
            r7.setItemType(r8)     // Catch:{ Exception -> 0x0081 }
            r8 = 2
            long r8 = r2.getLong(r8)     // Catch:{ Exception -> 0x0081 }
            r7.setMasterDatabaseId(r8)     // Catch:{ Exception -> 0x0081 }
            r8 = 3
            int r8 = r2.getInt(r8)     // Catch:{ Exception -> 0x0081 }
            if (r8 == 0) goto L_0x0069
            r8 = 1
            goto L_0x006a
        L_0x0069:
            r8 = 0
        L_0x006a:
            r7.setDestroyed(r8)     // Catch:{ Exception -> 0x0081 }
            r8 = 0
            r7.setLocalId(r8)     // Catch:{ Exception -> 0x0081 }
            r5.add(r7)     // Catch:{ Exception -> 0x0081 }
            r2.moveToNext()     // Catch:{ Exception -> 0x0081 }
            int r6 = r6 + 1
            goto L_0x0043
        L_0x007b:
            if (r2 == 0) goto L_0x0080
            r2.close()
        L_0x0080:
            return r5
        L_0x0081:
            r0 = move-exception
            goto L_0x008a
        L_0x0083:
            r0 = move-exception
            r3 = r16
            goto L_0x0099
        L_0x0087:
            r0 = move-exception
            r3 = r16
        L_0x008a:
            com.uacf.core.util.Ln.e(r0)     // Catch:{ all -> 0x0098 }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x0098 }
            r0.<init>(r1)     // Catch:{ all -> 0x0098 }
            if (r2 == 0) goto L_0x0097
            r2.close()
        L_0x0097:
            return r0
        L_0x0098:
            r0 = move-exception
        L_0x0099:
            if (r2 == 0) goto L_0x009e
            r2.close()
        L_0x009e:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.myfitnesspal.shared.db.adapter.DeletedItemsDBAdapter.fetchUnsyncedDeletedItemsForUserId(long, int):java.util.ArrayList");
    }
}
