package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;

final class zzanz implements OnClickListener {
    private final /* synthetic */ zzanx zzdpe;

    zzanz(zzanx zzanx) {
        this.zzdpe = zzanx;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        this.zzdpe.zzda("Operation denied by user.");
    }
}
