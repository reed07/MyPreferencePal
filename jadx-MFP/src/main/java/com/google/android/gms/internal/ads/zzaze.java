package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final /* synthetic */ class zzaze implements OnClickListener {
    private final zzazc zzely;
    private final int zzelz;
    private final int zzema;
    private final int zzemb;

    zzaze(zzazc zzazc, int i, int i2, int i3) {
        this.zzely = zzazc;
        this.zzelz = i;
        this.zzema = i2;
        this.zzemb = i3;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzely.zza(this.zzelz, this.zzema, this.zzemb, dialogInterface, i);
    }
}
