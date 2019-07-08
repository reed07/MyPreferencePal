package com.google.android.gms.internal.ads;

import java.io.IOException;

final class zzlg implements Runnable {
    private final /* synthetic */ IOException zzawj;
    private final /* synthetic */ zzlc zzaxn;

    zzlg(zzlc zzlc, IOException iOException) {
        this.zzaxn = zzlc;
        this.zzawj = iOException;
    }

    public final void run() {
        this.zzaxn.zzawo.zzb(this.zzawj);
    }
}
