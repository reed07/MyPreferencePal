package com.google.android.gms.tagmanager;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.text.TextUtils;
import com.google.android.gms.common.util.Clock;
import com.google.android.gms.common.util.DefaultClock;
import com.google.android.gms.common.util.VisibleForTesting;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@VisibleForTesting
final class zzeb implements zzcb {
    /* access modifiers changed from: private */
    public static final String zzxf = String.format("CREATE TABLE IF NOT EXISTS %s ( '%s' INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, '%s' INTEGER NOT NULL, '%s' TEXT NOT NULL,'%s' INTEGER NOT NULL);", new Object[]{"gtm_hits", "hit_id", "hit_time", "hit_url", "hit_first_send_time"});
    private final zzed zzbdr;
    private volatile zzbe zzbds;
    private final zzcc zzbdt;
    /* access modifiers changed from: private */
    public final String zzbdu;
    private long zzbdv;
    private final int zzbdw;
    /* access modifiers changed from: private */
    public final Context zzri;
    /* access modifiers changed from: private */
    public Clock zzrz;

    zzeb(zzcc zzcc, Context context) {
        this(zzcc, context, "gtm_urls.db", 2000);
    }

    @VisibleForTesting
    private zzeb(zzcc zzcc, Context context, String str, int i) {
        this.zzri = context.getApplicationContext();
        this.zzbdu = str;
        this.zzbdt = zzcc;
        this.zzrz = DefaultClock.getInstance();
        this.zzbdr = new zzed(this, this.zzri, this.zzbdu);
        this.zzbds = new zzfu(this.zzri, new zzec(this));
        this.zzbdv = 0;
        this.zzbdw = 2000;
    }

    public final void zzb(long j, String str) {
        long currentTimeMillis = this.zzrz.currentTimeMillis();
        if (currentTimeMillis > this.zzbdv + 86400000) {
            this.zzbdv = currentTimeMillis;
            SQLiteDatabase zzdl = zzdl("Error opening database for deleteStaleHits.");
            if (zzdl != null) {
                zzdl.delete("gtm_hits", "HIT_TIME < ?", new String[]{Long.toString(this.zzrz.currentTimeMillis() - 2592000000L)});
                this.zzbdt.zzo(zzpj() == 0);
            }
        }
        int zzpj = (zzpj() - this.zzbdw) + 1;
        if (zzpj > 0) {
            List zzab = zzab(zzpj);
            int size = zzab.size();
            StringBuilder sb = new StringBuilder(51);
            sb.append("Store full, deleting ");
            sb.append(size);
            sb.append(" hits to make room.");
            zzdi.v(sb.toString());
            zza((String[]) zzab.toArray(new String[0]));
        }
        SQLiteDatabase zzdl2 = zzdl("Error opening database for putHit");
        if (zzdl2 != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_time", Long.valueOf(j));
            contentValues.put("hit_url", str);
            contentValues.put("hit_first_send_time", Integer.valueOf(0));
            try {
                zzdl2.insert("gtm_hits", null, contentValues);
                this.zzbdt.zzo(false);
            } catch (SQLiteException unused) {
                zzdi.zzab("Error storing hit");
            }
        }
    }

    private final List<String> zzab(int i) {
        ArrayList arrayList = new ArrayList();
        if (i <= 0) {
            zzdi.zzab("Invalid maxHits specified. Skipping");
            return arrayList;
        }
        SQLiteDatabase zzdl = zzdl("Error opening database for peekHitIds.");
        if (zzdl == null) {
            return arrayList;
        }
        Cursor cursor = null;
        try {
            Cursor query = zzdl.query("gtm_hits", new String[]{"hit_id"}, null, null, null, null, String.format("%s ASC", new Object[]{"hit_id"}), Integer.toString(i));
            if (query.moveToFirst()) {
                do {
                    arrayList.add(String.valueOf(query.getLong(0)));
                } while (query.moveToNext());
            }
            if (query != null) {
                query.close();
            }
        } catch (SQLiteException e) {
            String str = "Error in peekHits fetching hitIds: ";
            String valueOf = String.valueOf(e.getMessage());
            zzdi.zzab(valueOf.length() != 0 ? str.concat(valueOf) : new String(str));
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return arrayList;
    }

    /* JADX INFO: finally extract failed */
    /* JADX WARNING: Removed duplicated region for block: B:41:0x00f1 A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:42:0x00f6 A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:45:0x010d A[Catch:{ all -> 0x00dd }] */
    /* JADX WARNING: Removed duplicated region for block: B:51:0x0128  */
    /* JADX WARNING: Removed duplicated region for block: B:64:0x0149 A[Catch:{ all -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:65:0x014e A[Catch:{ all -> 0x0136 }] */
    /* JADX WARNING: Removed duplicated region for block: B:68:0x0158  */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final java.util.List<com.google.android.gms.tagmanager.zzbw> zzac(int r17) {
        /*
            r16 = this;
            java.util.ArrayList r1 = new java.util.ArrayList
            r1.<init>()
            java.lang.String r0 = "Error opening database for peekHits"
            r2 = r16
            android.database.sqlite.SQLiteDatabase r0 = r2.zzdl(r0)
            if (r0 != 0) goto L_0x0010
            return r1
        L_0x0010:
            r12 = 0
            java.lang.String r4 = "gtm_hits"
            java.lang.String r3 = "hit_id"
            java.lang.String r5 = "hit_time"
            java.lang.String r6 = "hit_first_send_time"
            java.lang.String[] r5 = new java.lang.String[]{r3, r5, r6}     // Catch:{ SQLiteException -> 0x0138 }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r3 = "%s ASC"
            r13 = 1
            java.lang.Object[] r10 = new java.lang.Object[r13]     // Catch:{ SQLiteException -> 0x0138 }
            java.lang.String r11 = "hit_id"
            r14 = 0
            r10[r14] = r11     // Catch:{ SQLiteException -> 0x0138 }
            java.lang.String r10 = java.lang.String.format(r3, r10)     // Catch:{ SQLiteException -> 0x0138 }
            r15 = 40
            java.lang.String r11 = java.lang.Integer.toString(r15)     // Catch:{ SQLiteException -> 0x0138 }
            r3 = r0
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x0138 }
            java.util.ArrayList r11 = new java.util.ArrayList     // Catch:{ SQLiteException -> 0x0138 }
            r11.<init>()     // Catch:{ SQLiteException -> 0x0138 }
            boolean r1 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x0132 }
            if (r1 == 0) goto L_0x0066
        L_0x0045:
            com.google.android.gms.tagmanager.zzbw r1 = new com.google.android.gms.tagmanager.zzbw     // Catch:{ SQLiteException -> 0x0062 }
            long r4 = r12.getLong(r14)     // Catch:{ SQLiteException -> 0x0062 }
            long r6 = r12.getLong(r13)     // Catch:{ SQLiteException -> 0x0062 }
            r3 = 2
            long r8 = r12.getLong(r3)     // Catch:{ SQLiteException -> 0x0062 }
            r3 = r1
            r3.<init>(r4, r6, r8)     // Catch:{ SQLiteException -> 0x0062 }
            r11.add(r1)     // Catch:{ SQLiteException -> 0x0062 }
            boolean r1 = r12.moveToNext()     // Catch:{ SQLiteException -> 0x0062 }
            if (r1 != 0) goto L_0x0045
            goto L_0x0066
        L_0x0062:
            r0 = move-exception
            r1 = r11
            goto L_0x0139
        L_0x0066:
            if (r12 == 0) goto L_0x006b
            r12.close()
        L_0x006b:
            java.lang.String r4 = "gtm_hits"
            java.lang.String r1 = "hit_id"
            java.lang.String r3 = "hit_url"
            java.lang.String[] r5 = new java.lang.String[]{r1, r3}     // Catch:{ SQLiteException -> 0x00df }
            r6 = 0
            r7 = 0
            r8 = 0
            r9 = 0
            java.lang.String r1 = "%s ASC"
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ SQLiteException -> 0x00df }
            java.lang.String r10 = "hit_id"
            r3[r14] = r10     // Catch:{ SQLiteException -> 0x00df }
            java.lang.String r10 = java.lang.String.format(r1, r3)     // Catch:{ SQLiteException -> 0x00df }
            java.lang.String r1 = java.lang.Integer.toString(r15)     // Catch:{ SQLiteException -> 0x00df }
            r3 = r0
            r15 = r11
            r11 = r1
            android.database.Cursor r12 = r3.query(r4, r5, r6, r7, r8, r9, r10, r11)     // Catch:{ SQLiteException -> 0x00db }
            boolean r0 = r12.moveToFirst()     // Catch:{ SQLiteException -> 0x00db }
            if (r0 == 0) goto L_0x00d5
            r0 = 0
        L_0x0097:
            r1 = r12
            android.database.sqlite.SQLiteCursor r1 = (android.database.sqlite.SQLiteCursor) r1     // Catch:{ SQLiteException -> 0x00db }
            android.database.CursorWindow r1 = r1.getWindow()     // Catch:{ SQLiteException -> 0x00db }
            int r1 = r1.getNumRows()     // Catch:{ SQLiteException -> 0x00db }
            if (r1 <= 0) goto L_0x00b2
            java.lang.Object r1 = r15.get(r0)     // Catch:{ SQLiteException -> 0x00db }
            com.google.android.gms.tagmanager.zzbw r1 = (com.google.android.gms.tagmanager.zzbw) r1     // Catch:{ SQLiteException -> 0x00db }
            java.lang.String r3 = r12.getString(r13)     // Catch:{ SQLiteException -> 0x00db }
            r1.zzds(r3)     // Catch:{ SQLiteException -> 0x00db }
            goto L_0x00cd
        L_0x00b2:
            java.lang.String r1 = "HitString for hitId %d too large.  Hit will be deleted."
            java.lang.Object[] r3 = new java.lang.Object[r13]     // Catch:{ SQLiteException -> 0x00db }
            java.lang.Object r4 = r15.get(r0)     // Catch:{ SQLiteException -> 0x00db }
            com.google.android.gms.tagmanager.zzbw r4 = (com.google.android.gms.tagmanager.zzbw) r4     // Catch:{ SQLiteException -> 0x00db }
            long r4 = r4.zzov()     // Catch:{ SQLiteException -> 0x00db }
            java.lang.Long r4 = java.lang.Long.valueOf(r4)     // Catch:{ SQLiteException -> 0x00db }
            r3[r14] = r4     // Catch:{ SQLiteException -> 0x00db }
            java.lang.String r1 = java.lang.String.format(r1, r3)     // Catch:{ SQLiteException -> 0x00db }
            com.google.android.gms.tagmanager.zzdi.zzab(r1)     // Catch:{ SQLiteException -> 0x00db }
        L_0x00cd:
            int r0 = r0 + 1
            boolean r1 = r12.moveToNext()     // Catch:{ SQLiteException -> 0x00db }
            if (r1 != 0) goto L_0x0097
        L_0x00d5:
            if (r12 == 0) goto L_0x00da
            r12.close()
        L_0x00da:
            return r15
        L_0x00db:
            r0 = move-exception
            goto L_0x00e1
        L_0x00dd:
            r0 = move-exception
            goto L_0x012c
        L_0x00df:
            r0 = move-exception
            r15 = r11
        L_0x00e1:
            java.lang.String r1 = "Error in peekHits fetching hit url: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x00dd }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x00dd }
            int r3 = r0.length()     // Catch:{ all -> 0x00dd }
            if (r3 == 0) goto L_0x00f6
            java.lang.String r0 = r1.concat(r0)     // Catch:{ all -> 0x00dd }
            goto L_0x00fb
        L_0x00f6:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x00dd }
            r0.<init>(r1)     // Catch:{ all -> 0x00dd }
        L_0x00fb:
            com.google.android.gms.tagmanager.zzdi.zzab(r0)     // Catch:{ all -> 0x00dd }
            java.util.ArrayList r0 = new java.util.ArrayList     // Catch:{ all -> 0x00dd }
            r0.<init>()     // Catch:{ all -> 0x00dd }
            r11 = r15
            java.util.ArrayList r11 = (java.util.ArrayList) r11     // Catch:{ all -> 0x00dd }
            int r1 = r11.size()     // Catch:{ all -> 0x00dd }
            r3 = 0
        L_0x010b:
            if (r14 >= r1) goto L_0x0126
            java.lang.Object r4 = r11.get(r14)     // Catch:{ all -> 0x00dd }
            int r14 = r14 + 1
            com.google.android.gms.tagmanager.zzbw r4 = (com.google.android.gms.tagmanager.zzbw) r4     // Catch:{ all -> 0x00dd }
            java.lang.String r5 = r4.zzox()     // Catch:{ all -> 0x00dd }
            boolean r5 = android.text.TextUtils.isEmpty(r5)     // Catch:{ all -> 0x00dd }
            if (r5 == 0) goto L_0x0122
            if (r3 != 0) goto L_0x0126
            r3 = 1
        L_0x0122:
            r0.add(r4)     // Catch:{ all -> 0x00dd }
            goto L_0x010b
        L_0x0126:
            if (r12 == 0) goto L_0x012b
            r12.close()
        L_0x012b:
            return r0
        L_0x012c:
            if (r12 == 0) goto L_0x0131
            r12.close()
        L_0x0131:
            throw r0
        L_0x0132:
            r0 = move-exception
            r15 = r11
            r1 = r15
            goto L_0x0139
        L_0x0136:
            r0 = move-exception
            goto L_0x015c
        L_0x0138:
            r0 = move-exception
        L_0x0139:
            java.lang.String r3 = "Error in peekHits fetching hitIds: "
            java.lang.String r0 = r0.getMessage()     // Catch:{ all -> 0x0136 }
            java.lang.String r0 = java.lang.String.valueOf(r0)     // Catch:{ all -> 0x0136 }
            int r4 = r0.length()     // Catch:{ all -> 0x0136 }
            if (r4 == 0) goto L_0x014e
            java.lang.String r0 = r3.concat(r0)     // Catch:{ all -> 0x0136 }
            goto L_0x0153
        L_0x014e:
            java.lang.String r0 = new java.lang.String     // Catch:{ all -> 0x0136 }
            r0.<init>(r3)     // Catch:{ all -> 0x0136 }
        L_0x0153:
            com.google.android.gms.tagmanager.zzdi.zzab(r0)     // Catch:{ all -> 0x0136 }
            if (r12 == 0) goto L_0x015b
            r12.close()
        L_0x015b:
            return r1
        L_0x015c:
            if (r12 == 0) goto L_0x0161
            r12.close()
        L_0x0161:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzac(int):java.util.List");
    }

    private final void zza(String[] strArr) {
        if (strArr != null && strArr.length != 0) {
            SQLiteDatabase zzdl = zzdl("Error opening database for deleteHits.");
            if (zzdl != null) {
                boolean z = true;
                try {
                    zzdl.delete("gtm_hits", String.format("HIT_ID in (%s)", new Object[]{TextUtils.join(",", Collections.nCopies(strArr.length, "?"))}), strArr);
                    zzcc zzcc = this.zzbdt;
                    if (zzpj() != 0) {
                        z = false;
                    }
                    zzcc.zzo(z);
                } catch (SQLiteException unused) {
                    zzdi.zzab("Error deleting hits");
                }
            }
        }
    }

    /* access modifiers changed from: private */
    public final void zze(long j) {
        zza(new String[]{String.valueOf(j)});
    }

    /* access modifiers changed from: private */
    public final void zze(long j, long j2) {
        SQLiteDatabase zzdl = zzdl("Error opening database for getNumStoredHits.");
        if (zzdl != null) {
            ContentValues contentValues = new ContentValues();
            contentValues.put("hit_first_send_time", Long.valueOf(j2));
            try {
                zzdl.update("gtm_hits", contentValues, "hit_id=?", new String[]{String.valueOf(j)});
            } catch (SQLiteException unused) {
                StringBuilder sb = new StringBuilder(69);
                sb.append("Error setting HIT_FIRST_DISPATCH_TIME for hitId: ");
                sb.append(j);
                zzdi.zzab(sb.toString());
                zze(j);
            }
        }
    }

    /* JADX WARNING: type inference failed for: r2v0, types: [java.lang.String[], android.database.Cursor] */
    /* JADX WARNING: Multi-variable type inference failed. Error: jadx.core.utils.exceptions.JadxRuntimeException: No candidate types for var: r2v0, types: [java.lang.String[], android.database.Cursor]
  assigns: [?[int, float, boolean, short, byte, char, OBJECT, ARRAY]]
  uses: [?[int, boolean, OBJECT, ARRAY, byte, short, char], android.database.Cursor, java.lang.String[]]
  mth insns count: 24
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
    /* JADX WARNING: Unknown variable types count: 1 */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    private final int zzpj() {
        /*
            r4 = this;
            java.lang.String r0 = "Error opening database for getNumStoredHits."
            android.database.sqlite.SQLiteDatabase r0 = r4.zzdl(r0)
            r1 = 0
            if (r0 != 0) goto L_0x000a
            return r1
        L_0x000a:
            r2 = 0
            java.lang.String r3 = "SELECT COUNT(*) from gtm_hits"
            android.database.Cursor r2 = r0.rawQuery(r3, r2)     // Catch:{ SQLiteException -> 0x0024 }
            boolean r0 = r2.moveToFirst()     // Catch:{ SQLiteException -> 0x0024 }
            if (r0 == 0) goto L_0x001c
            long r0 = r2.getLong(r1)     // Catch:{ SQLiteException -> 0x0024 }
            int r1 = (int) r0
        L_0x001c:
            if (r2 == 0) goto L_0x002e
            r2.close()
            goto L_0x002e
        L_0x0022:
            r0 = move-exception
            goto L_0x002f
        L_0x0024:
            java.lang.String r0 = "Error getting numStoredHits"
            com.google.android.gms.tagmanager.zzdi.zzab(r0)     // Catch:{ all -> 0x0022 }
            if (r2 == 0) goto L_0x002e
            r2.close()
        L_0x002e:
            return r1
        L_0x002f:
            if (r2 == 0) goto L_0x0034
            r2.close()
        L_0x0034:
            throw r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.tagmanager.zzeb.zzpj():int");
    }

    private final int zzpk() {
        SQLiteDatabase zzdl = zzdl("Error opening database for getNumStoredHits.");
        int i = 0;
        if (zzdl == null) {
            return 0;
        }
        Cursor cursor = null;
        try {
            Cursor query = zzdl.query("gtm_hits", new String[]{"hit_id", "hit_first_send_time"}, "hit_first_send_time=0", null, null, null, null);
            i = query.getCount();
            if (query != null) {
                query.close();
            }
        } catch (SQLiteException unused) {
            zzdi.zzab("Error getting num untried hits");
            if (cursor != null) {
                cursor.close();
            }
        } catch (Throwable th) {
            if (cursor != null) {
                cursor.close();
            }
            throw th;
        }
        return i;
    }

    public final void dispatch() {
        zzdi.v("GTM Dispatch running...");
        if (this.zzbds.zzom()) {
            List zzac = zzac(40);
            if (zzac.isEmpty()) {
                zzdi.v("...nothing to dispatch");
                this.zzbdt.zzo(true);
                return;
            }
            this.zzbds.zzf(zzac);
            if (zzpk() > 0) {
                zzfn.zzqe().dispatch();
            }
        }
    }

    private final SQLiteDatabase zzdl(String str) {
        try {
            return this.zzbdr.getWritableDatabase();
        } catch (SQLiteException unused) {
            zzdi.zzab(str);
            return null;
        }
    }
}
