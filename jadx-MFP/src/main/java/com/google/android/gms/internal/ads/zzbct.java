package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;

@zzark
public final class zzbct {
    public static void zza(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        new zzbcu(view, onGlobalLayoutListener).attach();
    }

    public static void zza(View view, OnScrollChangedListener onScrollChangedListener) {
        new zzbcv(view, onScrollChangedListener).attach();
    }
}
