package com.facebook.ads.internal.adapters;

import android.os.Bundle;
import com.facebook.ads.internal.protocol.AdPlacementType;
import com.facebook.ads.internal.w.b.r;

public abstract class n implements AdAdapter, r<Bundle> {
    public abstract boolean e();

    public AdPlacementType getPlacementType() {
        return AdPlacementType.INSTREAM;
    }
}
