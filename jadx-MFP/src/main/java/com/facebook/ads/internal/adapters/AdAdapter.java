package com.facebook.ads.internal.adapters;

import android.support.annotation.Nullable;
import com.facebook.ads.internal.protocol.AdPlacementType;

public interface AdAdapter {
    @Nullable
    String getClientToken();

    AdPlacementType getPlacementType();

    void onDestroy();
}
