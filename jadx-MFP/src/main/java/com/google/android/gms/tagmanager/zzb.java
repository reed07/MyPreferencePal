package com.google.android.gms.tagmanager;

import com.google.android.gms.ads.identifier.AdvertisingIdClient;
import com.google.android.gms.ads.identifier.AdvertisingIdClient.Info;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import java.io.IOException;

final class zzb implements zzd {
    private final /* synthetic */ zza zzazg;

    zzb(zza zza) {
        this.zzazg = zza;
    }

    public final Info zznj() {
        try {
            return AdvertisingIdClient.getAdvertisingIdInfo(this.zzazg.zzri);
        } catch (IllegalStateException e) {
            zzdi.zzb("IllegalStateException getting Advertising Id Info", e);
            return null;
        } catch (GooglePlayServicesRepairableException e2) {
            zzdi.zzb("GooglePlayServicesRepairableException getting Advertising Id Info", e2);
            return null;
        } catch (IOException e3) {
            zzdi.zzb("IOException getting Ad Id Info", e3);
            return null;
        } catch (GooglePlayServicesNotAvailableException e4) {
            this.zzazg.close();
            zzdi.zzb("GooglePlayServicesNotAvailableException getting Advertising Id Info", e4);
            return null;
        } catch (Exception e5) {
            zzdi.zzb("Unknown exception. Could not get the Advertising Id Info.", e5);
            return null;
        }
    }
}
