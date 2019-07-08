package com.google.android.gms.internal.ads;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import java.util.List;

final class zzayk implements zzabl {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ List zzelj;
    private final /* synthetic */ zzabk zzelk;

    zzayk(zzayh zzayh, List list, zzabk zzabk, Context context) {
        this.zzelj = list;
        this.zzelk = zzabk;
        this.val$context = context;
    }

    public final void zzrn() {
    }

    public final void zzrm() {
        for (String str : this.zzelj) {
            String str2 = "Pinging url: ";
            String valueOf = String.valueOf(str);
            zzaxz.zzen(valueOf.length() != 0 ? str2.concat(valueOf) : new String(str2));
            this.zzelk.mayLaunchUrl(Uri.parse(str), null, null);
        }
        this.zzelk.zzc((Activity) this.val$context);
    }
}
