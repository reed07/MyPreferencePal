package com.google.android.gms.tagmanager;

import android.os.Handler.Callback;
import android.os.Message;

final class zzfs implements Callback {
    private final /* synthetic */ zzfr zzbgm;

    zzfs(zzfr zzfr) {
        this.zzbgm = zzfr;
    }

    public final boolean handleMessage(Message message) {
        if (1 == message.what && zzfn.zzbfz.equals(message.obj)) {
            this.zzbgm.zzbgl.dispatch();
            if (!this.zzbgm.zzbgl.isPowerSaveMode()) {
                zzfr zzfr = this.zzbgm;
                zzfr.zzh((long) zzfr.zzbgl.zzbgd);
            }
        }
        return true;
    }
}
