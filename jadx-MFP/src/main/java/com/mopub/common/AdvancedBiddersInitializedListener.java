package com.mopub.common;

import android.support.annotation.NonNull;
import java.util.List;

interface AdvancedBiddersInitializedListener {
    void onAdvancedBiddersInitialized(@NonNull List<MoPubAdvancedBidder> list);
}
