package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzaxy extends zzaxv {
    private Context mContext;

    zzaxy(Context context) {
        this.mContext = context;
    }

    public final void onStop() {
    }

    public final void zzki() {
        boolean z;
        try {
            z = AdvertisingIdClient.getIsAdIdFakeForDebugLogging(this.mContext);
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            zzaxz.zzb("Fail to get isAdIdFakeForDebugLogging", e);
            z = false;
        }
        zzbax.zzaq(z);
        StringBuilder sb = new StringBuilder(43);
        sb.append("Update ad debug logging enablement as ");
        sb.append(z);
        zzaxz.zzeo(sb.toString());
    }
}
