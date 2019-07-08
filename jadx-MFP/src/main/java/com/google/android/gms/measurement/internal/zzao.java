package com.google.android.gms.measurement.internal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzao extends zzf {
    private final zzap zzalo = new zzap(this, getContext(), "google_app_measurement_local.db");
    private boolean zzalp;

    zzao(zzbw zzbw) {
        super(zzbw);
    }

    /* access modifiers changed from: protected */
    public final boolean zzgy() {
        return false;
    }

    @WorkerThread
    public final void resetAnalyticsData() {
        zzgg();
        zzaf();
        try {
            int delete = getWritableDatabase().delete("messages", null, null) + 0;
            if (delete > 0) {
                zzgt().zzjo().zzg("Reset local analytics data. records", Integer.valueOf(delete));
            }
        } catch (SQLiteException e) {
            zzgt().zzjg().zzg("Error resetting local analytics data. error", e);
        }
    }

    /* JADX WARNING: type inference failed for: r2v0 */
    /* JADX WARNING: type inference failed for: r2v1, types: [boolean, int] */
    /* JADX WARNING: type inference failed for: r7v0 */
    /* JADX WARNING: type inference failed for: r12v0, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v0, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v1 */
    /* JADX WARNING: type inference failed for: r7v1 */
    /* JADX WARNING: type inference failed for: r12v1 */
    /* JADX WARNING: type inference failed for: r2v4 */
    /* JADX WARNING: type inference failed for: r9v2, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r7v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v3 */
    /* JADX WARNING: type inference failed for: r9v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r7v3, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r9v5 */
    /* JADX WARNING: type inference failed for: r12v2, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v4, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r9v6 */
    /* JADX WARNING: type inference failed for: r12v3 */
    /* JADX WARNING: type inference failed for: r9v7 */
    /* JADX WARNING: type inference failed for: r12v4 */
    /* JADX WARNING: type inference failed for: r9v8, types: [android.database.sqlite.SQLiteDatabase] */
    /* JADX WARNING: type inference failed for: r12v5 */
    /* JADX WARNING: type inference failed for: r7v5 */
    /* JADX WARNING: type inference failed for: r12v6 */
    /* JADX WARNING: type inference failed for: r12v7, types: [android.database.Cursor] */
    /* JADX WARNING: type inference failed for: r7v6 */
    /* JADX WARNING: type inference failed for: r7v7 */
    /* JADX WARNING: type inference failed for: r2v11 */
    /* JADX WARNING: type inference failed for: r7v8 */
    /* JADX WARNING: type inference failed for: r7v9 */
    /* JADX WARNING: type inference failed for: r7v10 */
    /* JADX WARNING: type inference failed for: r7v11 */
    /* JADX WARNING: type inference failed for: r7v12 */
    /* JADX WARNING: type inference failed for: r9v9 */
    /* JADX WARNING: type inference failed for: r2v12 */
    /* JADX WARNING: type inference failed for: r9v10 */
    /* JADX WARNING: type inference failed for: r9v11 */
    /* JADX WARNING: type inference failed for: r7v13 */
    /* JADX WARNING: type inference failed for: r7v14 */
    /* JADX WARNING: type inference failed for: r9v12 */
    /* JADX WARNING: type inference failed for: r9v13 */
    /* JADX WARNING: type inference failed for: r7v15 */
    /* JADX WARNING: type inference failed for: r7v16 */
    /* JADX WARNING: type inference failed for: r12v8 */
    /* JADX WARNING: type inference failed for: r9v14 */
    /* JADX WARNING: type inference failed for: r9v15 */
    /* JADX WARNING: type inference failed for: r9v16 */
    /* JADX WARNING: type inference failed for: r9v17 */
    /* JADX WARNING: type inference failed for: r9v18 */
    /* JADX WARNING: type inference failed for: r12v9 */
    /* JADX WARNING: type inference failed for: r12v10 */
    /* JADX WARNING: type inference failed for: r12v11 */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v1, types: [boolean, int]
  assigns: []
  uses: [?[int, short, byte, char], int, boolean]
  mth insns count: 161
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
    /* JADX WARNING: Removed duplicated region for block: B:49:0x00c6 A[SYNTHETIC, Splitter:B:49:0x00c6] */
    /* JADX WARNING: Removed duplicated region for block: B:55:0x00e0  */
    /* JADX WARNING: Removed duplicated region for block: B:57:0x00e5  */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x00f5  */
    /* JADX WARNING: Removed duplicated region for block: B:70:0x00fa  */
    /* JADX WARNING: Removed duplicated region for block: B:76:0x0111  */
    /* JADX WARNING: Removed duplicated region for block: B:78:0x0116  */
    /* JADX WARNING: Removed duplicated region for block: B:83:0x0123  */
    /* JADX WARNING: Removed duplicated region for block: B:85:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:93:0x0119 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:95:0x0119 A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:97:0x0119 A[SYNTHETIC] */
    /* JADX WARNING: Unknown variable types count: 19 */
    @android.support.annotation.WorkerThread
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final boolean zza(int r17, byte[] r18) {
        /*
            r16 = this;
            r1 = r16
            r16.zzgg()
            r16.zzaf()
            boolean r0 = r1.zzalp
            r2 = 0
            if (r0 == 0) goto L_0x000e
            return r2
        L_0x000e:
            android.content.ContentValues r3 = new android.content.ContentValues
            r3.<init>()
            java.lang.String r0 = "type"
            java.lang.Integer r4 = java.lang.Integer.valueOf(r17)
            r3.put(r0, r4)
            java.lang.String r0 = "entry"
            r4 = r18
            r3.put(r0, r4)
            r4 = 5
            r5 = 0
            r6 = 5
        L_0x0026:
            if (r5 >= r4) goto L_0x012c
            r7 = 0
            r8 = 1
            android.database.sqlite.SQLiteDatabase r9 = r16.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x00fe, SQLiteDatabaseLockedException -> 0x00ec, SQLiteException -> 0x00c2, all -> 0x00be }
            if (r9 != 0) goto L_0x0038
            r1.zzalp = r8     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            if (r9 == 0) goto L_0x0037
            r9.close()
        L_0x0037:
            return r2
        L_0x0038:
            r9.beginTransaction()     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            r10 = 0
            java.lang.String r0 = "select count(1) from messages"
            android.database.Cursor r12 = r9.rawQuery(r0, r7)     // Catch:{ SQLiteFullException -> 0x00bc, SQLiteDatabaseLockedException -> 0x00ed, SQLiteException -> 0x00b8 }
            if (r12 == 0) goto L_0x0059
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            if (r0 == 0) goto L_0x0059
            long r10 = r12.getLong(r2)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            goto L_0x0059
        L_0x0050:
            r0 = move-exception
            goto L_0x0121
        L_0x0053:
            r0 = move-exception
            goto L_0x00ba
        L_0x0055:
            r0 = move-exception
            r7 = r12
            goto L_0x0100
        L_0x0059:
            r13 = 100000(0x186a0, double:4.94066E-319)
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 < 0) goto L_0x00a0
            com.google.android.gms.measurement.internal.zzas r0 = r16.zzgt()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = "Data loss, local db full"
            r0.zzby(r15)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r13 = r13 - r10
            r10 = 1
            long r13 = r13 + r10
            java.lang.String r0 = "messages"
            java.lang.String r10 = "rowid in (select rowid from messages order by rowid asc limit ?)"
            java.lang.String[] r11 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = java.lang.Long.toString(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r11[r2] = r15     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            int r0 = r9.delete(r0, r10, r11)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r10 = (long) r0     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            int r0 = (r10 > r13 ? 1 : (r10 == r13 ? 0 : -1))
            if (r0 == 0) goto L_0x00a0
            com.google.android.gms.measurement.internal.zzas r0 = r16.zzgt()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.String r15 = "Different delete count than expected in local db. expected, received, difference"
            java.lang.Long r4 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            java.lang.Long r2 = java.lang.Long.valueOf(r10)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            long r13 = r13 - r10
            java.lang.Long r10 = java.lang.Long.valueOf(r13)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r0.zzd(r15, r4, r2, r10)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
        L_0x00a0:
            java.lang.String r0 = "messages"
            r9.insertOrThrow(r0, r7, r3)     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r9.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            r9.endTransaction()     // Catch:{ SQLiteFullException -> 0x0055, SQLiteDatabaseLockedException -> 0x00b6, SQLiteException -> 0x0053, all -> 0x0050 }
            if (r12 == 0) goto L_0x00b0
            r12.close()
        L_0x00b0:
            if (r9 == 0) goto L_0x00b5
            r9.close()
        L_0x00b5:
            return r8
        L_0x00b6:
            r7 = r12
            goto L_0x00ed
        L_0x00b8:
            r0 = move-exception
            r12 = r7
        L_0x00ba:
            r7 = r9
            goto L_0x00c4
        L_0x00bc:
            r0 = move-exception
            goto L_0x0100
        L_0x00be:
            r0 = move-exception
            r9 = r7
            r12 = r9
            goto L_0x0121
        L_0x00c2:
            r0 = move-exception
            r12 = r7
        L_0x00c4:
            if (r7 == 0) goto L_0x00cf
            boolean r2 = r7.inTransaction()     // Catch:{ all -> 0x00e9 }
            if (r2 == 0) goto L_0x00cf
            r7.endTransaction()     // Catch:{ all -> 0x00e9 }
        L_0x00cf:
            com.google.android.gms.measurement.internal.zzas r2 = r16.zzgt()     // Catch:{ all -> 0x00e9 }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()     // Catch:{ all -> 0x00e9 }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzg(r4, r0)     // Catch:{ all -> 0x00e9 }
            r1.zzalp = r8     // Catch:{ all -> 0x00e9 }
            if (r12 == 0) goto L_0x00e3
            r12.close()
        L_0x00e3:
            if (r7 == 0) goto L_0x0119
            r7.close()
            goto L_0x0119
        L_0x00e9:
            r0 = move-exception
            r9 = r7
            goto L_0x0121
        L_0x00ec:
            r9 = r7
        L_0x00ed:
            long r10 = (long) r6
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x011f }
            int r6 = r6 + 20
            if (r7 == 0) goto L_0x00f8
            r7.close()
        L_0x00f8:
            if (r9 == 0) goto L_0x0119
            r9.close()
            goto L_0x0119
        L_0x00fe:
            r0 = move-exception
            r9 = r7
        L_0x0100:
            com.google.android.gms.measurement.internal.zzas r2 = r16.zzgt()     // Catch:{ all -> 0x011f }
            com.google.android.gms.measurement.internal.zzau r2 = r2.zzjg()     // Catch:{ all -> 0x011f }
            java.lang.String r4 = "Error writing entry to local database"
            r2.zzg(r4, r0)     // Catch:{ all -> 0x011f }
            r1.zzalp = r8     // Catch:{ all -> 0x011f }
            if (r7 == 0) goto L_0x0114
            r7.close()
        L_0x0114:
            if (r9 == 0) goto L_0x0119
            r9.close()
        L_0x0119:
            int r5 = r5 + 1
            r2 = 0
            r4 = 5
            goto L_0x0026
        L_0x011f:
            r0 = move-exception
            r12 = r7
        L_0x0121:
            if (r12 == 0) goto L_0x0126
            r12.close()
        L_0x0126:
            if (r9 == 0) goto L_0x012b
            r9.close()
        L_0x012b:
            throw r0
        L_0x012c:
            com.google.android.gms.measurement.internal.zzas r0 = r16.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r2 = "Failed to write entry to local database"
            r0.zzby(r2)
            r2 = 0
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzao.zza(int, byte[]):boolean");
    }

    public final boolean zza(zzag zzag) {
        Parcel obtain = Parcel.obtain();
        zzag.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(0, marshall);
        }
        zzgt().zzjj().zzby("Event is too long for local database. Sending event directly to service");
        return false;
    }

    public final boolean zza(zzfu zzfu) {
        Parcel obtain = Parcel.obtain();
        zzfu.writeToParcel(obtain, 0);
        byte[] marshall = obtain.marshall();
        obtain.recycle();
        if (marshall.length <= 131072) {
            return zza(1, marshall);
        }
        zzgt().zzjj().zzby("User property too long for local database. Sending directly to service");
        return false;
    }

    public final boolean zzc(zzo zzo) {
        zzgr();
        byte[] zza = zzfx.zza((Parcelable) zzo);
        if (zza.length <= 131072) {
            return zza(2, zza);
        }
        zzgt().zzjj().zzby("Conditional user property too long for local database. Sending directly to service");
        return false;
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(4:55|56|57|58) */
    /* JADX WARNING: Can't wrap try/catch for region: R(4:70|71|72|73) */
    /* JADX WARNING: Can't wrap try/catch for region: R(5:42|43|44|45|160) */
    /* JADX WARNING: Code restructure failed: missing block: B:17:0x0039, code lost:
        r0 = th;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:18:0x003a, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:19:0x003e, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:20:0x003f, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:21:0x0043, code lost:
        r0 = e;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:22:0x0044, code lost:
        r9 = null;
        r4 = r15;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:43:?, code lost:
        zzgt().zzjg().zzby("Failed to load event from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:45:?, code lost:
        r12.recycle();
     */
    /* JADX WARNING: Code restructure failed: missing block: B:56:?, code lost:
        zzgt().zzjg().zzby("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:58:?, code lost:
        r12.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:71:?, code lost:
        zzgt().zzjg().zzby("Failed to load user property from local database");
     */
    /* JADX WARNING: Code restructure failed: missing block: B:73:?, code lost:
        r12.recycle();
        r0 = null;
     */
    /* JADX WARNING: Code restructure failed: missing block: B:99:0x0178, code lost:
        r4 = r15;
     */
    /* JADX WARNING: Failed to process nested try/catch */
    /* JADX WARNING: Missing exception handler attribute for start block: B:42:0x009f */
    /* JADX WARNING: Missing exception handler attribute for start block: B:55:0x00cf */
    /* JADX WARNING: Missing exception handler attribute for start block: B:70:0x0105 */
    /* JADX WARNING: Removed duplicated region for block: B:100:? A[ExcHandler: SQLiteDatabaseLockedException (unused android.database.sqlite.SQLiteDatabaseLockedException), SYNTHETIC, Splitter:B:12:0x0031] */
    /* JADX WARNING: Removed duplicated region for block: B:111:0x0188 A[SYNTHETIC, Splitter:B:111:0x0188] */
    /* JADX WARNING: Removed duplicated region for block: B:117:0x01a2  */
    /* JADX WARNING: Removed duplicated region for block: B:119:0x01a7  */
    /* JADX WARNING: Removed duplicated region for block: B:128:0x01b5  */
    /* JADX WARNING: Removed duplicated region for block: B:130:0x01ba  */
    /* JADX WARNING: Removed duplicated region for block: B:136:0x01d2  */
    /* JADX WARNING: Removed duplicated region for block: B:138:0x01d7  */
    /* JADX WARNING: Removed duplicated region for block: B:142:0x01e2  */
    /* JADX WARNING: Removed duplicated region for block: B:144:0x01e7  */
    /* JADX WARNING: Removed duplicated region for block: B:152:0x01da A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:154:0x01da A[SYNTHETIC] */
    /* JADX WARNING: Removed duplicated region for block: B:156:0x01da A[SYNTHETIC] */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public final java.util.List<com.google.android.gms.common.internal.safeparcel.AbstractSafeParcelable> zzr(int r19) {
        /*
            r18 = this;
            r1 = r18
            r18.zzaf()
            r18.zzgg()
            boolean r0 = r1.zzalp
            r2 = 0
            if (r0 == 0) goto L_0x000e
            return r2
        L_0x000e:
            java.util.ArrayList r3 = new java.util.ArrayList
            r3.<init>()
            android.content.Context r0 = r18.getContext()
            java.lang.String r4 = "google_app_measurement_local.db"
            java.io.File r0 = r0.getDatabasePath(r4)
            boolean r0 = r0.exists()
            if (r0 != 0) goto L_0x0024
            return r3
        L_0x0024:
            r4 = 5
            r5 = 0
            r6 = 0
            r7 = 5
        L_0x0028:
            if (r6 >= r4) goto L_0x01eb
            r8 = 1
            android.database.sqlite.SQLiteDatabase r15 = r18.getWritableDatabase()     // Catch:{ SQLiteFullException -> 0x01be, SQLiteDatabaseLockedException -> 0x01ab, SQLiteException -> 0x0183, all -> 0x017f }
            if (r15 != 0) goto L_0x0048
            r1.zzalp = r8     // Catch:{ SQLiteFullException -> 0x0043, SQLiteDatabaseLockedException -> 0x0178, SQLiteException -> 0x003e, all -> 0x0039 }
            if (r15 == 0) goto L_0x0038
            r15.close()
        L_0x0038:
            return r2
        L_0x0039:
            r0 = move-exception
            r9 = r2
            r4 = r15
            goto L_0x01e0
        L_0x003e:
            r0 = move-exception
            r9 = r2
            r4 = r15
            goto L_0x0186
        L_0x0043:
            r0 = move-exception
            r9 = r2
            r4 = r15
            goto L_0x01c1
        L_0x0048:
            r15.beginTransaction()     // Catch:{ SQLiteFullException -> 0x017b, SQLiteDatabaseLockedException -> 0x0178, SQLiteException -> 0x0174, all -> 0x016f }
            java.lang.String r10 = "messages"
            java.lang.String r0 = "rowid"
            java.lang.String r9 = "type"
            java.lang.String r11 = "entry"
            java.lang.String[] r11 = new java.lang.String[]{r0, r9, r11}     // Catch:{ SQLiteFullException -> 0x017b, SQLiteDatabaseLockedException -> 0x0178, SQLiteException -> 0x0174, all -> 0x016f }
            r12 = 0
            r13 = 0
            r14 = 0
            r0 = 0
            java.lang.String r16 = "rowid asc"
            r9 = 100
            java.lang.String r17 = java.lang.Integer.toString(r9)     // Catch:{ SQLiteFullException -> 0x017b, SQLiteDatabaseLockedException -> 0x0178, SQLiteException -> 0x0174, all -> 0x016f }
            r9 = r15
            r4 = r15
            r15 = r0
            android.database.Cursor r9 = r9.query(r10, r11, r12, r13, r14, r15, r16, r17)     // Catch:{ SQLiteFullException -> 0x016d, SQLiteDatabaseLockedException -> 0x0179, SQLiteException -> 0x016b, all -> 0x0169 }
            r10 = -1
        L_0x006c:
            boolean r0 = r9.moveToNext()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            if (r0 == 0) goto L_0x0130
            long r10 = r9.getLong(r5)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r0 = r9.getInt(r8)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            r12 = 2
            byte[] r13 = r9.getBlob(r12)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            if (r0 != 0) goto L_0x00b4
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r0 = r13.length     // Catch:{ ParseException -> 0x009f }
            r12.unmarshall(r13, r5, r0)     // Catch:{ ParseException -> 0x009f }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x009f }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzag> r0 = com.google.android.gms.measurement.internal.zzag.CREATOR     // Catch:{ ParseException -> 0x009f }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x009f }
            com.google.android.gms.measurement.internal.zzag r0 = (com.google.android.gms.measurement.internal.zzag) r0     // Catch:{ ParseException -> 0x009f }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            if (r0 == 0) goto L_0x006c
            r3.add(r0)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x006c
        L_0x009d:
            r0 = move-exception
            goto L_0x00b0
        L_0x009f:
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()     // Catch:{ all -> 0x009d }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x009d }
            java.lang.String r13 = "Failed to load event from local database"
            r0.zzby(r13)     // Catch:{ all -> 0x009d }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x006c
        L_0x00b0:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
        L_0x00b4:
            if (r0 != r8) goto L_0x00ea
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r0 = r13.length     // Catch:{ ParseException -> 0x00cf }
            r12.unmarshall(r13, r5, r0)     // Catch:{ ParseException -> 0x00cf }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x00cf }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzfu> r0 = com.google.android.gms.measurement.internal.zzfu.CREATOR     // Catch:{ ParseException -> 0x00cf }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x00cf }
            com.google.android.gms.measurement.internal.zzfu r0 = (com.google.android.gms.measurement.internal.zzfu) r0     // Catch:{ ParseException -> 0x00cf }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x00e0
        L_0x00cd:
            r0 = move-exception
            goto L_0x00e6
        L_0x00cf:
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()     // Catch:{ all -> 0x00cd }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x00cd }
            java.lang.String r13 = "Failed to load user property from local database"
            r0.zzby(r13)     // Catch:{ all -> 0x00cd }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            r0 = r2
        L_0x00e0:
            if (r0 == 0) goto L_0x006c
            r3.add(r0)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x006c
        L_0x00e6:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
        L_0x00ea:
            if (r0 != r12) goto L_0x0121
            android.os.Parcel r12 = android.os.Parcel.obtain()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r0 = r13.length     // Catch:{ ParseException -> 0x0105 }
            r12.unmarshall(r13, r5, r0)     // Catch:{ ParseException -> 0x0105 }
            r12.setDataPosition(r5)     // Catch:{ ParseException -> 0x0105 }
            android.os.Parcelable$Creator<com.google.android.gms.measurement.internal.zzo> r0 = com.google.android.gms.measurement.internal.zzo.CREATOR     // Catch:{ ParseException -> 0x0105 }
            java.lang.Object r0 = r0.createFromParcel(r12)     // Catch:{ ParseException -> 0x0105 }
            com.google.android.gms.measurement.internal.zzo r0 = (com.google.android.gms.measurement.internal.zzo) r0     // Catch:{ ParseException -> 0x0105 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x0116
        L_0x0103:
            r0 = move-exception
            goto L_0x011d
        L_0x0105:
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()     // Catch:{ all -> 0x0103 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ all -> 0x0103 }
            java.lang.String r13 = "Failed to load user property from local database"
            r0.zzby(r13)     // Catch:{ all -> 0x0103 }
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            r0 = r2
        L_0x0116:
            if (r0 == 0) goto L_0x006c
            r3.add(r0)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x006c
        L_0x011d:
            r12.recycle()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            throw r0     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
        L_0x0121:
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            java.lang.String r12 = "Unknown record type in local database"
            r0.zzby(r12)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            goto L_0x006c
        L_0x0130:
            java.lang.String r0 = "messages"
            java.lang.String r12 = "rowid <= ?"
            java.lang.String[] r13 = new java.lang.String[r8]     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            java.lang.String r10 = java.lang.Long.toString(r10)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            r13[r5] = r10     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r0 = r4.delete(r0, r12, r13)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            int r10 = r3.size()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            if (r0 >= r10) goto L_0x0153
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjg()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            java.lang.String r10 = "Fewer entries removed from local database than expected"
            r0.zzby(r10)     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
        L_0x0153:
            r4.setTransactionSuccessful()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            r4.endTransaction()     // Catch:{ SQLiteFullException -> 0x0166, SQLiteDatabaseLockedException -> 0x01ad, SQLiteException -> 0x0164 }
            if (r9 == 0) goto L_0x015e
            r9.close()
        L_0x015e:
            if (r4 == 0) goto L_0x0163
            r4.close()
        L_0x0163:
            return r3
        L_0x0164:
            r0 = move-exception
            goto L_0x0186
        L_0x0166:
            r0 = move-exception
            goto L_0x01c1
        L_0x0169:
            r0 = move-exception
            goto L_0x0171
        L_0x016b:
            r0 = move-exception
            goto L_0x0176
        L_0x016d:
            r0 = move-exception
            goto L_0x017d
        L_0x016f:
            r0 = move-exception
            r4 = r15
        L_0x0171:
            r9 = r2
            goto L_0x01e0
        L_0x0174:
            r0 = move-exception
            r4 = r15
        L_0x0176:
            r9 = r2
            goto L_0x0186
        L_0x0178:
            r4 = r15
        L_0x0179:
            r9 = r2
            goto L_0x01ad
        L_0x017b:
            r0 = move-exception
            r4 = r15
        L_0x017d:
            r9 = r2
            goto L_0x01c1
        L_0x017f:
            r0 = move-exception
            r4 = r2
            r9 = r4
            goto L_0x01e0
        L_0x0183:
            r0 = move-exception
            r4 = r2
            r9 = r4
        L_0x0186:
            if (r4 == 0) goto L_0x0191
            boolean r10 = r4.inTransaction()     // Catch:{ all -> 0x01df }
            if (r10 == 0) goto L_0x0191
            r4.endTransaction()     // Catch:{ all -> 0x01df }
        L_0x0191:
            com.google.android.gms.measurement.internal.zzas r10 = r18.zzgt()     // Catch:{ all -> 0x01df }
            com.google.android.gms.measurement.internal.zzau r10 = r10.zzjg()     // Catch:{ all -> 0x01df }
            java.lang.String r11 = "Error reading entries from local database"
            r10.zzg(r11, r0)     // Catch:{ all -> 0x01df }
            r1.zzalp = r8     // Catch:{ all -> 0x01df }
            if (r9 == 0) goto L_0x01a5
            r9.close()
        L_0x01a5:
            if (r4 == 0) goto L_0x01da
            r4.close()
            goto L_0x01da
        L_0x01ab:
            r4 = r2
            r9 = r4
        L_0x01ad:
            long r10 = (long) r7
            android.os.SystemClock.sleep(r10)     // Catch:{ all -> 0x01df }
            int r7 = r7 + 20
            if (r9 == 0) goto L_0x01b8
            r9.close()
        L_0x01b8:
            if (r4 == 0) goto L_0x01da
            r4.close()
            goto L_0x01da
        L_0x01be:
            r0 = move-exception
            r4 = r2
            r9 = r4
        L_0x01c1:
            com.google.android.gms.measurement.internal.zzas r10 = r18.zzgt()     // Catch:{ all -> 0x01df }
            com.google.android.gms.measurement.internal.zzau r10 = r10.zzjg()     // Catch:{ all -> 0x01df }
            java.lang.String r11 = "Error reading entries from local database"
            r10.zzg(r11, r0)     // Catch:{ all -> 0x01df }
            r1.zzalp = r8     // Catch:{ all -> 0x01df }
            if (r9 == 0) goto L_0x01d5
            r9.close()
        L_0x01d5:
            if (r4 == 0) goto L_0x01da
            r4.close()
        L_0x01da:
            int r6 = r6 + 1
            r4 = 5
            goto L_0x0028
        L_0x01df:
            r0 = move-exception
        L_0x01e0:
            if (r9 == 0) goto L_0x01e5
            r9.close()
        L_0x01e5:
            if (r4 == 0) goto L_0x01ea
            r4.close()
        L_0x01ea:
            throw r0
        L_0x01eb:
            com.google.android.gms.measurement.internal.zzas r0 = r18.zzgt()
            com.google.android.gms.measurement.internal.zzau r0 = r0.zzjj()
            java.lang.String r3 = "Failed to read events from database in reasonable time"
            r0.zzby(r3)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.measurement.internal.zzao.zzr(int):java.util.List");
    }

    @WorkerThread
    @VisibleForTesting
    private final SQLiteDatabase getWritableDatabase() throws SQLiteException {
        if (this.zzalp) {
            return null;
        }
        SQLiteDatabase writableDatabase = this.zzalo.getWritableDatabase();
        if (writableDatabase != null) {
            return writableDatabase;
        }
        this.zzalp = true;
        return null;
    }

    public final /* bridge */ /* synthetic */ void zzgf() {
        super.zzgf();
    }

    public final /* bridge */ /* synthetic */ void zzgg() {
        super.zzgg();
    }

    public final /* bridge */ /* synthetic */ void zzgh() {
        super.zzgh();
    }

    public final /* bridge */ /* synthetic */ void zzaf() {
        super.zzaf();
    }

    public final /* bridge */ /* synthetic */ zza zzgi() {
        return super.zzgi();
    }

    public final /* bridge */ /* synthetic */ zzda zzgj() {
        return super.zzgj();
    }

    public final /* bridge */ /* synthetic */ zzam zzgk() {
        return super.zzgk();
    }

    public final /* bridge */ /* synthetic */ zzeb zzgl() {
        return super.zzgl();
    }

    public final /* bridge */ /* synthetic */ zzdy zzgm() {
        return super.zzgm();
    }

    public final /* bridge */ /* synthetic */ zzao zzgn() {
        return super.zzgn();
    }

    public final /* bridge */ /* synthetic */ zzfd zzgo() {
        return super.zzgo();
    }

    public final /* bridge */ /* synthetic */ zzaa zzgp() {
        return super.zzgp();
    }

    public final /* bridge */ /* synthetic */ Clock zzbx() {
        return super.zzbx();
    }

    public final /* bridge */ /* synthetic */ Context getContext() {
        return super.getContext();
    }

    public final /* bridge */ /* synthetic */ zzaq zzgq() {
        return super.zzgq();
    }

    public final /* bridge */ /* synthetic */ zzfx zzgr() {
        return super.zzgr();
    }

    public final /* bridge */ /* synthetic */ zzbr zzgs() {
        return super.zzgs();
    }

    public final /* bridge */ /* synthetic */ zzas zzgt() {
        return super.zzgt();
    }

    public final /* bridge */ /* synthetic */ zzbd zzgu() {
        return super.zzgu();
    }

    public final /* bridge */ /* synthetic */ zzq zzgv() {
        return super.zzgv();
    }

    public final /* bridge */ /* synthetic */ zzn zzgw() {
        return super.zzgw();
    }
}
