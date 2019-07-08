package com.google.android.gms.internal.ads;

import android.content.Context;
import com.google.android.gms.ads.internal.zzbv;
import java.util.concurrent.Callable;

final class zzauc implements Callable<zzatz> {
    private final /* synthetic */ Context val$context;
    private final /* synthetic */ zzaub zzedq;

    zzauc(zzaub zzaub, Context context) {
        this.zzedq = zzaub;
        this.val$context = context;
    }

    public final /* synthetic */ Object call() throws Exception {
        zzatz zzatz;
        zzaud zzaud = (zzaud) this.zzedq.zzedp.get(this.val$context);
        if (zzaud != null) {
            if (!(zzaud.zzedr + ((Long) zzwu.zzpz().zzd(zzaan.zzcsv)).longValue() < zzbv.zzlm().currentTimeMillis())) {
                zzatz = new zzaua(this.val$context, zzaud.zzeds).zzwx();
                this.zzedq.zzedp.put(this.val$context, new zzaud(this.zzedq, zzatz));
                return zzatz;
            }
        }
        zzatz = new zzaua(this.val$context).zzwx();
        this.zzedq.zzedp.put(this.val$context, new zzaud(this.zzedq, zzatz));
        return zzatz;
    }
}
