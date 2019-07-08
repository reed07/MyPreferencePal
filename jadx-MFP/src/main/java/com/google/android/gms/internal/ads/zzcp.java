package com.google.android.gms.internal.ads;

final class zzcp implements Runnable {
    private final /* synthetic */ zzco zzqt;

    zzcp(zzco zzco) {
        this.zzqt = zzco;
    }

    public final void run() {
        if (this.zzqt.zzqr == null) {
            synchronized (zzco.zzqp) {
                if (this.zzqt.zzqr == null) {
                    boolean booleanValue = ((Boolean) zzwu.zzpz().zzd(zzaan.zzctg)).booleanValue();
                    if (booleanValue) {
                        try {
                            zzco.zzqq = new zzur(this.zzqt.zzqo.zzsp, "ADSHIELD", null);
                        } catch (Throwable unused) {
                            booleanValue = false;
                        }
                    }
                    this.zzqt.zzqr = Boolean.valueOf(booleanValue);
                    zzco.zzqp.open();
                }
            }
        }
    }
}
