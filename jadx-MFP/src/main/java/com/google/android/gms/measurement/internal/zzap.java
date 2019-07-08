package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabaseLockedException;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.VisibleForTesting;

@VisibleForTesting
final class zzap extends SQLiteOpenHelper {
    private final /* synthetic */ zzao zzalq;

    zzap(zzao zzao, Context context, String str) {
        this.zzalq = zzao;
        super(context, str, null, 1);
    }

    @WorkerThread
    public final void onDowngrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final void onUpgrade(SQLiteDatabase sQLiteDatabase, int i, int i2) {
    }

    @WorkerThread
    public final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        try {
            return super.getWritableDatabase();
        } catch (SQLiteDatabaseLockedException e) {
            throw e;
        } catch (SQLiteException unused) {
            this.zzalq.zzgt().zzjg().zzby("Opening the local database failed, dropping and recreating it");
            String str = "google_app_measurement_local.db";
            if (!this.zzalq.getContext().getDatabasePath(str).delete()) {
                this.zzalq.zzgt().zzjg().zzg("Failed to delete corrupted local db file", str);
            }
            try {
                return super.getWritableDatabase();
            } catch (SQLiteException e2) {
                this.zzalq.zzgt().zzjg().zzg("Failed to open local database. Events will bypass local storage", e2);
                return null;
            }
        }
    }

    /* JADX WARNING: type inference failed for: r0v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r0v5 */
    /* JADX WARNING: type inference failed for: r0v6 */
    /* JADX WARNING: Multi-variable type inference failed */
    /* JADX WARNING: Unknown variable types count: 1 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final void onOpen(android.database.sqlite.SQLiteDatabase r8) {
        /*
            r7 = this;
            int r0 = android.os.Build.VERSION.SDK_INT
            r1 = 15
            if (r0 >= r1) goto L_0x001d
            r0 = 0
            java.lang.String r1 = "PRAGMA journal_mode=memory"
            android.database.Cursor r0 = r8.rawQuery(r1, r0)     // Catch:{ all -> 0x0016 }
            r0.moveToFirst()     // Catch:{ all -> 0x0016 }
            if (r0 == 0) goto L_0x001d
            r0.close()
            goto L_0x001d
        L_0x0016:
            r8 = move-exception
            if (r0 == 0) goto L_0x001c
            r0.close()
        L_0x001c:
            throw r8
        L_0x001d:
            com.google.android.gms.measurement.internal.zzao r0 = r7.zzalq
            com.google.android.gms.measurement.internal.zzas r1 = r0.zzgt()
            java.lang.String r3 = "messages"
            java.lang.String r4 = "create table if not exists messages ( type INTEGER NOT NULL, entry BLOB NOT NULL)"
            java.lang.String r5 = "type,entry"
            r6 = 0
            r2 = r8
            com.google.android.gms.measurement.internal.zzx.zza(r1, r2, r3, r4, r5, r6)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzap.onOpen(android.database.sqlite.SQLiteDatabase):void");
    }

    @WorkerThread
    public final void onCreate(SQLiteDatabase sQLiteDatabase) {
        zzx.zza(this.zzalq.zzgt(), sQLiteDatabase);
    }
}
