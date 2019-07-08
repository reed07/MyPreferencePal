package com.google.android.gms.ads.internal;

import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

final class zzm implements OnTouchListener {
    private final /* synthetic */ zzk zzbmi;
    private final /* synthetic */ zzw zzbmj;

    zzm(zzk zzk, zzw zzw) {
        this.zzbmi = zzk;
        this.zzbmj = zzw;
    }

    public final boolean onTouch(View view, MotionEvent motionEvent) {
        this.zzbmj.recordClick();
        if (this.zzbmi.zzbmg != null) {
            this.zzbmi.zzbmg.zzxr();
        }
        return false;
    }
}
