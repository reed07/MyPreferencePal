package com.google.android.gms.tagmanager;

import android.content.Context;
import com.google.android.gms.common.util.VisibleForTesting;

public final class zzbb implements zzbx {
    private static final Object zzaze = new Object();
    private static zzbb zzbbw;
    private zzej zzbaj;
    private zzby zzbbx;

    private zzbb(Context context) {
        this(zzbz.zzv(context), new zzfl());
    }

    @VisibleForTesting
    private zzbb(zzby zzby, zzej zzej) {
        this.zzbbx = zzby;
        this.zzbaj = zzej;
    }

    public static zzbx zzp(Context context) {
        zzbb zzbb;
        synchronized (zzaze) {
            if (zzbbw == null) {
                zzbbw = new zzbb(context);
            }
            zzbb = zzbbw;
        }
        return zzbb;
    }

    public final boolean zzdo(String str) {
        if (!this.zzbaj.zzew()) {
            zzdi.zzab("Too many urls sent too quickly with the TagManagerSender, rate limiting invoked.");
            return false;
        }
        this.zzbbx.zzdt(str);
        return true;
    }
}
