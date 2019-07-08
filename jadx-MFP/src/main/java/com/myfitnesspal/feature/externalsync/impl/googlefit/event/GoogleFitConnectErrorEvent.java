package com.myfitnesspal.feature.externalsync.impl.googlefit.event;

import com.google.android.gms.common.ConnectionResult;
import com.myfitnesspal.shared.event.ConsumableEvent;

public class GoogleFitConnectErrorEvent extends ConsumableEvent {
    private ConnectionResult connectionResult;

    public GoogleFitConnectErrorEvent(ConnectionResult connectionResult2) {
        this.connectionResult = connectionResult2;
    }

    public ConnectionResult getConnectionResult() {
        return this.connectionResult;
    }
}
