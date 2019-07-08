package com.google.android.gms.internal.icing;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzbp implements zzbf {
    @GuardedBy
    static final Map<String, zzbp> zzdt = new HashMap();
    private final Object zzcv = new Object();
    private volatile Map<String, ?> zzcw;
    @GuardedBy
    private final List<zzbe> zzcx = new ArrayList();
    private final SharedPreferences zzdu;
    private final OnSharedPreferenceChangeListener zzdv = new zzbq(this);

    static zzbp zza(Context context, String str) {
        zzbp zzbp;
        SharedPreferences sharedPreferences;
        if (!((!zzba.zzq() || str.startsWith("direct_boot:")) ? true : zzba.isUserUnlocked(context))) {
            return null;
        }
        synchronized (zzbp.class) {
            zzbp = (zzbp) zzdt.get(str);
            if (zzbp == null) {
                if (str.startsWith("direct_boot:")) {
                    if (zzba.zzq()) {
                        context = context.createDeviceProtectedStorageContext();
                    }
                    sharedPreferences = context.getSharedPreferences(str.substring(12), 0);
                } else {
                    sharedPreferences = context.getSharedPreferences(str, 0);
                }
                zzbp = new zzbp(sharedPreferences);
                zzdt.put(str, zzbp);
            }
        }
        return zzbp;
    }

    private zzbp(SharedPreferences sharedPreferences) {
        this.zzdu = sharedPreferences;
        this.zzdu.registerOnSharedPreferenceChangeListener(this.zzdv);
    }

    public final Object zzn(String str) {
        Map<String, ?> map = this.zzcw;
        if (map == null) {
            synchronized (this.zzcv) {
                map = this.zzcw;
                if (map == null) {
                    map = this.zzdu.getAll();
                    this.zzcw = map;
                }
            }
        }
        if (map != null) {
            return map.get(str);
        }
        return null;
    }

    /* access modifiers changed from: 0000 */
    public final /* synthetic */ void zza(SharedPreferences sharedPreferences, String str) {
        synchronized (this.zzcv) {
            this.zzcw = null;
            zzbl.zzx();
        }
        synchronized (this) {
            for (zzbe zzw : this.zzcx) {
                zzw.zzw();
            }
        }
    }
}
