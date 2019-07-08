package com.google.android.gms.internal.icing;

import android.util.Log;
import com.google.android.gms.common.api.Status;

final class zzau extends zzas {
    private final /* synthetic */ zzat zzcb;

    zzau(zzat zzat) {
        this.zzcb = zzat;
    }

    public final void zzb(Status status) {
        if (this.zzcb.zzca) {
            Log.d("SearchAuth", "ClearTokenImpl success");
        }
        this.zzcb.setResult(status);
    }
}
