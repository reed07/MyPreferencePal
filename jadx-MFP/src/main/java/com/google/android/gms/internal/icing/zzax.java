package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.GoogleNowAuthState;
import com.google.android.gms.search.SearchAuthApi.GoogleNowAuthResult;

final class zzax implements GoogleNowAuthResult {
    private final GoogleNowAuthState zzce;
    private final Status zzv;

    zzax(Status status, GoogleNowAuthState googleNowAuthState) {
        this.zzv = status;
        this.zzce = googleNowAuthState;
    }

    public final Status getStatus() {
        return this.zzv;
    }

    public final GoogleNowAuthState getGoogleNowAuthState() {
        return this.zzce;
    }
}
