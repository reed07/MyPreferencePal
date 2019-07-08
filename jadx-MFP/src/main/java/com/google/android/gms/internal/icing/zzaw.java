package com.google.android.gms.internal.icing;

import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;

final class zzaw extends zzas {
    private final /* synthetic */ zzav zzcd;

    zzaw(zzav zzav) {
        this.zzcd = zzav;
    }

    public final void zza(Status status, GoogleNowAuthState googleNowAuthState) {
        if (this.zzcd.zzca) {
            Log.d("SearchAuth", "GetGoogleNowAuthImpl success");
        }
        this.zzcd.setResult(new zzax(status, googleNowAuthState));
    }
}
