package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.HashSet;
import java.util.Set;

public final class zzd implements zza {
    private AppMeasurement zzbss;
    Set<String> zzbte;
    /* access modifiers changed from: private */
    public AnalyticsConnectorListener zzbtf;
    private zze zzbtg = new zze(this);

    public zzd(AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzbtf = analyticsConnectorListener;
        this.zzbss = appMeasurement;
        this.zzbss.registerOnMeasurementEventListener(this.zzbtg);
        this.zzbte = new HashSet();
    }

    public final AnalyticsConnectorListener zztv() {
        return this.zzbtf;
    }

    public final void registerEventNames(Set<String> set) {
        this.zzbte.clear();
        Set<String> set2 = this.zzbte;
        HashSet hashSet = new HashSet();
        for (String str : set) {
            if (hashSet.size() >= 50) {
                break;
            } else if (zzc.zzfw(str) && zzc.zzfv(str)) {
                hashSet.add(zzc.zzfy(str));
            }
        }
        set2.addAll(hashSet);
    }

    public final void unregisterEventNames() {
        this.zzbte.clear();
    }
}
