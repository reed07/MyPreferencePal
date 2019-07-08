package com.google.firebase.analytics.connector.internal;

import android.os.Bundle;
import com.google.android.gms.measurement.AppMeasurement.OnEventListener;
import com.mopub.common.Constants;

final class zze implements OnEventListener {
    private final /* synthetic */ zzd zzbth;

    public zze(zzd zzd) {
        this.zzbth = zzd;
    }

    public final void onEvent(String str, String str2, Bundle bundle, long j) {
        if (this.zzbth.zzbte.contains(str2)) {
            Bundle bundle2 = new Bundle();
            bundle2.putString(Constants.VIDEO_TRACKING_EVENTS_KEY, zzc.zzfx(str2));
            this.zzbth.zzbtf.onMessageTriggered(2, bundle2);
        }
    }
}
