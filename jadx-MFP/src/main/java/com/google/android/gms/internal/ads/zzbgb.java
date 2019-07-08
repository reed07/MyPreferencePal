package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.webkit.JsResult;

final class zzbgb implements OnClickListener {
    private final /* synthetic */ JsResult zzexp;

    zzbgb(JsResult jsResult) {
        this.zzexp = jsResult;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzexp.confirm();
    }
}
