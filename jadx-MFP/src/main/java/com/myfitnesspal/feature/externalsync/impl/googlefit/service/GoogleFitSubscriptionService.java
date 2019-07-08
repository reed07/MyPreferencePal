package com.myfitnesspal.feature.externalsync.impl.googlefit.service;

import com.google.android.gms.common.api.GoogleApiClient;
import com.uacf.core.util.Function1;

public interface GoogleFitSubscriptionService {
    void subscribe(GoogleApiClient googleApiClient);

    void unsubscribe(GoogleApiClient googleApiClient, Function1<Boolean> function1);
}
