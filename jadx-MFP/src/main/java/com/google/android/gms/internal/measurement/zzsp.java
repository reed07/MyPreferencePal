package com.google.android.gms.internal.measurement;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.support.annotation.GuardedBy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class zzsp implements zzsb {
    @GuardedBy
    static final Map<String, zzsp> zzbsb = new HashMap();
    private final Object zzbrf = new Object();
    private volatile Map<String, ?> zzbrg;
    @GuardedBy
    private final List<zzsa> zzbrh = new ArrayList();
    private final SharedPreferences zzbsc;
    private final OnSharedPreferenceChangeListener zzbsd = new zzsq(this);

    static zzsp zzi(Context context, String str) {
        zzsp zzsp;
        SharedPreferences sharedPreferences;
        if (!((!zzrw.zztj() || str.startsWith("direct_boot:")) ? true : zzrw.isUserUnlocked(context))) {
            return null;
        }
        synchronized (zzsp.class) {
            zzsp = (zzsp) zzbsb.get(str);
            if (zzsp == null) {
                if (str.startsWith("direct_boot:")) {
                    if (zzrw.zztj()) {
                        context = context.createDeviceProtectedStorageContext();
                    }
                    sharedPreferences = context.getSharedPreferences(str.substring(12), 0);
                } else {
                    sharedPreferences = context.getSharedPreferences(str, 0);
                }
                zzsp = new zzsp(sharedPreferences);
                zzbsb.put(str, zzsp);
            }
        }
        return zzsp;
    }

    private zzsp(SharedPreferences sharedPreferences) {
        this.zzbsc = sharedPreferences;
        this.zzbsc.registerOnSharedPreferenceChangeListener(this.zzbsd);
    }

    public final Object zzfn(String str) {
        Map<String, ?> map = this.zzbrg;
        if (map == null) {
            synchronized (this.zzbrf) {
                map = this.zzbrg;
                if (map == null) {
                    map = this.zzbsc.getAll();
                    this.zzbrg = map;
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
}
