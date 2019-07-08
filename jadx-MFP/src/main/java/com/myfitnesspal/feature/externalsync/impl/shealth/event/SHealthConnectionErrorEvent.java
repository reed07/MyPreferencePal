package com.myfitnesspal.feature.externalsync.impl.shealth.event;

import com.myfitnesspal.shared.event.ConsumableEvent;
import com.samsung.android.sdk.healthdata.HealthConnectionErrorResult;

public class SHealthConnectionErrorEvent extends ConsumableEvent {
    private HealthConnectionErrorResult error;

    public SHealthConnectionErrorEvent(HealthConnectionErrorResult healthConnectionErrorResult) {
        this.error = healthConnectionErrorResult;
    }

    public HealthConnectionErrorResult getError() {
        return this.error;
    }
}
