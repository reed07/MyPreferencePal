package com.google.android.gms.tagmanager;

import android.content.ComponentCallbacks2;
import android.content.res.Configuration;

final class zzgc implements ComponentCallbacks2 {
    private final /* synthetic */ TagManager zzbgt;

    zzgc(TagManager tagManager) {
        this.zzbgt = tagManager;
    }

    public final void onConfigurationChanged(Configuration configuration) {
    }

    public final void onLowMemory() {
    }

    public final void onTrimMemory(int i) {
        if (i == 20) {
            this.zzbgt.dispatch();
        }
    }
}
