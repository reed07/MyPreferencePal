package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzaxd implements Runnable {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzbcl zzehg;

    zzaxd(zzaxc zzaxc, Context context, zzbcl zzbcl) {
        this.val$context = context;
        this.zzehg = zzbcl;
    }

    public final void run() {
        try {
            this.zzehg.set(AdvertisingIdClient.getAdvertisingIdInfo(this.val$context));
        } catch (GooglePlayServicesNotAvailableException | GooglePlayServicesRepairableException | IOException | IllegalStateException e) {
            this.zzehg.setException(e);
            zzbbd.zzb("Exception while getting advertising Id info", e);
        }
    }
}
