package com.google.android.gms.internal.icing;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.search.SearchAuthApi;
import com.google.android.gms.search.SearchAuthApi.GoogleNowAuthResult;

public final class zzar implements SearchAuthApi {
    public final PendingResult<GoogleNowAuthResult> getGoogleNowAuth(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzav(googleApiClient, str));
    }

    public final PendingResult<Status> clearToken(GoogleApiClient googleApiClient, String str) {
        return googleApiClient.enqueue(new zzat(googleApiClient, str));
    }
}
