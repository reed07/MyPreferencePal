package com.google.android.gms.internal.measurement;

import android.content.ContentResolver;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;
import android.net.Uri;
import android.support.annotation.GuardedBy;
import android.support.v4.util.ArrayMap;
import android.util.Log;
import com.samsung.android.sdk.internal.healthdata.IpcUtil;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzrx implements zzsb {
    @GuardedBy
    static final Map<Uri, zzrx> zzbrd = new ArrayMap();
    private static final String[] zzbri = {IpcUtil.KEY_CODE, "value"};
    private final Uri uri;
    private final ContentResolver zzbre;
    private final Object zzbrf = new Object();
    private volatile Map<String, String> zzbrg;
    @GuardedBy
    private final List<zzsa> zzbrh = new ArrayList();

    private zzrx(ContentResolver contentResolver, Uri uri2) {
        this.zzbre = contentResolver;
        this.uri = uri2;
        this.zzbre.registerContentObserver(uri2, false, new zzrz(this, null));
    }

    /* JADX WARNING: Can't wrap try/catch for region: R(5:2|3|(5:5|6|7|8|9)|12|13) */
    /* JADX WARNING: Missing exception handler attribute for start block: B:12:0x001a */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    public static com.google.android.gms.internal.measurement.zzrx zza(android.content.ContentResolver r3, android.net.Uri r4) {
        /*
            java.lang.Class<com.google.android.gms.internal.measurement.zzrx> r0 = com.google.android.gms.internal.measurement.zzrx.class
            monitor-enter(r0)
            java.util.Map<android.net.Uri, com.google.android.gms.internal.measurement.zzrx> r1 = zzbrd     // Catch:{ all -> 0x001c }
            java.lang.Object r1 = r1.get(r4)     // Catch:{ all -> 0x001c }
            com.google.android.gms.internal.measurement.zzrx r1 = (com.google.android.gms.internal.measurement.zzrx) r1     // Catch:{ all -> 0x001c }
            if (r1 != 0) goto L_0x001a
            com.google.android.gms.internal.measurement.zzrx r2 = new com.google.android.gms.internal.measurement.zzrx     // Catch:{ SecurityException -> 0x001a }
            r2.<init>(r3, r4)     // Catch:{ SecurityException -> 0x001a }
            java.util.Map<android.net.Uri, com.google.android.gms.internal.measurement.zzrx> r3 = zzbrd     // Catch:{ SecurityException -> 0x0019 }
            r3.put(r4, r2)     // Catch:{ SecurityException -> 0x0019 }
            r1 = r2
            goto L_0x001a
        L_0x0019:
            r1 = r2
        L_0x001a:
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            return r1
        L_0x001c:
            r3 = move-exception
            monitor-exit(r0)     // Catch:{ all -> 0x001c }
            throw r3
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.gms.internal.measurement.zzrx.zza(android.content.ContentResolver, android.net.Uri):com.google.android.gms.internal.measurement.zzrx");
    }

    public final Map<String, String> zztk() {
        Map<String, String> map = this.zzbrg;
        if (map == null) {
            synchronized (this.zzbrf) {
                map = this.zzbrg;
                if (map == null) {
                    map = zztm();
                    this.zzbrg = map;
                }
            }
        }
        if (map != null) {
            return map;
        }
        return Collections.emptyMap();
    }

    public final void zztl() {
        synchronized (this.zzbrf) {
            this.zzbrg = null;
            zzsi.zztq();
        }
        synchronized (this) {
            for (zzsa zztp : this.zzbrh) {
                zztp.zztp();
            }
        }
    }

    private final Map<String, String> zztm() {
        try {
            return (Map) zzsc.zza(new zzry(this));
        } catch (SQLiteException | SecurityException unused) {
            Log.e("ConfigurationContentLoader", "PhenotypeFlag unable to load ContentProvider, using default values");
            return null;
        }
    }

    public final /* synthetic */ Object zzfn(String str) {
        return (String) zztk().get(str);
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ Map zztn() {
        Map map;
        Cursor query = this.zzbre.query(this.uri, zzbri, null, null, null);
        if (query == null) {
            return Collections.emptyMap();
        }
        try {
            int count = query.getCount();
            if (count == 0) {
                return Collections.emptyMap();
            }
            if (count <= 256) {
                map = new ArrayMap(count);
            } else {
                map = new HashMap(count, 1.0f);
            }
            while (query.moveToNext()) {
                map.put(query.getString(0), query.getString(1));
            }
            query.close();
            return map;
        } finally {
            query.close();
        }
    }
}
