package com.google.android.gms.internal.ads;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.net.Uri;
import com.google.android.gms.ads.internal.zzbv;

final class zzazl implements OnClickListener {
    private final /* synthetic */ zzazk zzemk;

    zzazl(zzazk zzazk) {
        this.zzemk = zzazk;
    }

    public final void onClick(DialogInterface dialogInterface, int i) {
        zzbv.zzlf();
        zzayh.zza(this.zzemk.val$context, Uri.parse("https://support.google.com/dfp_premium/answer/7160685#push"));
    }
}
