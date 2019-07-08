package com.google.android.gms.internal.ads;

import android.view.View;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import com.google.android.gms.ads.internal.zzbv;
import java.lang.ref.WeakReference;

@zzark
final class zzbcu extends zzbcw implements OnGlobalLayoutListener {
    private final WeakReference<OnGlobalLayoutListener> zzeqc;

    public zzbcu(View view, OnGlobalLayoutListener onGlobalLayoutListener) {
        super(view);
        this.zzeqc = new WeakReference<>(onGlobalLayoutListener);
    }

    public final void onGlobalLayout() {
        OnGlobalLayoutListener onGlobalLayoutListener = (OnGlobalLayoutListener) this.zzeqc.get();
        if (onGlobalLayoutListener != null) {
            onGlobalLayoutListener.onGlobalLayout();
        } else {
            detach();
        }
    }

    /* access modifiers changed from: protected */
    public final void zza(ViewTreeObserver viewTreeObserver) {
        viewTreeObserver.addOnGlobalLayoutListener(this);
    }

    /* access modifiers changed from: protected */
    public final void zzb(ViewTreeObserver viewTreeObserver) {
        zzbv.zzlh().zza(viewTreeObserver, (OnGlobalLayoutListener) this);
    }
}
