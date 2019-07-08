package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzaof implements OnClickListener {
    private final /* synthetic */ zzaod zzdpx;

    zzaof(zzaod zzaod) {
        this.zzdpx = zzaod;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdpx.zzda("User canceled the download.");
    }
}
