package com.amazon.device.ads;

import android.support.annotation.NonNull;

public interface DTBAdCallback {
    void onFailure(@NonNull AdError adError);

    void onSuccess(@NonNull DTBAdResponse dTBAdResponse);
}
