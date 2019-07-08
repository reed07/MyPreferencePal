package com.google.firebase.analytics.connector.internal;

import com.google.android.gms.measurement.AppMeasurement;
import com.google.firebase.analytics.connector.AnalyticsConnector.AnalyticsConnectorListener;
import java.util.Set;

public final class zzf implements zza {
    private AppMeasurement zzbss;
    /* access modifiers changed from: private */
    public AnalyticsConnectorListener zzbtf;
    private zzg zzbti = new zzg(this);

    public zzf(AppMeasurement appMeasurement, AnalyticsConnectorListener analyticsConnectorListener) {
        this.zzbtf = analyticsConnectorListener;
        this.zzbss = appMeasurement;
        this.zzbss.registerOnMeasurementEventListener(this.zzbti);
    }

    public final void registerEventNames(Set<String> set) {
    }

    public final void unregisterEventNames() {
    }

    public final AnalyticsConnectorListener zztv() {
        return this.zzbtf;
    }
}
