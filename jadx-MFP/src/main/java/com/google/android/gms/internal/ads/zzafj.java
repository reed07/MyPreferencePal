package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.formats.UnifiedNativeAd.UnconfirmedClickListener;

public final class zzafj extends zzaeu {
    private final UnconfirmedClickListener zzdeq;

    public zzafj(UnconfirmedClickListener unconfirmedClickListener) {
        this.zzdeq = unconfirmedClickListener;
    }

    public final void onUnconfirmedClickReceived(String str) {
        this.zzdeq.onUnconfirmedClickReceived(str);
    }

    public final void onUnconfirmedClickCancelled() {
        this.zzdeq.onUnconfirmedClickCancelled();
    }
}
