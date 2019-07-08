package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class zzbr implements OnTouchListener {
    private final /* synthetic */ zzbp zzbra;

    zzbr(zzbp zzbp) {
        this.zzbra = zzbp;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        if (this.zzbra.zzbqy != null) {
            this.zzbra.zzbqy.zza(motionEvent);
        }
        return false;
    }
}
