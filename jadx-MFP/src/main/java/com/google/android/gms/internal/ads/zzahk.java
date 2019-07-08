package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import com.google.android.gms.ads.internal.zzbv;
import java.util.Random;

final class zzahk extends zzxb {
    private final zzxa zzdhc;

    zzahk(zzxa zzxa) {
        this.zzdhc = zzxa;
    }

    public final void onAdClosed() throws RemoteException {
        if (zzaht.zztz()) {
            int intValue = ((Integer) zzwu.zzpz().zzd(zzaan.zzcsh)).intValue();
            int intValue2 = ((Integer) zzwu.zzpz().zzd(zzaan.zzcsi)).intValue();
            if (intValue <= 0 || intValue2 < 0) {
                zzbv.zzlt().zzth();
            } else {
                zzayh.zzelc.postDelayed(zzahl.zzdhd, (long) (intValue + new Random().nextInt(intValue2 + 1)));
            }
        }
        this.zzdhc.onAdClosed();
    }

    public final void onAdFailedToLoad(int i) throws RemoteException {
        this.zzdhc.onAdFailedToLoad(i);
    }

    public final void onAdLeftApplication() throws RemoteException {
        this.zzdhc.onAdLeftApplication();
    }

    public final void onAdLoaded() throws RemoteException {
        this.zzdhc.onAdLoaded();
    }

    public final void onAdOpened() throws RemoteException {
        this.zzdhc.onAdOpened();
    }

    public final void onAdImpression() throws RemoteException {
        this.zzdhc.onAdImpression();
    }

    public final void onAdClicked() throws RemoteException {
        this.zzdhc.onAdClicked();
    }
}
