package com.google.android.gms.internal.ads;

import com.google.android.gms.ads.MuteThisAdListener;

public final class zzyk extends zzyi {
    private final MuteThisAdListener zzclv;

    public zzyk(MuteThisAdListener muteThisAdListener) {
        this.zzclv = muteThisAdListener;
    }

    public final void onAdMuted() {
        this.zzclv.onAdMuted();
    }
}
