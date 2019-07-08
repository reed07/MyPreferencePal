package com.google.android.gms.internal.ads;

import android.content.ComponentName;
import android.support.customtabs.CustomTabsClient;
import android.support.customtabs.CustomTabsServiceConnection;
import java.lang.ref.WeakReference;

public final class zzbwj extends CustomTabsServiceConnection {
    private WeakReference<zzbwk> zzgdf;

    public zzbwj(zzbwk zzbwk) {
        this.zzgdf = new WeakReference<>(zzbwk);
    }

    public final void onCustomTabsServiceConnected(ComponentName componentName, CustomTabsClient customTabsClient) {
        zzbwk zzbwk = (zzbwk) this.zzgdf.get();
        if (zzbwk != null) {
            zzbwk.zza(customTabsClient);
        }
    }

    public final void onServiceDisconnected(ComponentName componentName) {
        zzbwk zzbwk = (zzbwk) this.zzgdf.get();
        if (zzbwk != null) {
            zzbwk.zzrl();
        }
    }
}
