package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.view.MotionEvent;
import android.widget.RelativeLayout;
import com.google.android.gms.common.util.VisibleForTesting;
import com.google.android.gms.internal.ads.zzark;
import com.google.android.gms.internal.ads.zzazc;

@zzark
@VisibleForTesting
final class zzh extends RelativeLayout {
    @VisibleForTesting
    private zzazc zzbue;
    @VisibleForTesting
    boolean zzdro;

    public zzh(Context context, String str, String str2) {
        super(context);
        this.zzbue = new zzazc(context, str);
        this.zzbue.zzee(str2);
    }

    public final boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (!this.zzdro) {
            this.zzbue.zze(motionEvent);
        }
        return false;
    }
}
