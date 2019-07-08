package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import com.google.android.gms.ads.internal.zzbv;

final class zzany implements OnClickListener {
    private final /* synthetic */ zzanx zzdpe;

    zzany(zzanx zzanx) {
        this.zzdpe = zzanx;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        Intent createIntent = this.zzdpe.createIntent();
        zzbv.zzlf();
        zzayh.zza(this.zzdpe.mContext, createIntent);
    }
}
