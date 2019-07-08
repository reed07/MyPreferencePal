package com.google.android.gms.internal.ads;

import android.os.RemoteException;
import java.io.IOException;

final /* synthetic */ class zzuh implements Runnable {
    private final zzug zzcag;
    private final zztx zzcah;
    private final zzty zzcai;
    private final zzbcl zzcaj;

    zzuh(zzug zzug, zztx zztx, zzty zzty, zzbcl zzbcl) {
        this.zzcag = zzug;
        this.zzcah = zztx;
        this.zzcai = zzty;
        this.zzcaj = zzbcl;
    }

    public final void run() {
        zzug zzug = this.zzcag;
        zztx zztx = this.zzcah;
        zzty zzty = this.zzcai;
        zzbcl zzbcl = this.zzcaj;
        try {
            zztv zza = zztx.zzoh().zza(zzty);
            if (!zza.zzoe()) {
                zzbcl.setException(new RuntimeException("No entry contents."));
                zzug.zzcad.disconnect();
                return;
            }
            zzuj zzuj = new zzuj(zzug, zza.zzof(), 1);
            int read = zzuj.read();
            if (read != -1) {
                zzuj.unread(read);
                zzbcl.set(zzuj);
                return;
            }
            throw new IOException("Unable to read from cache.");
        } catch (RemoteException | IOException e) {
            zzaxz.zzb("Unable to obtain a cache service instance.", e);
            zzbcl.setException(e);
            zzug.zzcad.disconnect();
        }
    }
}
