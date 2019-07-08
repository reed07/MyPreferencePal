package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class zzazf implements OnClickListener {
    private final String zzbpk;
    private final zzazc zzely;

    zzazf(zzazc zzazc, String str) {
        this.zzely = zzazc;
        this.zzbpk = str;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzely.zza(this.zzbpk, dialogInterface, i);
    }
}
