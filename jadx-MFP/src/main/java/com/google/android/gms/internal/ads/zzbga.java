package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class zzbga implements OnClickListener {
    private final /* synthetic */ JsResult zzexp;

    zzbga(JsResult jsResult) {
        this.zzexp = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzexp.cancel();
    }
}
