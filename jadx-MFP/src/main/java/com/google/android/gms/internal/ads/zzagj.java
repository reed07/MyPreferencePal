package com.google.android.gms.internal.ads;

import android.os.Handler;
import java.util.ArrayList;
import java.util.List;

@zzark
final class zzagj {
    /* access modifiers changed from: private */
    public final List<zzahi> zzbnj = new ArrayList();

    zzagj() {
    }

    /* access modifiers changed from: 0000 */
    public final void zza(zzahj zzahj) {
        Handler handler = zzayh.zzelc;
        for (zzahi zzahh : this.zzbnj) {
            handler.post(new zzahh(this, zzahh, zzahj));
        }
        this.zzbnj.clear();
    }
}
