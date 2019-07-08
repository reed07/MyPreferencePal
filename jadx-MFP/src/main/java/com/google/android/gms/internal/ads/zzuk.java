package com.google.android.gms.internal.ads;

import android.support.annotation.NonNull;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.internal.BaseGmsClient.BaseOnConnectionFailedListener;

final class zzuk implements BaseOnConnectionFailedListener {
    private final /* synthetic */ zzue zzcad;
    private final /* synthetic */ zzbcl zzcaf;

    zzuk(zzue zzue, zzbcl zzbcl) {
        this.zzcad = zzue;
        this.zzcaf = zzbcl;
    }

    public final void onConnectionFailed(@NonNull ConnectionResult connectionResult) {
        synchronized (this.zzcad.mLock) {
            this.zzcaf.setException(new RuntimeException("Connection failed."));
        }
    }
}
