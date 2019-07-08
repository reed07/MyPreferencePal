package com.google.android.gms.internal.ads;

@zzark
final class zzbeb implements Runnable {
    private boolean zzbxt = false;
    private zzbdk zzeuh;

    zzbeb(zzbdk zzbdk) {
        this.zzeuh = zzbdk;
    }

    public final void run() {
        if (!this.zzbxt) {
            this.zzeuh.zzabm();
            zzacf();
        }
    }

    public final void pause() {
        this.zzbxt = true;
        this.zzeuh.zzabm();
    }

    public final void resume() {
        this.zzbxt = false;
        zzacf();
    }

    private final void zzacf() {
        zzayh.zzelc.removeCallbacks(this);
        zzayh.zzelc.postDelayed(this, 250);
    }
}
